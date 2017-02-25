package com.stnetix.ariaddna.desktopcore.fileWatchigService;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * File System Watching Service
 * Generate events CREATE, MODIFY, MOVE, RENAME, DELETE
 */
public class FileSystemWatchingService {

    //default Watch service
    private final WatchService watcher;
    ////Map contains pairs <WatchKey, Path> for take full path from keys
    private final Map<WatchKey, Path> keys;
    //native events handler
    private final Consumer consumer;
    //Watch event listeners
    private final List<FileSystemEventListener> listeners = new ArrayList<>();
    //flag for stop service
    private static boolean isStoped = false;

    /**
     * Creates a WatchService and registers the given directory
     *
     * @param dir root watched directory
     * @throws IOException
     */
    FileSystemWatchingService(Path dir) throws IOException {
        //get default watching service
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        consumer = new Consumer(this);
        walkAndRegisterDirectories(dir);
        new Thread(consumer).start();
    }

    /**
     * Register the given directory with the WatchService; This function will be called by FileVisitor
     *
     * @param dir registered directory
     * @throws IOException
     */
    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the WatchService.
     *
     * @param start parent directory
     * @throws IOException
     */
    void walkAndRegisterDirectories(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Process all native events for keys queued to the watcher
     */
    void processEvents() {
        for (; ; ) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            //get bind path
            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }
            //get events from key
            List<WatchEvent<?>> events = key.pollEvents();

            //process events
            //in this method native events transforms to FileSystemWatchEvent
            consumer.adaptEvent(events, dir);

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    /**
     * Stop service
     */
    public void stopService() {
        isStoped = true;
    }

    /**
     * Add event listeners
     *
     * @param eventListener
     */
    public void addEventListner(FileSystemEventListener eventListener) {
        listeners.add(eventListener);
    }

    /**
     * run event listeners
     *
     * @param event
     */
    void runEventListeners(FileSystemWatchEvent event) {
        for (FileSystemEventListener l :
                listeners) {
            l.processEvent(event);
        }
    }

}

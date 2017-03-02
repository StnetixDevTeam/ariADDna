package com.stnetix.ariaddna.localstoragemanager.fileSystemWatchingService;

/**
 * Interface fo external event listeners
 */
@FunctionalInterface
public interface FileSystemEventListener {
    /**
     * @param event FileSystemWatchEvent
     */
    void processEvent(FileSystemWatchEvent event);
}

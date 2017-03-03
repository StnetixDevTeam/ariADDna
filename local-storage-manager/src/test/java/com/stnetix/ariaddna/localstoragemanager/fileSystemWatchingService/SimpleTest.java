package com.stnetix.ariaddna.localstoragemanager.fileSystemWatchingService;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by User on 03.03.2017.
 */
public class SimpleTest {


    /**
     * Run watch service for testing manual work with file system
     *
     * @throws IOException
     */
    @Test
    void simple() throws IOException {
        Path path = Paths.get("C:/temp");

        FileSystemWatchingService service = new FileSystemWatchingService(path);

        service.addEventListener(e -> {
            if (e.getType().equals(FileSystemWatchEvent.Type.RENAME)) System.out.println("ok");
        });

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Path file = null;
            try {
                file = Files.createTempFile(Paths.get("C:/temp"), "temp", ".tmp");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Files.move(file, Paths.get("C:/temp/33.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Files.delete(Paths.get("C:/temp/33.txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            service.stop();

        }).start();


        service.processEvents();
    }
}

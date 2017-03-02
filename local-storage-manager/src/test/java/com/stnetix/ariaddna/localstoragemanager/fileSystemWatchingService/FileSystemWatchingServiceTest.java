package com.stnetix.ariaddna.localstoragemanager.fileSystemWatchingService;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;


class FileSystemWatchingServiceTest {
    static FileSystemWatchingService service;
    static Path root;
    static int count;

    @BeforeAll
    static void setUp() throws IOException {
        //Change to yours test folder
        root = Files.createTempDirectory("ariaddnaTemp");
        service = new FileSystemWatchingService(root);


        Thread thread = new Thread(() -> {
            service.processEvents();
        });
        thread.start();

        try {
            thread.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @BeforeEach
    void beforeEach() {
        count = 0;
        service.removeEventListners();
    }

    @Test
    void createFilesTest() throws InterruptedException {

        service.addEventListener(e -> {
            if (e.getType().equals(FileSystemWatchEvent.Type.CREATE)) {
                count++;
            }
        });

        int filesCount = 10;

        createTempFiles(filesCount, root);

        Thread.sleep(1000);

        Assertions.assertEquals(filesCount, count);
    }

    @Test
    void deleteFilesTest() throws IOException, InterruptedException {
        count = 0;

        service.addEventListener(e -> {
            if (e.getType().equals(FileSystemWatchEvent.Type.DELETE)) {
                count++;
            }
        });

        int filesCount = 10;

        createTempFiles(filesCount, root);

        Thread.sleep(1000);

        deleteFiles(root);

        Thread.sleep(1000);

        Assertions.assertEquals(filesCount, count);


    }

    static void createTempFiles(int filesCount, Path rootDir) {
        for (int i = 0; i < filesCount; i++) {
            try {
                Files.createTempFile(rootDir, "ariaddnaFile" + i, ".tmp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void deleteFiles(Path dir) throws IOException {

        Files.walkFileTree(dir, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @AfterAll
    static void tearDown() throws IOException {

        deleteFiles(root);

        Files.delete(root);
    }




}
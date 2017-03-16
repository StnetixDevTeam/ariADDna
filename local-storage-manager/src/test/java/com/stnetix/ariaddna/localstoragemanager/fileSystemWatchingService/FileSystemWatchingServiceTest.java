package com.stnetix.ariaddna.localstoragemanager.fileSystemWatchingService;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;


class FileSystemWatchingServiceTest {
    private static FileSystemWatchingService service;
    private static Path root;
    private static int count;

    @BeforeAll
    static void setUp() throws IOException {
        root = Files.createTempDirectory("ariaddnaTemp");
        service = new FileSystemWatchingService(root);

        new Thread(() -> service.processEvents()).start();
    }

    @BeforeEach
    void beforeEach() throws IOException {

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

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        createTempFiles(filesCount, root);

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(1500);
        Assertions.assertEquals(filesCount, count);
    }

    @Test
    void deleteFilesTest() throws IOException, InterruptedException {

        int filesCount = 10;

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.addEventListener(e -> {
            if (e.getType().equals(FileSystemWatchEvent.Type.DELETE)) {
                count++;
            }
        });

        createTempFiles(filesCount, root);

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            deleteFiles(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(2500);

        Assertions.assertEquals(filesCount, count);
    }

    @Test
    void renameFileTest() throws InterruptedException, IOException {

        service.addEventListener(event -> {
            if (event.getType().equals(FileSystemWatchEvent.Type.RENAME)) {
                count++;
            }
        });

        int fileCount = 10;

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createTempFiles(fileCount, root);

        try {
            renameFiles(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.sleep(2000);

        Assertions.assertEquals(fileCount, count);
    }

    @Test
    void moveFileTest() throws IOException, InterruptedException {
        service.addEventListener(event -> {
            if (event.getType().equals(FileSystemWatchEvent.Type.MOVE)) {
                count++;
            }
        });

        int fileCount = 10;

        Path newFolder = Files.createTempDirectory(root, "new");

        createTempFiles(fileCount, root);

        Thread.sleep(500);

        moveFiles(root, newFolder);

        Thread.sleep(500);

        Assertions.assertEquals(fileCount, count);

        deleteFiles(newFolder);

        Files.delete(newFolder);


    }

    private static void createTempFiles(int filesCount, Path rootDir) {
        for (int i = 0; i < filesCount; i++) {
            try {
                Files.createTempFile(rootDir, "ariaddnaFile" + i, ".tmp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteFiles(Path dir) throws IOException {

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

    private static void renameFiles(Path dir) throws IOException {
        final int[] i = {0};

        Files.walkFileTree(dir, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (Files.isDirectory(file)) return FileVisitResult.CONTINUE;
                Path parent = file.getParent();
                Files.move(file, Paths.get(parent.toString(), "newFile" + i[0] + ".tmp"));

                i[0]++;

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

    private static void moveFiles(Path from, Path to) throws IOException {
        Files.walkFileTree(from, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.move(file, to.resolve(file.getFileName()));
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

    @AfterEach
    void tearDown() throws IOException {
        deleteFiles(root);
    }

    @AfterAll
    static void tearDownAll() throws IOException {
        service.stop();
        Files.delete(root);
    }
}
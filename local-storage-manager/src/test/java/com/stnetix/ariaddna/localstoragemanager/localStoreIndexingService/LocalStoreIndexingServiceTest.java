package com.stnetix.ariaddna.localstoragemanager.localStoreIndexingService;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;


class LocalStoreIndexingServiceTest {
    static Path root;
    static LocalStoreIndexingService service;

    @BeforeAll
    static void setUpAll() throws IOException {
        service = new LocalStoreIndexingService();
    }


    @BeforeEach
    void setUp() throws IOException {
        root = Files.createTempDirectory("AriaddnaTemp");
    }


    @Test
    void findFileByName() throws IOException {
        createTempFiles(2, root);
        String fileName = "tempFile1.tmp";
        Optional<Path> result = service.findFileByName(root, fileName);
        Assertions.assertEquals(fileName, result.get().getFileName().toString());
    }

    @Test
    void findNonexistentFile() throws IOException {
        createTempFiles(2, root);
        Optional<Path> result = service.findFileByName(root, "SomeFile.txt");
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    void findFilesByMatch() throws IOException {
        String match = "*1.*";
        int filesCount = 4;
        createTempFiles(filesCount, root);
        Assertions.assertEquals(1, service.findFiles(root, match).size());
    }

    @Test
    void findAllFiles() throws IOException {
        int filesCount = 4;
        createTempFiles(4, root);
        Assertions.assertEquals(filesCount, service.findAllFiles(root).size());
    }

    @Test
    void findFilesByCreationDate() throws IOException {
        int startCount = 5;
        createTempFiles(5, root);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date = new Date();
        int newFilesCount = 5;
        createTempFiles(5, root, startCount);
        Assertions.assertEquals(newFilesCount, service.findFilesByCreateDate(root, date).size());
    }

    @Test
    void findFilesInSubDirectories() throws IOException {
        createTempFiles(5, root);
        Path sub = Files.createTempDirectory(root, "sub");
        createTempFiles(3, sub, 7);
        String fileName = "tempFile8.tmp";
        Assertions.assertEquals(fileName, service.findFileByName(root, fileName).get().getFileName().toString());
    }

    private static void createTempFiles(int filesCount, Path rootDir) {
        createTempFiles(filesCount, rootDir, 0);
    }

    private static void createTempFiles(int filesCount, Path rootDir, int startCount) {
        for (int i = startCount; i < filesCount + startCount; i++) {
            try {
                Files.createFile(rootDir.resolve("tempFile" + i + ".tmp"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void deleteFiles(Path dir) throws IOException {

        Files.walk(dir, FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @AfterEach
    void tearDown() throws IOException {
        deleteFiles(root);
    }

    @AfterAll
    static void tearDownAll() throws IOException {
    }

}
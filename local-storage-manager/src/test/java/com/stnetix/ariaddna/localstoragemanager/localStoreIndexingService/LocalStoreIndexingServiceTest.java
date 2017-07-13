package com.stnetix.ariaddna.localstoragemanager.localStoreIndexingService;

import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;


public class LocalStoreIndexingServiceTest {
    static Path root;
    static LocalStoreIndexingService service;

    @BeforeClass
    public static void setUpAll() throws IOException {
        service = new LocalStoreIndexingService();
    }


    @Before
    public void setUp() throws IOException {
        root = Files.createTempDirectory("AriaddnaTemp");
    }


    @Test
    public void findFileByName() throws IOException {
        createTempFiles(2, root);
        String fileName = "tempFile1.tmp";
        Optional<Path> result = service.findFileByName(root, fileName);
        Assert.assertEquals(fileName, result.get().getFileName().toString());
    }

    @Test
    public void findNonexistentFile() throws IOException {
        createTempFiles(2, root);
        Optional<Path> result = service.findFileByName(root, "SomeFile.txt");
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void findFilesByMatch() throws IOException {
        String match = "*1.*";
        int filesCount = 4;
        createTempFiles(filesCount, root);
        Assert.assertEquals(1, service.findFiles(root, match).size());
    }

    @Test
    public void findAllFiles() throws IOException {
        int filesCount = 4;
        createTempFiles(4, root);
        Assert.assertEquals(filesCount, service.findAllFiles(root).size());
    }

    @Test
    public void findFilesByCreationDate() throws IOException {
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
        Assert.assertEquals(newFilesCount, service.findFilesByCreateDate(root, date).size());
    }

    @Test
    public void findFilesInSubDirectories() throws IOException {
        createTempFiles(5, root);
        Path sub = Files.createTempDirectory(root, "sub");
        createTempFiles(3, sub, 7);
        String fileName = "tempFile8.tmp";
        Assert.assertEquals(fileName, service.findFileByName(root, fileName).get().getFileName().toString());
    }

    @Test
    public void getFileAttribute() throws IOException {
        createTempFiles(1, root);
        Path file = root.resolve("temFFpFile0.tmp");
        FileAttributes attr = service.getFileAttributes(file);
        Assert.assertTrue(attr.getCreationTime().getTime() > 0);
        Assert.assertTrue(attr.getLastAccessTime().getTime() > 0);
        Assert.assertTrue(attr.getModifyTime().getTime() > 0);
        Assert.assertFalse(attr.isDirectory());
        Assert.assertTrue(attr.isRegularFile());
        Assert.assertFalse(attr.isSymbolicLinc());
    }

    @Test
    public void getLastModifyTime() throws IOException {
        createTempFiles(1, root);
        Path file = root.resolve("tempFile0.tmp");
        Date date = new Date();
        Files.write(file, "qwerty".getBytes());
        FileAttributes attr = service.getFileAttributes(file);
        Assert.assertTrue(attr.getCreationTime().getTime() < attr.getModifyTime().getTime());
        Assert.assertTrue(attr.getModifyTime().getTime() > date.getTime());
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

    @After
    public void tearDown() throws IOException {
        deleteFiles(root);
    }

    @AfterClass
    public static void tearDownAll() throws IOException {
    }

}
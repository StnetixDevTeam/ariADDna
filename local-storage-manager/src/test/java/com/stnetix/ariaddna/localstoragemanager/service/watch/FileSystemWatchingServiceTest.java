/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.localstoragemanager.service.watch;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.stnetix.ariaddna.localstoragemanager.event.FileSystemWatchEvent;
import com.stnetix.ariaddna.localstoragemanager.service.FileSystemWatchingService;

public class FileSystemWatchingServiceTest {
    private static final int MIN_TIMEOUT_MS = 1500;
    private static FileSystemWatchingService service;
    private static Path dir;

    @BeforeClass
    public static void setUp() throws IOException {
        dir = Files.createTempDirectory("Temp");
        service = new FileSystemWatchingService(dir);

        new Thread(() -> service.processEvents()).start();
    }

    @Before
    public void beforeEach() throws IOException {
        service.removeEventListners();
    }

    @Test
    public void createFilesTest() throws InterruptedException {
        AtomicInteger actualdFileCounts = new AtomicInteger();
        int expectedFilesCount = 10;

        service.addEventListener(e -> {
            if (e.getType().equals(FileSystemWatchEvent.Type.CREATE)) {
                actualdFileCounts.getAndIncrement();
            }
        });

        Thread.sleep(MIN_TIMEOUT_MS);

        createTempFiles(expectedFilesCount, dir);

        Thread.sleep(MIN_TIMEOUT_MS);

        Assert.assertEquals(expectedFilesCount, actualdFileCounts.get());
    }

    @Test
    public void deleteFilesTest() throws IOException, InterruptedException {
        AtomicInteger actualdFileCounts = new AtomicInteger();
        int expectedFilesCount = 10;

        service.addEventListener(e -> {
            if (e.getType().equals(FileSystemWatchEvent.Type.DELETE)) {
                actualdFileCounts.getAndIncrement();
            }
        });

        Thread.sleep(MIN_TIMEOUT_MS);

        createTempFiles(expectedFilesCount, dir);

        Thread.sleep(MIN_TIMEOUT_MS);

        deleteFiles(dir);

        Thread.sleep(MIN_TIMEOUT_MS);

        Assert.assertEquals(expectedFilesCount, actualdFileCounts.get());
    }

    @Test
    @Ignore
    public void renameFileTest() throws InterruptedException, IOException {
        AtomicInteger actualdFileCounts = new AtomicInteger();
        int expectedFilesCount = 10;

        service.addEventListener(event -> {
            if (event.getType().equals(FileSystemWatchEvent.Type.RENAME)) {
                actualdFileCounts.getAndIncrement();
            }
        });

        Thread.sleep(MIN_TIMEOUT_MS);

        createTempFiles(expectedFilesCount, dir);

        renameFiles(dir);

        Thread.sleep(MIN_TIMEOUT_MS);

        Assert.assertEquals(expectedFilesCount, actualdFileCounts.get());
    }

    @Test
    @Ignore
    public void moveFileTest() throws IOException, InterruptedException {
        AtomicInteger actualdFileCounts = new AtomicInteger();
        int expectedFilesCount = 10;

        service.addEventListener(event -> {
            if (event.getType().equals(FileSystemWatchEvent.Type.MOVE)) {
                actualdFileCounts.getAndIncrement();
            }
        });

        Path newFolder = Files.createTempDirectory(dir, "new");

        createTempFiles(expectedFilesCount, dir);

        Thread.sleep(MIN_TIMEOUT_MS);

        moveFiles(dir, newFolder);

        Thread.sleep(MIN_TIMEOUT_MS);

        Assert.assertEquals(expectedFilesCount, actualdFileCounts.get());

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
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void renameFiles(Path dir) throws IOException {
        final int[] i = { 0 };

        Files.walkFileTree(dir, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                if (Files.isDirectory(file)) {
                    return FileVisitResult.CONTINUE;
                }
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
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static void moveFiles(Path from, Path to) throws IOException {
        Files.walkFileTree(from, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Files.move(file, to.resolve(file.getFileName()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @After
    public void tearDown() throws IOException {
        deleteFiles(dir);
    }

    @AfterClass
    public static void tearDownAll() throws IOException {
        service.stop();
        Files.delete(dir);
    }
}
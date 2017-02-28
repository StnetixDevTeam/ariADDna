package com.stnetix.ariaddna.localstoragemanager.fileSystemWatchingService;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


class FileSystemWatchingServiceTest {
    Path path;
    FileSystemWatchingService service;

    @Test
    void simpleServiceTest() throws IOException {
        path = Paths.get("C:/temp");
        service = new FileSystemWatchingService(path);
        service.addEventListeners(System.out::println);
        service.processEvents();
    }

}
package com.stnetix.ariaddna.desktopcore.fileWatchigService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Anton on 25.02.2017.
 */
class FileSystemWatchingServiceTest {

    Path root;

    @BeforeEach
    void setUp() throws IOException {

        root = Paths.get("C:/temp");

    }

    //run service, watch changes and print events
    @Test
    void simpleServiceTest() {

    }

}
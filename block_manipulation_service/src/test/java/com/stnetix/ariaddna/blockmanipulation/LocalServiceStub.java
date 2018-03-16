package com.stnetix.ariaddna.blockmanipulation;



import com.stnetix.ariaddna.localstoragemanager.localservice.LocalService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Lexsus on 24.02.2018.
 */

public class LocalServiceStub implements LocalService {

    @Override
    public File getLocalFileByUuid(String fileUuid)
    {
        Path currentDirectory = Paths.get(".").toAbsolutePath();
        Path parentDirectory = currentDirectory.getParent().getParent();
        Path workingDirectory = parentDirectory.resolve("tmp");

        switch (fileUuid)
        {
            case "1":
                return workingDirectory.resolve("block_equal.dat").toFile();

            case "2":
                return workingDirectory.resolve("block_above.dat").toFile();

            case "3":
                return workingDirectory.resolve("block_below.dat").toFile();
        }
        return null;
    }
}

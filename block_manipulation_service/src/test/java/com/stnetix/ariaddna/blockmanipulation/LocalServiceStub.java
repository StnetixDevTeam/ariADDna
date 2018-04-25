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

package com.stnetix.ariaddna.blockmanipulation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


import com.stnetix.ariaddna.localstoragemanager.localservice.LocalService;

/**
 * Created by Lexsus on 24.02.2018.
 */

public class LocalServiceStub implements LocalService {

    @Override
    public File getLocalFileByUuid(String fileUuid) {
        Path currentDirectory = Paths.get(".").toAbsolutePath();
        Path parentDirectory = currentDirectory.getParent().getParent();
        Path workingDirectory = parentDirectory.resolve("tmp");

        switch (fileUuid) {
        case "1":
            return workingDirectory.resolve("block_equal.dat").toFile();

        case "2":
            return workingDirectory.resolve("block_above.dat").toFile();

        case "3":
            return workingDirectory.resolve("block_below.dat").toFile();

        case "4":
            return workingDirectory.toFile();

        case "5":
            return workingDirectory.resolve("payload.dat").toFile();

        case "6":
            return workingDirectory.resolve("merge_file.dat").toFile();
        default:
            return null;
        }
    }

    @Override public Map<String, String> getFileAttributes(String fileUuid) {
        File file = getLocalFileByUuid(fileUuid);
        Path path = file.toPath();
        Map<String, Object> mapAttributes = new HashMap<>();
        Map<String, String> mapProperties = new HashMap<>();
        try {
            mapAttributes = Files.readAttributes(path, "*");
            for (Map.Entry<String, Object> entry : mapAttributes.entrySet()) {
                if (entry.getValue() != null) {
                    mapProperties.put(entry.getKey(), entry.getValue().toString());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapProperties;
    }
}

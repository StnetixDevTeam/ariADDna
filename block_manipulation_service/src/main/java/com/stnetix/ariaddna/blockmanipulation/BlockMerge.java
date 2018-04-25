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
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.stnetix.ariaddna.localstoragemanager.localservice.LocalService;
import com.stnetix.ariaddna.vufs.bo.Block;
import com.stnetix.ariaddna.vufs.bo.Metafile;

public class BlockMerge {

    private LocalService localService;

    public BlockMerge() {

    }

    @Autowired
    public void setLocalService(
            LocalService localService) {
        this.localService = localService;
    }

    public File concateBlockInFile(Metafile metafile, Set<Block> blockSet) throws IOException {
        File localFile = localService.getLocalFileByUuid(metafile.getFileUuid());
        FileOutputStream outputStream = new FileOutputStream(localFile);

        //int off = 0;
        for (Block block : blockSet) {

            int size = block.getPayload().length;
            outputStream.write(block.getPayload(), 0, size);
            //off += size;
        }
        Map<String, String> attributes = metafile.getProperties();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            Path path = localFile.toPath();
            Files.setAttribute(path, entry.getKey(), entry.getValue());
        }
        return localFile;
    }
}

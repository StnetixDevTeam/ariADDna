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

package com.stnetix.ariaddna.blockmanipulation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stnetix.ariaddna.blockmanipulation.exception.BlockDoesNotExistException;
import com.stnetix.ariaddna.blockmanipulation.exception.MetafileIsFolderException;
import com.stnetix.ariaddna.commonutils.dto.vufs.FileType;
import com.stnetix.ariaddna.vufs.bo.Metafile;

@Service
@Scope(value = "session")
public class BlockManipulationServiceImpl implements IBlockManipulationService {

    private List<String> blockUuidList;

    @Override
    public String getNextBlockUuid(Metafile metafile)
            throws MetafileIsFolderException, BlockDoesNotExistException {
        if (metafile.getProperties().containsValue(FileType.DIR.toString())) {
            StringBuilder sb = new StringBuilder();
            sb.append(getMetafileInfoForLog(metafile, "getNextBlockUuid"));
            sb.append(", is folder.");
            throw new MetafileIsFolderException(sb.toString());
        } else {
            if (blockUuidList == null) {
                blockUuidList = new ArrayList<>(metafile.getBlockUuidList());
            }

            if (blockUuidList.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append(getMetafileInfoForLog(metafile, "getNextBlockUuid"));
                sb.append(" does not have a block.");
                throw new BlockDoesNotExistException(sb.toString());
            }
        }

        return blockUuidList.remove(0);
    }

    @Override
    public String getBlockUuidByNumber(Metafile metafile, int blockNumber)
            throws MetafileIsFolderException, BlockDoesNotExistException {
        List<String> blockUuidList = metafile.getBlockUuidList();
        if (metafile.getProperties().containsValue(FileType.DIR.toString())) {
            StringBuilder sb = new StringBuilder();
            sb.append(getMetafileInfoForLog(metafile, "getBlockUuidByNumber"));
            sb.append(", is folder.");
            throw new MetafileIsFolderException(sb.toString());
        } else {
            if (blockUuidList.size() < blockNumber) {
                StringBuilder sb = new StringBuilder();
                sb.append(getMetafileInfoForLog(metafile, "getBlockUuidByNumber"));
                sb.append(", has block count: ");
                sb.append(blockUuidList.size());
                sb.append(", requested number is: ");
                sb.append(blockNumber);
                throw new BlockDoesNotExistException(sb.toString());
            }
        }

        return blockUuidList.get(blockNumber);
    }

    private StringBuilder getMetafileInfoForLog(Metafile metafile, String methodName) {
        return new StringBuilder()
                .append("Method ")
                .append(methodName)
                .append(", Metafile with uuid: ")
                .append(metafile.getFileUuid())
                .append(", version: ")
                .append(metafile.getVersion());
    }
}

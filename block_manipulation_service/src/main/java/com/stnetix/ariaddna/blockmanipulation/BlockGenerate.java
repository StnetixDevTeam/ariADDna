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
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.settings.Settings;
import com.stnetix.ariaddna.commonutils.xmlparser.XmlParser;
import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;
import com.stnetix.ariaddna.commonutils.xmlparser.handlers.BlockManipulationSettingHandler;
import com.stnetix.ariaddna.localstoragemanager.localservice.LocalService;
import com.stnetix.ariaddna.vufs.bo.Block;
import com.stnetix.ariaddna.vufs.bo.Metafile;

/**
 * Created by LugovoyAV on 15.02.2018.
 */

@Service
public class BlockGenerate {
    private final int defaultBlockSize = 5242880;
    private int blockSize;

    private LocalService localService;

    public BlockGenerate() {
        if (!loadSettings()) {
            blockSize = defaultBlockSize;
        }
    }

    public int getBlockSize() {
        return blockSize;
    }

    @Autowired
    public void setLocalService(
            LocalService localService) {
        this.localService = localService;
    }

    public boolean loadSettings() {
        BlockManipulationSettingHandler handler = null;
        File file = null;
        try {

            URL settingURL = getClass().getClassLoader().getResource(Settings.fileNameSettings);
            file = new File(settingURL.getFile());
            handler = (BlockManipulationSettingHandler) new XmlParser(file,
                    new BlockManipulationSettingHandler()).getHandler();
            blockSize = handler.getBlockSize();
            return true;
        } catch (XmlParserException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Block getNextBlock(Metafile metafile) throws IOException {
        File localFile = localService.getLocalFileByUuid(metafile.getFileUuid());
        if (!localFile.isFile()) {
            throw new IllegalArgumentException("Can't get block for directory");
        }

        ByteBuffer buf = ByteBuffer.allocate(blockSize);
        FileInputStream inputStream = new FileInputStream(localFile);
        long fileSize = localFile.length();
        long offset = metafile.getBlockUuidList().size();

        if ((fileSize == 0) && (offset == 0)) {
            return createBlockForEmptyFile(localFile, metafile.getVersion(),
                    metafile.getFileUuid());
        }
        if (fileSize <= offset * blockSize) {
            throw new IllegalArgumentException("Can't generate block");
        }
        int byteReads = 0;
        FileChannel channel = inputStream.getChannel();

        channel.position(offset * blockSize);
        if ((byteReads = channel.read(buf, offset * blockSize)) != -1) {
            byte[] data = new byte[byteReads];
            buf.position(0);
            buf.get(data, 0, byteReads);
            DateTime date = new DateTime();
            //files.put(localFile, offset);
            inputStream.close();
            return new Block(metafile.getVersion(), offset, metafile.getFileUuid(), data,
                    date.getTimeInMillisec(),
                    (long) byteReads);
        } else {
            throw new IllegalArgumentException("Can't generate block");
        }
    }

    private Block createBlockForEmptyFile(File localFile, String version, String fileUuid) {
        DateTime date = new DateTime();
        byte[] data = new byte[0];
        return new Block(version, 0L, fileUuid, data, date.getTimeInMillisec(), 0L);
    }
}

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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.greenrobot.common.hash.Murmur3A;
import org.junit.Before;
import org.junit.Test;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.localstoragemanager.localservice.LocalService;
import com.stnetix.ariaddna.vufs.bo.Block;
import com.stnetix.ariaddna.vufs.bo.Metafile;



public class BMSTest {

    LocalService localService = new LocalServiceStub();
    private BlockGenerate blockGenerate;
    private BlockMerge blockMerge;

    @Before
    public void before() {
        if (blockGenerate == null) {
            blockGenerate = new BlockGenerate();
            blockGenerate.setLocalService(localService);
        }

        if (blockMerge == null) {
            blockMerge = new BlockMerge();
            blockMerge.setLocalService(localService);
        }
    }

    @Test
    public void getBlockSize() throws Exception {

        String version = "1";
        String parentFileUuid = "1";
        assertFalse(blockGenerate.getBlockSize() <= 0);

        //below
        Metafile metafileBelow = new Metafile(version, "3", parentFileUuid);
        Block newBlock = blockGenerate.getNextBlock(metafileBelow);
        assertFalse(newBlock.getSize() >= blockGenerate.getBlockSize());

        //equal
        Metafile metafileEqual = new Metafile(version, "1", parentFileUuid);
        newBlock = blockGenerate.getNextBlock(metafileEqual);
        assertFalse(newBlock.getSize() != blockGenerate.getBlockSize());

        //above
        Metafile metafileAbove = new Metafile(version, "2", parentFileUuid);
        newBlock = blockGenerate.getNextBlock(metafileAbove);
        metafileAbove.addBlockUuid(newBlock.getBlockUuid());
        assertFalse(newBlock.getSize() != blockGenerate.getBlockSize());

        newBlock = blockGenerate.getNextBlock(metafileAbove);
        metafileAbove.addBlockUuid(newBlock.getBlockUuid());
        assertFalse(newBlock.getSize() != 1);
    }

    @Test
    public void mergeFile() throws IOException {
        String version = "1";
        String parentFileUuid = "1";

        Set<Block> setBlock = new HashSet<>();
        int blockSize = blockGenerate.getBlockSize();
        Metafile metafileMerge = new Metafile(version, "6", parentFileUuid);

        byte[] data = new byte[blockSize];
        Arrays.fill(data, (byte) 0xf0);
        DateTime date = new DateTime();
        Block block = new Block(metafileMerge.getVersion(), 0L, metafileMerge.getFileUuid(), data,
                date.getTimeInMillisec(),
                (long) blockSize);
        metafileMerge.addBlockUuid(block.getBlockUuid());
        setBlock.add(block);

        data = new byte[blockSize];
        Arrays.fill(data, (byte) 0xaa);
        block = new Block(metafileMerge.getVersion(), 1L, metafileMerge.getFileUuid(), data,
                date.getTimeInMillisec(),
                (long) blockSize);
        metafileMerge.addBlockUuid(block.getBlockUuid());
        setBlock.add(block);

        //Attributes for file
        Map<String, String> attributes = localService.getFileAttributes("5");
        metafileMerge.setProperties(attributes);

        File mergeFile = blockMerge.concateBlockInFile(metafileMerge, setBlock);
        File originalFile = localService.getLocalFileByUuid("5");
        String hashMerge = "";
        String hashOriginal = "";

        data = new byte[Math.toIntExact(mergeFile.length())];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(mergeFile));
        if (bis.read(data) > 0) {
            Murmur3A murmur = new Murmur3A();
            murmur.update(data);
            hashMerge = String.valueOf(murmur.getValue());
        } else {
            fail("File not read");
        }

        data = new byte[Math.toIntExact(originalFile.length())];
        bis = new BufferedInputStream(new FileInputStream(originalFile));
        if (bis.read(data) > 0) {
            Murmur3A murmur = new Murmur3A();
            murmur.update(data);
            hashOriginal = String.valueOf(murmur.getValue());
        } else {
            fail("File not read");
        }

        assertTrue(hashOriginal.equals(hashMerge));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBlockForDirectory() throws IOException {
        String version = "1";
        String parentFileUuid = "1";
        assertFalse(blockGenerate.getBlockSize() <= 0);

        //directory
        Metafile metafileDir = new Metafile(version, "4", parentFileUuid);
        Block newBlock = blockGenerate.getNextBlock(metafileDir);
    }
}
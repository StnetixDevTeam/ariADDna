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

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.stnetix.ariaddna.vufs.bo.Block;
import com.stnetix.ariaddna.vufs.bo.Metafile;

public class BMSTest {

    private BlockGenerate blockGenerate;

    @Before
    public void before() {
        if (blockGenerate == null) {
            blockGenerate = new BlockGenerate();
            blockGenerate.setLocalService(new LocalServiceStub());
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
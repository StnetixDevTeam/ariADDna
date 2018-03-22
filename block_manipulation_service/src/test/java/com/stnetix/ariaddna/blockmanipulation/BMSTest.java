package com.stnetix.ariaddna.blockmanipulation;

import com.stnetix.ariaddna.vufs.businessobjects.Block;
import com.stnetix.ariaddna.vufs.businessobjects.Metafile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

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
        assertFalse(newBlock.getSize() != blockGenerate.getBlockSize());
        newBlock = blockGenerate.getNextBlock(metafileAbove);
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
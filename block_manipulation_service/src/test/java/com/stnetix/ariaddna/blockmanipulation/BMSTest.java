package com.stnetix.ariaddna.blockmanipulation;

import com.stnetix.ariaddna.vufs.businessobjects.Block;
import com.stnetix.ariaddna.vufs.businessobjects.Metafile;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertFalse;

public class BMSTest {

    private BlockGenerate blockGenerate;

    @Before
    public void before(){
        if(blockGenerate == null) {
            blockGenerate = new BlockGenerate();
            blockGenerate.setLocalService(new LocalServiceStub());
        }
    }

    @Test
    public void getBlockSize() throws Exception {

        String version="1";
        String parentFileUuid = "1";
        assertFalse(blockGenerate.getBlockSize()<=0);

        //equal
        Metafile metafileBelow = new Metafile(version,"3",parentFileUuid);
        Block newBlock = blockGenerate.getNextBlock(metafileBelow);
        assertFalse(newBlock.getSize()>=blockGenerate.getBlockSize());
        try
        {
            newBlock = blockGenerate.getNextBlock(metafileBelow);
            assertFalse(false);
        }
        catch (IllegalArgumentException e)
        {

        }

        //equal
        Metafile metafileEqual = new Metafile(version,"1",parentFileUuid);
        newBlock = blockGenerate.getNextBlock(metafileEqual);
        assertFalse(newBlock.getSize()!=blockGenerate.getBlockSize());
        try
        {
            newBlock = blockGenerate.getNextBlock(metafileEqual);
            assertFalse(false);
        }
        catch (IllegalArgumentException e)
        {

        }

        //above
        Metafile metafileAbove = new Metafile(version,"2",parentFileUuid);
        newBlock = blockGenerate.getNextBlock(metafileAbove);
        assertFalse(newBlock.getSize()!=blockGenerate.getBlockSize());
        try
        {
            newBlock = blockGenerate.getNextBlock(metafileAbove);
            assertFalse(newBlock.getSize()!=1);

        }
        catch (IllegalArgumentException e)
        {
            assertFalse(false);
        }
    }
}
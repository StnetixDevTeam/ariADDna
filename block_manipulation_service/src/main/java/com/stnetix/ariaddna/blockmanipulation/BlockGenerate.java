package com.stnetix.ariaddna.blockmanipulation;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.settings.Settings;
import com.stnetix.ariaddna.commonutils.xmlparser.XmlParser;
import com.stnetix.ariaddna.commonutils.xmlparser.exception.XmlParserException;
import com.stnetix.ariaddna.commonutils.xmlparser.handlers.BlockManipulationSettingHandler;
import com.stnetix.ariaddna.localstoragemanager.localservice.LocalService;
import com.stnetix.ariaddna.vufs.businessobjects.Block;
import com.stnetix.ariaddna.vufs.businessobjects.Metafile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * Created by LugovoyAV on 15.02.2018.
 */
@Service
public class BlockGenerate {
    private final int defaultBlockSize = 5242880;
    private int blockSize;

    private HashMap<File,Long> files = new HashMap<>();

    public int getBlockSize() {
        return blockSize;
    }

    public BlockGenerate() {
        if (!loadSettings())
            blockSize = defaultBlockSize;
    }

    private LocalService localService;

    @Autowired
    public void setLocalService(
            LocalService localService) {
        this.localService = localService;
    }

    public boolean loadSettings()
    {
        BlockManipulationSettingHandler handler = null;
        File file = null;
        try {

            URL settingURL = getClass().getClassLoader().getResource(Settings.fileNameSettings);
            file = new File(settingURL.getFile());
            handler = (BlockManipulationSettingHandler) new XmlParser(file, new BlockManipulationSettingHandler()).getHandler();
            blockSize = handler.getBlockSize();
            return true;
        } catch (XmlParserException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Block getNextBlock(Metafile metaFile) throws IOException {
        return createNextBlock(metaFile.getVersion(),metaFile.getFileUuid());
    }

    private Block createNextBlock(String version, String fileUuid) throws IOException {
        long offset = -1;
        File localFile = localService.getLocalFileByUuid(fileUuid);
        if (!localFile.isFile())
            throw new IllegalArgumentException("Can't get block for directory");


        //TODO we should be call restoreFileContext(metafile). We add this method in the features tasks.
        if (files.containsKey(localFile))
            offset = files.get(localFile);
        ByteBuffer buf = ByteBuffer.allocate(blockSize);

        FileInputStream inputStream = new FileInputStream(localFile);
        long fileSize = localFile.length();
        if ((fileSize == 0) && (offset == -1))
            return createBlockForEmptyFile(localFile, version, fileUuid);

        offset++;
        if (fileSize <= offset * blockSize)
            throw new IllegalArgumentException("Can't generate block");
        int byteReads = 0;
        FileChannel channel = inputStream.getChannel();

        channel.position(offset * blockSize);
        if ((byteReads = channel.read(buf, offset * blockSize)) != -1) {
            byte[] data = new byte[byteReads];
            buf.position(0);
            buf.get(data,0,byteReads);
            DateTime date = new DateTime();
            files.put(localFile, offset);
            inputStream.close();
            return new Block(version, offset, fileUuid, data, date.getTimeInMillisec(), (long) byteReads);
        }
        else
            throw new IllegalArgumentException("Can't generate block");
    }

    private Block createBlockForEmptyFile(File localFile, String version, String fileUuid)
    {
        DateTime date = new DateTime();
        files.put(localFile, 0L);
        byte[] data = new byte[0];
        return new Block(version, 0L, fileUuid, data, date.getTimeInMillisec(),  0L);
    }
}

package com.stnetix.ariaddna.blockmanipulation;



import java.io.File;

/**
 * Created by Lexsus on 24.02.2018.
 */

public class LocalServiceTest implements LocalService {

    @Override
    public File getLocalFileByUuid(String fileUuid)
    {
        switch (fileUuid)
        {
            case "1":
                return new File("block_equal.dat");

            case "2":
                return new File("block_above.dat");

        }
        return null;
    }
}

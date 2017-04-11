package com.stnetix.ariaddna.servercore.tools;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

/**
 * Created by alexkotov on 21.03.17.
 */
public class TestMainServise implements IService{
    private boolean isRun ;
    @Override
    public void start() throws AriaddnaException {
        isRun = true;
    }

    @Override
    public void stop() throws AriaddnaException {
        isRun = false;
    }

    @Override
    public boolean isRun() {
        return isRun;
    }

    @Override
    public void run() {
        try {
            start();
        } catch (AriaddnaException e) {
            e.printStackTrace();
        }
        while (isRun){

        }
    }
}

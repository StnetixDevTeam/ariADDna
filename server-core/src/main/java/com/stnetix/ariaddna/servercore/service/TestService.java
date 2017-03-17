package com.stnetix.ariaddna.servercore.service;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;
import com.stnetix.ariaddna.servercore.tools.IService;

/**
 * Created by alexkotov on 28.02.17.
 */
public class TestService implements IService{
    private boolean isStopped = false;
    @Override
    public void start() throws AriaddnaException {
        run();
    }

    @Override
    public void stop() throws AriaddnaException {
        isStopped = true;
    }

    @Override
    public boolean isRun() {
        return !isStopped;
    }

    @Override
    public void run() {
        while (!isStopped);
    }
}

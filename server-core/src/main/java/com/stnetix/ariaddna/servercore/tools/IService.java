package com.stnetix.ariaddna.servercore.tools;

import com.stnetix.ariaddna.commonutils.exception.AriaddnaException;

/**
 * Created by alexkotov on 27.02.17.
 */
public interface IService extends Runnable {
    void start() throws AriaddnaException;
    void stop() throws AriaddnaException;
}

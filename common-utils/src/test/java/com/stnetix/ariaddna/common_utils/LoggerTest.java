package com.stnetix.ariaddna.common_utils;

import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;
import org.junit.Test;

/**
 * Created by alexkotov on 23.01.17.
 */
public class LoggerTest {
    private AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(LoggerTest.class.getName());
    @Test
    public void testLogging(){
        LOGGER.info("info1");
        String s = "1111";
        LOGGER.info("info2 is {}", s);
    }

}
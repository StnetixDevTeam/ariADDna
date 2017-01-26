package com.stnetix.ariaddna.common;

import com.stnetix.ariaddna.common.logger.AriaddnaLogger;
import org.junit.Test;

/**
 * Created by alexkotov on 23.01.17.
 */
public class LoggerTest {

    @Test
    public void testLogging(){
        AriaddnaLogger logger = AriaddnaLogger.getLogger(LoggerTest.class.getName());
        logger.info("info1");
        String s = "1111";
        logger.info("info2 is {}", s);
    }

}
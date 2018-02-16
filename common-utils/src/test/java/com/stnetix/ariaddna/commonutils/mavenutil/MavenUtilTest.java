package com.stnetix.ariaddna.commonutils.mavenutil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vasap87 on 16.02.18.
 */
public class MavenUtilTest {
    @Test
    public void getCurrentVersion() throws Exception {
        String version = MavenUtil.getCurrentVersion();
        assertEquals(version, "1.0-SNAPSHOT");
    }

}
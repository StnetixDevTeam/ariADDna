package com.stnetix.ariaddna.vufs.BusinessObjects;

import de.greenrobot.common.hash.Murmur3A;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by vasap87 on 16.02.18.
 */
public class BlockUuidHashTest {

    @Test
    public void blockUuidHashTest(){
        byte[] payload = new byte[5000];
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < 5000; i++) {
            payload[i] = (byte) (i%127);
        }

        Murmur3A murmur3A = new Murmur3A();
        murmur3A.update(payload);
        long hashAfterByteArray = murmur3A.getValue();
        murmur3A.update(uuid.getBytes());
        long hashAfterByteArrayAndUuid = murmur3A.getValue();

        Murmur3A murmur3A1 = new Murmur3A();
        murmur3A1.update(uuid.getBytes());
        long hashAfterUuid = murmur3A1.getValue();

        assertNotEquals(hashAfterUuid, hashAfterByteArrayAndUuid);

        Murmur3A murmur3A2 = new Murmur3A();
        murmur3A2.update(payload);
        long hashAfterByteArray1 = murmur3A2.getValue();

        assertEquals(hashAfterByteArray, hashAfterByteArray1);

    }

}
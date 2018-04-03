/*
 * Copyright (c) 2017 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.vufs.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.UUID;

import de.greenrobot.common.hash.Murmur3A;
import org.junit.Test;

/**
 * Created by vasap87 on 16.02.18.
 */
public class BlockUuidHashTest {

    @Test
    public void blockUuidHashTest() {
        byte[] payload = new byte[5000];
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < 5000; i++) {
            payload[i] = (byte) (i % 127);
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
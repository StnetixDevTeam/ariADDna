/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.junit.Test;

import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;

public class MetafileCloneTest {

    @Test
    public void metafilecloneTest() {
        Metafile metafile1 = new Metafile("1", "fileuuid", "parentfileuuid");
        metafile1.setAllocationStrategy(AllocationStrategy.HIGH);
        Set<String> childfileUuidSet = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 5; i++) {
            childfileUuidSet.add(UUID.randomUUID().toString());
        }
        metafile1.setChildFileUuidSet(childfileUuidSet);

        List<String> blockUuidList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 5; i++) {
            blockUuidList.add(UUID.randomUUID().toString());
        }
        metafile1.setBlockUuidList(blockUuidList);

        Map<String, String> properties = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            properties.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        metafile1.setProperties(properties);

        Map<String, Set<String>> blockAllocateMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 5; i++) {
            Set<String> cloudUuidSet = new HashSet<>();
            for (int j = 0; j < 5; j++) {
                cloudUuidSet.add(UUID.randomUUID().toString());
            }
            blockAllocateMap.put(UUID.randomUUID().toString(), cloudUuidSet);
        }
        metafile1.setBlockAllocateMap(blockAllocateMap);

        //clone
        Metafile metafile2 = (Metafile) metafile1.clone();

        assertNotNull(metafile2);
        //Checks that these object have different links in memory.
        assertTrue(metafile2 != metafile1);
        assertEquals(metafile2.getFileUuid(), metafile1.getFileUuid());

    }

}
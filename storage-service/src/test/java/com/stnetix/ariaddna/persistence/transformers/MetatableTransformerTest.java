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

package com.stnetix.ariaddna.persistence.transformers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.entities.vufs.AllocationCollection;
import com.stnetix.ariaddna.persistence.entities.vufs.MetafileEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.MetatableEntity;
import com.stnetix.ariaddna.vufs.businessobjects.MetatableType;
import com.stnetix.ariaddna.vufs.dto.MetafileDTO;
import com.stnetix.ariaddna.vufs.dto.MetatableDTO;

/**
 * Created by vasap87 on 05.03.18.
 */
public class MetatableTransformerTest {
    @Test
    public void metafileEntityToDTO() throws Exception {
        MetatableTransformer metatableTransformer = new MetatableTransformer();

        MetatableEntity metatableEntity = TestHelper.getMetatableEntity(MetatableType.MASTER);

        MetatableDTO metatableDTO = metatableTransformer.metafileEntityToDTO(metatableEntity);

        assertEquals(metatableDTO.getType(), metatableEntity.getType());
        assertEquals(metatableDTO.getVersion(), metatableEntity.getVersion());
        assertEquals(metatableDTO.getUuid(), metatableEntity.getUuid());
        assertEquals(metatableDTO.getLastUpdateTimestamp(),
                metatableEntity.getLastUpdateTimestamp());

        assertEquals(metatableDTO.getMetafileSet().size(), metatableEntity.getMetafileSet()
                .size());

        int countEquals = compareMetafileDTOAndEntitySet(metatableDTO.getMetafileSet(),
                metatableEntity.getMetafileSet());
        assertEquals(countEquals, metatableDTO.getMetafileSet().size());
    }

    @Test
    public void metafileDTOtoEntity() throws Exception {
        MetatableTransformer metatableTransformer = new MetatableTransformer();

        MetatableDTO metatableDTO = TestHelper
                .getMetatableDTO(UUID.randomUUID().toString(), "0.0.0", MetatableType.MASTER,
                        new DateTime().getTimeInMillisec());

        MetatableEntity metatableEntity = metatableTransformer.metafileDTOtoEntity(metatableDTO);

        assertEquals(metatableEntity.getType(), metatableDTO.getType());
        assertEquals(metatableEntity.getVersion(), metatableDTO.getVersion());
        assertEquals(metatableEntity.getUuid(), metatableDTO.getUuid());
        assertEquals(metatableEntity.getLastUpdateTimestamp(),
                metatableDTO.getLastUpdateTimestamp());

        MetafileDTO[] metafileDTOs = new MetafileDTO[metatableDTO.getMetafileSet().size()];
        metatableDTO.getMetafileSet().toArray(metafileDTOs);

        MetafileEntity[] metafileEntities = new MetafileEntity[metatableEntity.getMetafileSet()
                .size()];
        metatableEntity.getMetafileSet().toArray(metafileEntities);

        assertEquals(metafileDTOs.length, metafileEntities.length);

        int countEquals = compareMetafileDTOAndEntitySet(metatableDTO.getMetafileSet(),
                metatableEntity.getMetafileSet());
        assertEquals(countEquals, metafileDTOs.length);
    }

    private int compareMetafileDTOAndEntitySet(Set<MetafileDTO> metafileDTOSet,
            Set<MetafileEntity> metafileEntitySet) {
        int result = 0;
        for (MetafileDTO metafileDTO : metafileDTOSet) {
            for (MetafileEntity metafileEntity : metafileEntitySet) {
                if (metafileDTO.getFileUuid().equals(metafileEntity.getFileUuid())) {
                    assertEquals(metafileDTO.getFileUuid(), metafileEntity.getFileUuid());
                    assertEquals(metafileDTO.getVersion(), metafileEntity.getVersion());
                    assertEquals(metafileDTO.getParentFileUuid(),
                            metafileEntity.getParentFileUuid());
                    assertEquals(metafileDTO.getAllocationStrategy(),
                            metafileEntity.getAllocationStrategy());
                    //check that expected map contains actual key and value
                    for (Map.Entry<String, String> entryDTO : metafileDTO.getProperties()
                            .entrySet()) {
                        assertTrue(metafileEntity.getProperties().containsKey(entryDTO.getKey()));
                        assertTrue(
                                metafileEntity.getProperties().containsValue(entryDTO.getValue()));
                    }
                    int metafileChildCompareCount = compareStringCollection(
                            metafileEntity.getChildFileUuidSet(),
                            metafileDTO.getChildFileUuidSet());
                    assertEquals(metafileDTO.getChildFileUuidSet().size(),
                            metafileChildCompareCount);

                    int blockUuidCompareCount = compareStringCollection(
                            metafileEntity.getBlockUuidList(), metafileDTO.getBlockUuidList());
                    assertEquals(metafileDTO.getBlockUuidList().size(), blockUuidCompareCount);

                    for (Map.Entry<String, AllocationCollection> entryEntity : metafileEntity
                            .getBlockAllocateMap().entrySet()) {
                        assertTrue(metafileDTO.getBlockAllocateMap()
                                .containsKey(entryEntity.getKey()));
                        assertTrue(metafileDTO.getBlockAllocateMap().get(entryEntity.getKey())
                                .containsAll(entryEntity.getValue().getCloudUuids()));
                    }
                    result++;
                }
            }
        }
        return result;
    }

    private int compareStringCollection(Iterable<String> expected, Iterable<String> actual) {
        int result = 0;
        for (String expectedString : expected) {
            for (String actualString : actual) {
                if (expectedString.equals(actualString)) {
                    result++;
                }
            }
        }
        return result;
    }
}
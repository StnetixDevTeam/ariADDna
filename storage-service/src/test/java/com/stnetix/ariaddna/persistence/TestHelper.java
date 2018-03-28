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

package com.stnetix.ariaddna.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.dto.UserDTO;
import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetafileDTO;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableType;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.AllocationCollection;
import com.stnetix.ariaddna.persistence.entities.vufs.MetafileEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.MetatableEntity;

/**
 * Created by alexkotov on 15.09.17.
 */
public class TestHelper {
    private TestHelper() {
    }

    public static UserEntity getUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setNickname("nickname");
        entity.setUuid(UUID.randomUUID());
        return entity;
    }

    public static UserDTO getUserDTO(UUID uuid, String nickname) {
        UserDTO dto = new UserDTO();
        dto.setNickname(nickname);
        dto.setUuid(uuid);
        return dto;
    }

    public static UserDTO getUserDTO() {
        return getUserDTO(UUID.randomUUID(), "nickname");
    }

    private static MetafileDTO getMetafileDTO(String fileUuid, String parentFileUuid) {
        MetafileDTO metafileDTO = new MetafileDTO();
        metafileDTO.setFileUuid(fileUuid);
        metafileDTO.setVersion(MavenUtil.getCurrentVersion());
        metafileDTO.setAllocationStrategy(AllocationStrategy.HIGH_AVAILABILITY);
        metafileDTO.setParentFileUuid(parentFileUuid);
        metafileDTO.setProperties(getFilePropertiesBySize(6));
        metafileDTO.setBlockAllocateMap(getBlockAllocateMapToDtoBySize(6));
        metafileDTO.setBlockUuidList(getUuidCollectionBySize(6));
        metafileDTO.setChildFileUuidSet(new HashSet<>(getUuidCollectionBySize(6)));

        return metafileDTO;
    }

    public static MetatableDTO getMetatableDTO(String metatableUuid, String version,
            MetatableType metatableType, Long lastUpdTimeStamp) {
        MetatableDTO metatableDTO = new MetatableDTO();
        metatableDTO.setUuid(metatableUuid);
        metatableDTO.setVersion(version);
        metatableDTO.setLastUpdateTimestamp(lastUpdTimeStamp);
        metatableDTO.setType(metatableType);
        metatableDTO.setMetafileSet(getMetafileDtoSet(6));

        return metatableDTO;
    }

    public static MetafileEntity getMetafileEntity(String fileUuid, String parentFileUuid) {
        MetafileEntity metafileEntity = new MetafileEntity();
        metafileEntity.setFileUuid(fileUuid);
        metafileEntity.setVersion(MavenUtil.getCurrentVersion());
        metafileEntity.setAllocationStrategy(AllocationStrategy.HIGH_AVAILABILITY);
        metafileEntity.setParentFileUuid(parentFileUuid);
        metafileEntity.setProperties(getFilePropertiesBySize(6));
        metafileEntity.setBlockAllocateMap(getBlockAllocateMapToEntityBySize(6, metafileEntity));
        metafileEntity.setBlockUuidList(getUuidCollectionBySize(6));
        metafileEntity.setChildFileUuidSet(new HashSet<>(getUuidCollectionBySize(6)));

        return metafileEntity;
    }

    public static MetatableEntity getMetatableEntity(MetatableType metatableType) {
        MetatableEntity metatableEntity = new MetatableEntity();
        metatableEntity.setType(metatableType);
        metatableEntity
                .setLastUpdateTimestamp(new DateTime().getTimeInMillisec());
        metatableEntity.setVersion(MavenUtil.getCurrentVersion());
        metatableEntity.setUuid(UUID.randomUUID().toString());
        metatableEntity.setMetafileSet(getMetafileEntitySet(6));
        return metatableEntity;
    }

    private static Set<MetafileEntity> getMetafileEntitySet(int size) {
        Set<MetafileEntity> metafileEntitySet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            metafileEntitySet.add(getMetafileEntity(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()));
        }
        return metafileEntitySet;
    }

    private static Map<String, AllocationCollection> getBlockAllocateMapToEntityBySize(int size,
            MetafileEntity metafileEntity) {
        Map<String, AllocationCollection> resultMap = new HashMap<>(size);
        Set<String> cloudUuidSet = new HashSet<>(3);
        for (int i = 0; i < 3; i++) {
            cloudUuidSet.add(UUID.randomUUID().toString());
        }
        AllocationCollection allocationCollection = new AllocationCollection();
        allocationCollection.setMetafileEntity(metafileEntity);
        allocationCollection.setCloudUuids(cloudUuidSet);
        for (int i = 0; i < size; i++) {
            resultMap.put(UUID.randomUUID().toString(), allocationCollection);
        }
        return resultMap;
    }

    private static Set<MetafileDTO> getMetafileDtoSet(int size) {
        Set<MetafileDTO> resultSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            resultSet.add(getMetafileDTO(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()));
        }
        return resultSet;
    }

    private static List<String> getUuidCollectionBySize(int size) {
        List<String> resultList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            resultList.add(UUID.randomUUID().toString());
        }
        return resultList;
    }

    private static Map<String, Set<String>> getBlockAllocateMapToDtoBySize(int size) {
        Map<String, Set<String>> resultMap = new HashMap<>(size);
        Set<String> cloudUuidSet = new HashSet<>(3);
        for (int i = 0; i < 3; i++) {
            cloudUuidSet.add(UUID.randomUUID().toString());
        }

        for (int i = 0; i < size; i++) {
            resultMap.put(UUID.randomUUID().toString(), cloudUuidSet);
        }
        return resultMap;
    }

    private static Map<String, String> getFilePropertiesBySize(int size) {
        Map<String, String> resultMap = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            resultMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        return resultMap;
    }

}

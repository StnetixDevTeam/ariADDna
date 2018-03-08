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

package com.stnetix.ariaddna.persistence.transformers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.stnetix.ariaddna.persistence.entities.vufs.AllocationCollection;
import com.stnetix.ariaddna.persistence.entities.vufs.MetafileEntity;
import com.stnetix.ariaddna.vufs.dto.MetafileDTO;

/**
 * 22.02.18.
 */
public class MetafileTransformer {

    public MetafileDTO metafileEntityToDTO(MetafileEntity metafileEntity) {
        MetafileDTO metafileDTO = new MetafileDTO();
        metafileDTO.setFileUuid(metafileEntity.getFileUuid());
        metafileDTO.setAllocationStrategy(metafileEntity.getAllocationStrategy());
        Map<String, Set<String>> blockAllocateMap = new HashMap<>();
        metafileEntity.getBlockAllocateMap().entrySet().forEach(entry -> {
            blockAllocateMap.put(entry.getKey(), entry.getValue().getCloudUuids());
        });
        metafileDTO.setBlockAllocateMap(blockAllocateMap);
        metafileDTO.setBlockUuidList(metafileEntity.getBlockUuidList());
        metafileDTO.setChildFileUuidSet(metafileEntity.getChildFileUuidSet());
        metafileDTO.setParentFileUuid(metafileEntity.getParentFileUuid());
        metafileDTO.setProperties(metafileEntity.getProperties());
        metafileDTO.setVersion(metafileEntity.getVersion());
        return metafileDTO;
    }

    public MetafileEntity metafileDTOtoEntity(MetafileDTO metafileDTO) {
        MetafileEntity metafileEntity = new MetafileEntity();
        metafileEntity.setFileUuid(metafileDTO.getFileUuid());
        metafileEntity.setAllocationStrategy(metafileDTO.getAllocationStrategy());
        Map<String, AllocationCollection> blockAllocateMap = new HashMap<>();

        metafileDTO.getBlockAllocateMap().entrySet().forEach(entry -> {
            AllocationCollection collection = new AllocationCollection();
            collection.setMetafileEntity(metafileEntity);
            collection.setCloudUuids(entry.getValue());
            blockAllocateMap.put(entry.getKey(), collection);
        });

        metafileEntity.setBlockAllocateMap(blockAllocateMap);
        metafileEntity.setBlockUuidList(metafileDTO.getBlockUuidList());
        metafileEntity.setChildFileUuidSet(metafileDTO.getChildFileUuidSet());
        metafileEntity.setParentFileUuid(metafileDTO.getParentFileUuid());
        metafileEntity.setProperties(metafileDTO.getProperties());
        metafileEntity.setVersion(metafileDTO.getVersion());
        return metafileEntity;
    }
}

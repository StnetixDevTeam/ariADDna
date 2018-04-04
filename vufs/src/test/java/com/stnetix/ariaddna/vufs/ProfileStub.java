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

package com.stnetix.ariaddna.vufs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetafileDTO;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableType;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;
import com.stnetix.ariaddna.userservice.IProfile;

public class ProfileStub implements IProfile {

    @Override public Set<MetatableDTO> getMetatables() {
        return null;
    }

    @Override public MetatableDTO getCurrentMasterTable() {
        return getMetatableDTO(UUID.randomUUID().toString(), MavenUtil.getCurrentVersion(),
                MetatableType.MASTER, new DateTime().getTimeInMillisec());
    }

    public MetatableDTO getMetatableDTO(String metatableUuid, String version,
            MetatableType metatableType, Long lastUpdTimeStamp) {
        MetatableDTO metatableDTO = new MetatableDTO();
        metatableDTO.setUuid(metatableUuid);
        metatableDTO.setVersion(version);
        metatableDTO.setLastUpdateTimestamp(lastUpdTimeStamp);
        metatableDTO.setType(metatableType);
        metatableDTO.setMetafileSet(getMetafileDtoSet(6));

        return metatableDTO;
    }

    private Set<MetafileDTO> getMetafileDtoSet(int size) {
        Set<MetafileDTO> resultSet = new HashSet<>(size);
        for (int i = 0; i < size; i++) {
            resultSet.add(getMetafileDTO(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()));
        }
        return resultSet;
    }

    private MetafileDTO getMetafileDTO(String fileUuid, String parentFileUuid) {
        MetafileDTO metafileDTO = new MetafileDTO();
        metafileDTO.setFileUuid(fileUuid);
        metafileDTO.setVersion(MavenUtil.getCurrentVersion());
        metafileDTO.setAllocationStrategy(AllocationStrategy.HIGH);
        metafileDTO.setParentFileUuid(parentFileUuid);
        metafileDTO.setProperties(getFilePropertiesBySize(6));
        metafileDTO.setBlockAllocateMap(getBlockAllocateMapToDtoBySize(6));
        metafileDTO.setBlockUuidList(getUuidCollectionBySize(6));
        metafileDTO.setChildFileUuidSet(new HashSet<>(getUuidCollectionBySize(6)));

        return metafileDTO;
    }

    private Map<String, String> getFilePropertiesBySize(int size) {
        Map<String, String> resultMap = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            resultMap.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        return resultMap;
    }

    private Map<String, Set<String>> getBlockAllocateMapToDtoBySize(int size) {
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

    private List<String> getUuidCollectionBySize(int size) {
        List<String> resultList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            resultList.add(UUID.randomUUID().toString());
        }
        return resultList;
    }
}

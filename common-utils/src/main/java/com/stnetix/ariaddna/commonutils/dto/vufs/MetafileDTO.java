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

package com.stnetix.ariaddna.commonutils.dto.vufs;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Data transfer object to Metafile class
 */
public class MetafileDTO {
    private String version;
    private String fileUuid;
    private String parentFileUuid;
    private Set<String> childFileUuidSet;
    private List<String> blockUuidList;
    private Map<String, String> properties;
    private AllocationStrategy allocationStrategy;
    private Map<String, Set<String>> blockAllocateMap;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getParentFileUuid() {
        return parentFileUuid;
    }

    public void setParentFileUuid(String parentFileUuid) {
        this.parentFileUuid = parentFileUuid;
    }

    public Set<String> getChildFileUuidSet() {
        return childFileUuidSet;
    }

    public void setChildFileUuidSet(Set<String> childFileUuidSet) {
        this.childFileUuidSet = childFileUuidSet;
    }

    public List<String> getBlockUuidList() {
        return blockUuidList;
    }

    public void setBlockUuidList(List<String> blockUuidList) {
        this.blockUuidList = blockUuidList;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public AllocationStrategy getAllocationStrategy() {
        return allocationStrategy;
    }

    public void setAllocationStrategy(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }

    public Map<String, Set<String>> getBlockAllocateMap() {
        return blockAllocateMap;
    }

    public void setBlockAllocateMap(Map<String, Set<String>> blockAllocateMap) {
        this.blockAllocateMap = blockAllocateMap;
    }
}

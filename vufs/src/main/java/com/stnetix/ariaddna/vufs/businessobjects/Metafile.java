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

package com.stnetix.ariaddna.vufs.businessobjects;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Metafile it's one of many vertex acyclic graph and on local filesystem metafile equals local files and directory.
 */
public class Metafile {

    private String version;
    private String fileUuid;
    private String parentFileUuid;
    private Set<String> childFileUuidSet;
    private List<String> blockUuidList;
    private Map<String, String> properties;
    private AllocationStrategy allocationStrategy;
    private Map<String, Set<String>> blockAllocateMap;

    public Metafile(String version, String fileUuid, String parentFileUuid) {
        childFileUuidSet = new CopyOnWriteArraySet<>();
        blockUuidList = new CopyOnWriteArrayList<>();
        properties = new ConcurrentHashMap<>();
        blockAllocateMap = new ConcurrentHashMap<>();

        this.version = version;
        this.fileUuid = fileUuid;
        this.parentFileUuid = parentFileUuid;
    }

    public boolean addChildFileUUid(String fileUuid) {
        return childFileUuidSet.add(fileUuid);
    }

    public boolean removeChildFileUuid(String fileUuid) {
        return childFileUuidSet.remove(fileUuid);
    }

    public boolean addBlockUuid(String blockUUid) {
        return blockUuidList.add(blockUUid);
    }

    public boolean removeBlockUuid(String blockUUid) {
        return blockUuidList.remove(blockUUid);
    }

    public void addProperty(String propName, String propValue) {
        properties.put(propName, propValue);
    }

    public void removeProperty(String propName) {
        properties.remove(propName);
    }

    public void addBlockAllocation(String blockUuid, String cloudUuid) {
        Set<String> cloudUuids = blockAllocateMap.getOrDefault(blockUuid, new HashSet<>());
        cloudUuids.add(cloudUuid);
        blockAllocateMap.put(blockUuid, cloudUuids);
    }

    public void removeBlockAllocation(String blockUuid) {
        blockAllocateMap.remove(blockUuid);
    }

    //[GETTERS]
    public String getVersion() {
        return version;
    }

    public String getFileUuid() {
        return fileUuid;
    }

    public String getParentFileUuid() {
        return parentFileUuid;
    }

    public Set<String> getChildFileUuidSet() {
        return childFileUuidSet;
    }

    public List<String> getBlockUuidList() {
        return blockUuidList;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public AllocationStrategy getAllocationStrategy() {
        return allocationStrategy;
    }

    public void setAllocationStrategy(AllocationStrategy strategy) {
        allocationStrategy = strategy;
    }

    public Map<String, Set<String>> getBlockAllocateMap() {
        return blockAllocateMap;
    }
    //[END GETTERS]
}

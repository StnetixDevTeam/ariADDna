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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.commonutils.logger.AriaddnaLogger;

/**
 * Metafile it's one of many vertex acyclic graph and on local filesystem metafile equals local
 * files and directory.
 */
public class Metafile implements Cloneable {

    private static final AriaddnaLogger LOGGER = AriaddnaLogger.getLogger(Metafile.class);

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

    public Set<String> getBlockAllocation(String blockUuid) {
        return blockAllocateMap.getOrDefault(blockUuid, new HashSet<>());
    }

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
        Set<String> childFileUuidSetCopy = new HashSet<>(childFileUuidSet);
        return childFileUuidSetCopy;
    }

    public void setChildFileUuidSet(Set<String> childFileUuidSet) {
        this.childFileUuidSet = new CopyOnWriteArraySet<>(childFileUuidSet);
    }

    public List<String> getBlockUuidList() {
        List<String> blockUuidListCopy = new ArrayList<>(blockUuidList);
        return blockUuidListCopy;
    }

    public void setBlockUuidList(List<String> blockUuidList) {
        this.blockUuidList = new CopyOnWriteArrayList<>(blockUuidList);
    }

    public Map<String, String> getProperties() {
        Map<String, String> propertiesCopy = new HashMap<>(properties);
        return propertiesCopy;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = new ConcurrentHashMap<>(properties);
    }

    public AllocationStrategy getAllocationStrategy() {
        return allocationStrategy;
    }

    public void setAllocationStrategy(AllocationStrategy strategy) {
        allocationStrategy = strategy;
    }

    public Map<String, Set<String>> getBlockAllocateMap() {
        Map<String, Set<String>> blockAllocateMapCopy = new HashMap<>(blockAllocateMap);
        return blockAllocateMapCopy;
    }

    public void setBlockAllocateMap(
            Map<String, Set<String>> blockAllocateMap) {
        this.blockAllocateMap = new ConcurrentHashMap<>(blockAllocateMap);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error("Method clone in object Metafile throw exception:", e);
            return null;
        }
    }

    public void setParentFileUuid(String parentFileUid) {
        this.parentFileUuid = parentFileUid;
    }
}

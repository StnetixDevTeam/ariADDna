package com.stnetix.ariaddna.vufs.BusinessObjects;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
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
    private Map<String, String> blockAllocateMap;

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
        blockAllocateMap.put(blockUuid, cloudUuid);
    }

    public void removeBlockAllocation(String blockUuid) {
        blockAllocateMap.remove(blockUuid);
    }

    public void setAllocationStrategy(AllocationStrategy strategy) {
        allocationStrategy = strategy;
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

    public Map<String, String> getBlockAllocateMap() {
        return blockAllocateMap;
    }
    //[END GETTERS]
}

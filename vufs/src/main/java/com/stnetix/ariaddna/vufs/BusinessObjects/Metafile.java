package com.stnetix.ariaddna.vufs.BusinessObjects;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Metafile it's one of many vertex acyclic graph and on local filesystem metafile equals local files and directory.
 */
public class Metafile {

    private String mVersion;
    private String mFileUuid;
    private String mParentFileUuid;
    private Set<String> mChildFileUuidSet;
    private List<String> mBlockUuidList;
    private Map<String, String> mProperties;
    private AllocationStrategy mAllocationStrategy;
    private Map<String, String> mBlockAllocateMap;

    public Metafile(String mVersion, String mFileUuid, String mParentFileUuid) {
        mChildFileUuidSet = new HashSet<>();
        mBlockUuidList = new ArrayList<>();
        mProperties = new HashMap<>();
        mBlockAllocateMap = new ConcurrentHashMap<>();

        this.mVersion = mVersion;
        this.mFileUuid = mFileUuid;
        this.mParentFileUuid = mParentFileUuid;
    }


    public boolean addChildFileUUid(String fileUuid){
        return mChildFileUuidSet.add(fileUuid);
    }

    public boolean removeChildFileUuid(String fileUuid){
        return mChildFileUuidSet.remove(fileUuid);
    }

    public boolean addBlockUuid(String blockUUid){
        return mBlockUuidList.add(blockUUid);
    }

    public boolean removeBlockUuid(String blockUUid){
        return mBlockUuidList.remove(blockUUid);
    }

    public void addProperty(String propName, String propValue){
        mProperties.put(propName, propValue);
    }

    public void removeProperty(String propName){
        mProperties.remove(propName);
    }

    public void addBlockAllocation(String blockUuid, String cloudUuid){
        mBlockAllocateMap.put(blockUuid, cloudUuid);
    }

    public void removeBlockAllocation(String blockUuid){
        mBlockAllocateMap.remove(blockUuid);
    }

    public void setAllocationStrategy(AllocationStrategy strategy){
        mAllocationStrategy = strategy;
    }

    //[GETTERS]
    public String getVersion() {
        return mVersion;
    }

    public String getFileUuid() {
        return mFileUuid;
    }

    public String getParentFileUuid() {
        return mParentFileUuid;
    }

    public Set<String> getChildFileUuidSet() {
        return mChildFileUuidSet;
    }

    public List<String> getBlockUuidList() {
        return mBlockUuidList;
    }

    public Map<String, String> getProperties() {
        return mProperties;
    }

    public AllocationStrategy getAllocationStrategy() {
        return mAllocationStrategy;
    }

    public Map<String, String> getBlockAllocateMap() {
        return mBlockAllocateMap;
    }
    //[END GETTERS]
}

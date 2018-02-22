package com.stnetix.ariaddna.persistence.entities.vufs;

import com.stnetix.ariaddna.vufs.BusinessObjects.AllocationStrategy;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Entity class for Metafile {@link com.stnetix.ariaddna.vufs.BusinessObjects.Metafile} class.
 */
@Entity
public class MetafileEntity {
    private String version;
    @Id
    private String fileUuid;
    private String parentFileUuid;

    @OneToMany
    private Set<String> childFileUuidSet;

    @OneToMany
    private List<String> blockUuidList;

    @ElementCollection
    @MapKeyColumn(name = "propkey")
    @Column(name = "propvalue")
    @CollectionTable(name = "metafile_properties")
    private Map<String, String> properties;

    @Enumerated(EnumType.STRING)
    private AllocationStrategy allocationStrategy;

    @OneToMany
    @MapKeyColumn(name = "block_uuid")
    private Map<String, AllocationCollection> blockAllocateMap;

    public MetafileEntity() {
    }

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

    public Map<String, AllocationCollection> getBlockAllocateMap() {
        return blockAllocateMap;
    }

    public void setBlockAllocateMap(Map<String, AllocationCollection> blockAllocateMap) {
        this.blockAllocateMap = blockAllocateMap;
    }

    public Map<String, Set<String>> getRealBlockAllocateMap() {
        Map<String, Set<String>> result = new HashMap<>();
        for (String s: blockAllocateMap.keySet()) {
            result.put(s, blockAllocateMap.get(s).getCloudUuids());
        }
        return result;
    }

    public void setRealBlockAllocateMap(Map<String, Set<String>> blockAllocateMap) {
        for (String s: blockAllocateMap.keySet()) {
            AllocationCollection collection = new AllocationCollection();
            collection.setCloudUuids(blockAllocateMap.get(s));
            this.blockAllocateMap.put(s, collection);
        }
    }
}

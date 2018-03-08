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

package com.stnetix.ariaddna.persistence.entities.vufs;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.stnetix.ariaddna.vufs.businessobjects.AllocationStrategy;

/**
 * Entity class for Metafile {@link com.stnetix.ariaddna.vufs.businessobjects.Metafile} class.
 */
@Entity
@Table(name = "Metafile")
public class MetafileEntity {
    @Id
    @Column(name = "uuid")
    private String fileUuid;
    @Column(name = "parentFileUuid", nullable = false)
    private String parentFileUuid;
    @Column(name = "version", nullable = false, updatable = false)
    private String version;

    @ElementCollection
    @CollectionTable(name = "childfileuuids", joinColumns = @JoinColumn(name = "parent_uuid"))
    @Column(name = "file_uuid")
    private Set<String> childFileUuidSet;

    @ElementCollection
    @CollectionTable(name = "fileblockuuids", joinColumns = @JoinColumn(name = "file_uuid"))
    @Column(name = "block_uuid")
    private List<String> blockUuidList;

    @ElementCollection
    @MapKeyColumn(name = "propkey")
    @Column(name = "propvalue")
    @CollectionTable(name = "metafile_properties")
    private Map<String, String> properties;

    @Enumerated(EnumType.STRING)
    private AllocationStrategy allocationStrategy;

    @OneToMany (cascade = CascadeType.ALL)
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
        for (String s : blockAllocateMap.keySet()) {
            result.put(s, blockAllocateMap.get(s).getCloudUuids());
        }
        return result;
    }

    public void setRealBlockAllocateMap(Map<String, Set<String>> blockAllocateMap) {
        for (String s : blockAllocateMap.keySet()) {
            AllocationCollection collection = new AllocationCollection();
            collection.setCloudUuids(blockAllocateMap.get(s));
            this.blockAllocateMap.put(s, collection);
        }
    }
}

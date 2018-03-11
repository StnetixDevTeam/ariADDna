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

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 22.02.18.
 */
@Entity
@Table(name = "CloudAllocation")
public class AllocationCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MetafileEntity metafileEntity;

    @ElementCollection
    @CollectionTable(name = "blockalocationclouduuids", joinColumns = @JoinColumn(name = "allocation_id"))
    @Column(name = "cloud_uuid")
    private Set<String> cloudUuids;

    public AllocationCollection() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MetafileEntity getMetafileEntity() {
        return metafileEntity;
    }

    public void setMetafileEntity(MetafileEntity metafileEntity) {
        this.metafileEntity = metafileEntity;
    }

    public Set<String> getCloudUuids() {
        return cloudUuids;
    }

    public void setCloudUuids(Set<String> cloudUuids) {
        this.cloudUuids = cloudUuids;
    }
}

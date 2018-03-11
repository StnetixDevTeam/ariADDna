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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.stnetix.ariaddna.vufs.businessobjects.MetatableType;

/**
 * Entity class for Metatable {@link com.stnetix.ariaddna.vufs.businessobjects.Metatable} class.
 */
@Entity
@Table(name = "Metatable")
public class MetatableEntity {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "version", nullable = false, updatable = false)
    private String version;

    @Enumerated(EnumType.STRING)
    @Column(name = "metatable_type", nullable = false, updatable = false)
    private MetatableType type;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "metafile_uuid", nullable = false)
    private Set<MetafileEntity> metafileSet;

    @Column(name = "lastUpdateTimestamp", nullable = false, updatable = false)
    private Long lastUpdateTimestamp;

    public MetatableEntity() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public MetatableType getType() {
        return type;
    }

    public void setType(MetatableType type) {
        this.type = type;
    }

    public Set<MetafileEntity> getMetafileSet() {
        return metafileSet;
    }

    public void setMetafileSet(Set<MetafileEntity> metafileSet) {
        this.metafileSet = metafileSet;
    }

    public Long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Long lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }
}

package com.stnetix.ariaddna.persistence.entities.vufs;

import com.stnetix.ariaddna.vufs.BusinessObjects.MetatableType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Entity class for Metatable {@link com.stnetix.ariaddna.vufs.BusinessObjects.Metatable} class.
 */
@Entity
public class MetatableEntity {
    @Id
    private String uuid;
    private String version;
    private MetatableType type;

    @OneToMany
    private Set<MetafileEntity> metafileSet;
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

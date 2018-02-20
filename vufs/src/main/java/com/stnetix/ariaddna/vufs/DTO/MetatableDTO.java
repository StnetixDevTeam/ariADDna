package com.stnetix.ariaddna.vufs.DTO;

import com.stnetix.ariaddna.vufs.BusinessObjects.MetatableType;

import java.util.Set;

/**
 * Data transfer object to Metatable {@link com.stnetix.ariaddna.vufs.BusinessObjects.Metatable} class
 */
public class MetatableDTO {
    private String version;
    private MetatableType type;
    private Set<MetafileDTO> metafileSet;
    private Long lastUpdateTimestamp;

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

    public Set<MetafileDTO> getMetafileSet() {
        return metafileSet;
    }

    public void setMetafileSet(Set<MetafileDTO> metafileSet) {
        this.metafileSet = metafileSet;
    }

    public Long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Long lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }
}

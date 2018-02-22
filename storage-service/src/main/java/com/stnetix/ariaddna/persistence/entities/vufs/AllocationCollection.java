package com.stnetix.ariaddna.persistence.entities.vufs;

import javax.persistence.*;
import java.util.Set;

/**
 * 22.02.18.
 */
@Entity
public class AllocationCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MetafileEntity metafileEntity;

    @ManyToMany
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

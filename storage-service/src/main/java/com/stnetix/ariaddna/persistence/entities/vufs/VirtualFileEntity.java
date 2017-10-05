package com.stnetix.ariaddna.persistence.entities.vufs;

import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.vufs.BusinessObjects.AllocationStrategy;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Entity to the VirtualFile
 */
@Entity
public class VirtualFileEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID uuid;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "VFILE_ID")
    private List<VirtualFileEntity> childs;

    @Enumerated(EnumType.STRING)
    private AllocationStrategy allocationStrategy;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "VFILE_ID")
    private List<FilePropertyEntity> properties;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "VFILE_ID")
    private List<ChunkFileEntity> chunks;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserEntity owner;

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public AllocationStrategy getAllocationStrategy() {
        return allocationStrategy;
    }

    public void setAllocationStrategy(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<VirtualFileEntity> getChilds() {
        return childs;
    }

    public void setChilds(List<VirtualFileEntity> childs) {
        this.childs = childs;
    }


    public List<FilePropertyEntity> getProperties() {
        return properties;
    }

    public void setProperties(List<FilePropertyEntity> properties) {
        this.properties = properties;
    }

    public List<ChunkFileEntity> getChunks() {
        return chunks;
    }

    public void setChunks(List<ChunkFileEntity> chunks) {
        this.chunks = chunks;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

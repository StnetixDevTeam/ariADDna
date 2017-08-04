package com.stnetix.ariaddna.commonutils.DTO.vufs;

import java.io.Serializable;
import java.util.List;

/**
 * Describe metadata of the virtual file. Allocation strategy is value of enum {@link AllocationStrategy}.
 * Other attributes describe platform properties about file. List of {@link ChunkFile} contains physical location on clouds.
 */
public class VirtualFile implements Serializable{
    private Long id;
    private List<VirtualFile> childs;
    private AllocationStrategy allocationStrategy;
    private List<FileProperty> properties;
    private List<ChunkFile> chunks;

    public List<VirtualFile> getChilds() {
        return childs;
    }

    public void setChilds(List<VirtualFile> childs) {
        this.childs = childs;
    }

    public AllocationStrategy getAllocationStrategy() {
        return allocationStrategy;
    }

    public void setAllocationStrategy(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }

    public List<FileProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<FileProperty> properties) {
        this.properties = properties;
    }

    public List<ChunkFile> getChunks() {
        return chunks;
    }

    public void setChunks(List<ChunkFile> chunks) {
        this.chunks = chunks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

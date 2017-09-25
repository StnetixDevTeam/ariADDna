package com.stnetix.ariaddna.vufs.DTO;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Describe metadata of the virtual file. Allocation strategy is value of enum {@link AllocationStrategyDTO}.
 * Other attributes describe platform properties about file. List of {@link ChunkFileDTO} contains physical location on clouds.
 */
public class VirtualFileDTO implements Serializable{
    private UUID uuid;
    private List<VirtualFileDTO> childs;
    private AllocationStrategyDTO allocationStrategyDTO;
    private List<FilePropertyDTO> properties;
    private List<ChunkFileDTO> chunks;

    public List<VirtualFileDTO> getChilds() {
        return childs;
    }

    public void setChilds(List<VirtualFileDTO> childs) {
        this.childs = childs;
    }

    public AllocationStrategyDTO getAllocationStrategyDTO() {
        return allocationStrategyDTO;
    }

    public void setAllocationStrategyDTO(AllocationStrategyDTO allocationStrategyDTO) {
        this.allocationStrategyDTO = allocationStrategyDTO;
    }

    public List<FilePropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(List<FilePropertyDTO> properties) {
        this.properties = properties;
    }

    public List<ChunkFileDTO> getChunks() {
        return chunks;
    }

    public void setChunks(List<ChunkFileDTO> chunks) {
        this.chunks = chunks;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

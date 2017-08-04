package com.stnetix.ariaddna.commonutils.DTO.vufs;

import java.io.Serializable;
import java.util.List;

/**
 * Describes the structure of the object which represent virtual union file system.
 */
public class VUFSObject implements Serializable {
    private Long id;
    private List<VirtualFile> virtualFiles;
    private int chunkSize;
    private Long fullAvailableSpace;
    private Long occupiedSpace;

    public List<VirtualFile> getVirtualFiles() {
        return virtualFiles;
    }

    public void setVirtualFiles(List<VirtualFile> virtualFiles) {
        this.virtualFiles = virtualFiles;
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFullAvailableSpace() {
        return fullAvailableSpace;
    }

    public void setFullAvailableSpace(Long fullAvailableSpace) {
        this.fullAvailableSpace = fullAvailableSpace;
    }

    public Long getOccupiedSpace() {
        return occupiedSpace;
    }

    public void setOccupiedSpace(Long occupiedSpace) {
        this.occupiedSpace = occupiedSpace;
    }
}

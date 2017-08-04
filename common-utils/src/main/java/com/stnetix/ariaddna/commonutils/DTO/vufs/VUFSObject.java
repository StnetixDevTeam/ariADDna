package com.stnetix.ariaddna.commonutils.DTO.vufs;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Describes the structure of the object which represent virtual union file system.
 */
public class VUFSObject implements Serializable {
    private Long id;
    private List<VirtualFile> virtualFiles;
    private int chunkSize;
    private Long fullAvailableSpace;
    private Long occupiedSpace;
    private DateTime creationDateTime;
    private DateTime disableDateTime;
    private boolean isActual;
    private UUID userUuid;

    public DateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(DateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public DateTime getDisableDateTime() {
        return disableDateTime;
    }

    public void setDisableDateTime(DateTime disableDateTime) {
        this.disableDateTime = disableDateTime;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

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

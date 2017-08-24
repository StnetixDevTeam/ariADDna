package com.stnetix.ariaddna.vufs.DTO;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Describe the chunk of file. Field url contain the place where this chunk stored.
 * Used in class {@link VirtualFile} as metadata of part of file.
 * Field urls contain collection of url to the chunk.
 * verifyHash is MD5 hash string of the chunk byte array.
 */
public class ChunkFileDTO implements Serializable {
    private UUID uuid;
    private List<String> urls;
    private String verifyHash;

    public String getVerifyHash() {
        return verifyHash;
    }

    public void setVerifyHash(String verifyHash) {
        this.verifyHash = verifyHash;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}

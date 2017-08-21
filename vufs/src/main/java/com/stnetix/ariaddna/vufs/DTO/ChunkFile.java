package com.stnetix.ariaddna.vufs.DTO;

import java.io.Serializable;
import java.util.List;

/**
 * Describe the chunk of file. Field url contain the place where this chunk stored.
 * Used in class {@link VirtualFile} as metadata of part of file.
 */
public class ChunkFile implements Serializable {
    private Long id;
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

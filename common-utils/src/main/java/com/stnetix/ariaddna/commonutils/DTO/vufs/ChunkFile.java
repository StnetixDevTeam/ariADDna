package com.stnetix.ariaddna.commonutils.DTO.vufs;

import java.io.Serializable;

/**
 * Describe the chunk of file
 */
public class ChunkFile implements Serializable {
    private Long id;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

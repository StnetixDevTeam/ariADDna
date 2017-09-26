package com.stnetix.ariaddna.persistence.entities.vufs;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/** Entity to the ChunkFile.
 */
@Entity
public class ChunkFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID uuid;

    @ElementCollection
    @CollectionTable(name = "chunkurls", joinColumns = @JoinColumn(name = "chunk_id"))
    private List<String> urls;

    private String verifyHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

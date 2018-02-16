package com.stnetix.ariaddna.vufs.BusinessObjects;

import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Metatable it's kernel structure of VUFS and implementing as acyclic graph.
 */
public class Metatable {

    private String version;
    private MetatableType type;
    private Set<Metafile> metafileSet;
    private Long lastUpdateTimestamp;

    public Metatable(MetatableType type) {
        this.type = type;
        version = MavenUtil.getCurrentVersion();
        metafileSet = new CopyOnWriteArraySet<>();
    }

    public boolean addMetafileUUid(Metafile metafile) {
        if (metafileSet.add(metafile)) {
            lastUpdateTimestamp = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public boolean removeMetafileUuid(Metafile metafile) {
        if (metafileSet.remove(metafile)) {
            lastUpdateTimestamp = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public String getVersion() {
        return version;
    }

    public MetatableType getType() {
        return type;
    }

    public Set<Metafile> getMetafileUuidSet() {
        return metafileSet;
    }

    public Long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }
}

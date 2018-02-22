package com.stnetix.ariaddna.vufs.BusinessObjects;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Metatable it's kernel structure of VUFS and implementing as acyclic graph.
 */
public class Metatable {

    private String uuid;
    private String version;
    private MetatableType type;
    private Set<Metafile> metafileSet;
    private Long lastUpdateTimestamp;

    public Metatable(MetatableType type, String uuid) {
        this.type = type;
        version = MavenUtil.getCurrentVersion();
        metafileSet = new CopyOnWriteArraySet<>();
        this.uuid = uuid;
    }

    public boolean addMetafileUUid(Metafile metafile) {
        if (metafileSet.add(metafile)) {
            lastUpdateTimestamp = new DateTime().getTimeInMillisec();
            return true;
        }
        return false;
    }

    public boolean removeMetafileUuid(Metafile metafile) {
        if (metafileSet.remove(metafile)) {
            lastUpdateTimestamp = new DateTime().getTimeInMillisec();
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

    public String getUuid() {
        return uuid;
    }
}

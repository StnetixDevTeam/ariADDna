package com.stnetix.ariaddna.vufs.BusinessObjects;

import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Metatable it's kernel structure of VUFS and implementing as acyclic graph.
 */
public class MetaTable {

    private String mVersion;
    private MasterTableType mType;
    private Set<String> mMetafileUuidSet;
    private Long mLastUpdateTimestamp;

    public MetaTable(MasterTableType mType) {
        this.mType = mType;
        mVersion = MavenUtil.getCurrentVersion();
        mMetafileUuidSet = new CopyOnWriteArraySet<>();
    }

    public boolean addMetafileUUid(String metafileUUid){
        if(mMetafileUuidSet.add(metafileUUid)){
            mLastUpdateTimestamp = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public boolean removeMetafileUuid(String metafileUuid){
        if(mMetafileUuidSet.remove(metafileUuid)){
            mLastUpdateTimestamp = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public String getVersion() {
        return mVersion;
    }

    public MasterTableType getType() {
        return mType;
    }

    public Set<String> getMetafileUuidSet() {
        return mMetafileUuidSet;
    }

    public Long getLastUpdateTimestamp() {
        return mLastUpdateTimestamp;
    }
}

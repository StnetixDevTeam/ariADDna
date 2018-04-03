/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.vufs.bo;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableType;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;

/**
 * Metatable it's kernel structure of VUFS and implementing as acyclic graph.
 */
public class Metatable {

    private String uuid;
    private String version;
    private MetatableType type;
    private Set<Metafile> metafileSet;
    private Long lastUpdateTimestamp;

    public Metatable(MetatableType type, String uuid, String version) {
        this.type = type;
        if (version != null) {
            this.version = version;
        } else {
            this.version = MavenUtil.getCurrentVersion();
        }
        metafileSet = new CopyOnWriteArraySet<>();
        this.uuid = uuid;
    }

    public boolean addMetafile(Metafile metafile) {
        if (metafileSet.add(metafile)) {
            lastUpdateTimestamp = new DateTime().getTimeInMillisec();
            return true;
        }
        return false;
    }

    public boolean removeMetafile(Metafile metafile) {
        if (metafileSet.remove(metafile)) {
            lastUpdateTimestamp = new DateTime().getTimeInMillisec();
            return true;
        }
        return false;
    }

    public boolean removeMetafileByUuid(String childMetafileUuid) {
        Metafile metafile = null;
        for (Metafile existMetafile : metafileSet) {
            if (existMetafile.getFileUuid().equalsIgnoreCase(childMetafileUuid)) {
                metafile = existMetafile;
                break;
            }
        }
        return removeMetafile(metafile);
    }

    public String getVersion() {
        return version;
    }

    public MetatableType getType() {
        return type;
    }

    public Set<Metafile> getMetafileSet() {
        return metafileSet;
    }

    public void setMetafileSet(Set<Metafile> metafileSet) {
        this.metafileSet = metafileSet;
    }

    public Long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public void setLastUpdateTimestamp(Long lastUpdateTimestamp) {
        this.lastUpdateTimestamp = lastUpdateTimestamp;
    }

    public String getUuid() {
        return uuid;
    }

}

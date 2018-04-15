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

package com.stnetix.ariaddna.allocateservice.stub;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.vufs.bo.Metafile;
import com.stnetix.ariaddna.vufs.exception.BlockNotExistInMetatableException;
import com.stnetix.ariaddna.vufs.exception.MetafileDoesNotExistException;
import com.stnetix.ariaddna.vufs.service.IVufsService;

public class VufsServiceStub implements IVufsService {

    @Override
    public Metafile createEmptyMetafile() {
        return null;
    }

    @Override
    public Metafile getMetafileByUuid(String fileUuid)
            throws MetafileDoesNotExistException {
        return null;
    }

    @Override
    public Metafile addBlockByUuidToMetafile(String blockUuid, Metafile metafile) {
        return null;
    }

    @Override
    public Metafile removeBlockByUuidFromMetafile(String blockUuid, Metafile metafile) {
        return null;
    }

    @Override
    public boolean addMetafileToMetatable(Metafile metafile) {
        return false;
    }

    @Override
    public boolean removeMetafileFromMetatable(Metafile metafile) {
        return false;
    }

    @Override
    public Set<String> getAllocationByBlockUuid(String blockUuid)
            throws BlockNotExistInMetatableException {
        Set<String> allocationSet = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            allocationSet.add(UUID.randomUUID().toString());
        }
        return allocationSet;
    }

    @Override
    public void setAllocationForBlockByUuid(String blockUuid, Set<String> allocationSet) {

    }

    @Override
    public boolean addMetafileAsChildToParent(Metafile childMetafile,
            String parentMetafileUuid) {
        return false;
    }

    @Override
    public boolean removeMetafileFromParent(String childMetafileUuid,
            String parentMetafileUuid) {
        return false;
    }

    @Override
    public AllocationStrategy getAllocationStrategyByMetafileUuid(String metafileUuid)
            throws MetafileDoesNotExistException {
        return AllocationStrategy.HA;
    }

    @Override
    public void setAllocationStrategyByMetafileUuid(String metafileUuid,
            AllocationStrategy allocationStrategy) throws MetafileDoesNotExistException {

    }
}

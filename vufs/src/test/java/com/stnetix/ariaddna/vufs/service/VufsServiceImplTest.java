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

package com.stnetix.ariaddna.vufs.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.persistence.services.IMetatableService;
import com.stnetix.ariaddna.userservice.IProfile;
import com.stnetix.ariaddna.vufs.MetatablePersisteceServiceStub;
import com.stnetix.ariaddna.vufs.ProfileStub;
import com.stnetix.ariaddna.vufs.bo.Metafile;
import com.stnetix.ariaddna.vufs.exception.MetafileDoesNotExistException;
import com.stnetix.ariaddna.vufs.transformers.MetafileTransformer;
import com.stnetix.ariaddna.vufs.transformers.MetatableTransformer;

public class VufsServiceImplTest {

    private IVufsService vufsService;

    @Before
    public void initial() {
        MetafileTransformer metafileTransformer = new MetafileTransformer();
        MetatableTransformer transformer = new MetatableTransformer();
        transformer.setMetafileTransformer(metafileTransformer);
        IProfile profile = new ProfileStub();
        IMetatableService persistService = new MetatablePersisteceServiceStub();
        vufsService = new VufsServiceImpl(profile, transformer, persistService);
    }

    @Test
    public void createEmptyMetafile() {
        Metafile emptyMetafile = vufsService.createEmptyMetafile();
        assertNotNull(emptyMetafile);
        assertNotNull(emptyMetafile.getFileUuid());
        assertNotNull(emptyMetafile.getVersion());
    }

    @Test
    public void getMetafileByUuid() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.HIGH);
        String metafileUUid = newMetafile.getFileUuid();
        vufsService.addMetafileToMetatable(newMetafile);
        Metafile result = vufsService.getMetafileByUuid(metafileUUid);
        assertEquals(result.getFileUuid(), newMetafile.getFileUuid());
    }

    @Test
    public void addBlockByUuidToMetafile() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        vufsService.addMetafileToMetatable(newMetafile);
        String newBlockUuid = UUID.randomUUID().toString();
        Metafile withNewBlock = vufsService.addBlockByUuidToMetafile(newBlockUuid, newMetafile);
        assertTrue(withNewBlock.getBlockUuidList().contains(newBlockUuid));
    }

    @Test
    public void removeBlockByUuidFromMetafile() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        vufsService.addMetafileToMetatable(newMetafile);
        String newBlockUuid = UUID.randomUUID().toString();
        Metafile withNewBlock = vufsService.addBlockByUuidToMetafile(newBlockUuid, newMetafile);
        assertEquals(withNewBlock.getBlockUuidList().size(), 1);
        Metafile withRemove = vufsService.removeBlockByUuidFromMetafile(newBlockUuid, withNewBlock);
        assertFalse(withRemove.getBlockUuidList().contains(newBlockUuid));
    }

    @Test
    public void addMetafileToMetatable() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.HIGH);
        boolean isAdded = vufsService.addMetafileToMetatable(newMetafile);
        assertTrue(isAdded);
    }

    @Test
    public void removeMetafileFromMetatable() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.HIGH);
        vufsService.addMetafileToMetatable(newMetafile);
        boolean isRemoved = vufsService.removeMetafileFromMetatable(newMetafile);
        assertTrue(isRemoved);
    }

    @Test
    public void getAllocationByBlockUuid() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.HIGH);
        vufsService.addMetafileToMetatable(newMetafile);
        String newBlockUuid = UUID.randomUUID().toString();
        vufsService.addBlockByUuidToMetafile(newBlockUuid, newMetafile);
        Set<String> allocationSet = vufsService.getAllocationByBlockUuid(newBlockUuid);
        assertNotNull(allocationSet);
        assertTrue(allocationSet.isEmpty());
    }

    @Test
    public void setAllocationForBlockByUuid() {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.HIGH);
        vufsService.addMetafileToMetatable(newMetafile);
        String newBlockUuid = UUID.randomUUID().toString();
        vufsService.addBlockByUuidToMetafile(newBlockUuid, newMetafile);
        Set<String> allocationSet = new HashSet<>(5);
        for (int i = 0; i < 5; i++) {
            allocationSet.add(UUID.randomUUID().toString());
        }
        vufsService.setAllocationForBlockByUuid(newBlockUuid, allocationSet);
        Set<String> resultAllocationSet = vufsService.getAllocationByBlockUuid(newBlockUuid);
        assertFalse(resultAllocationSet.isEmpty());
        assertEquals(resultAllocationSet.size(), 5);
    }

    @Test
    public void addMetafileAsChildToParent() {
        Metafile parentMetafile = vufsService.createEmptyMetafile();
        String parentMetafileUuid = parentMetafile.getFileUuid();
        Metafile childMetafile = vufsService.createEmptyMetafile();
        String childMetafileUuid = childMetafile.getFileUuid();
        assertEquals(parentMetafile.getChildFileUuidSet().size(), 0);
        assertEquals(childMetafile.getChildFileUuidSet().size(), 0);
        vufsService.addMetafileToMetatable(parentMetafile);

        boolean isAdded = vufsService.addMetafileAsChildToParent(childMetafile, parentMetafileUuid);
        assertTrue(isAdded);
        assertEquals(parentMetafile.getChildFileUuidSet().size(), 1);
        assertTrue(parentMetafile.getChildFileUuidSet().contains(childMetafileUuid));
    }

    @Test
    public void removeMetafileFromParent() {
        Metafile parentMetafile = vufsService.createEmptyMetafile();
        String parentMetafileUuid = parentMetafile.getFileUuid();
        Metafile childMetafile = vufsService.createEmptyMetafile();
        String childMetafileUuid = childMetafile.getFileUuid();
        vufsService.addMetafileToMetatable(parentMetafile);
        vufsService.addMetafileAsChildToParent(childMetafile, parentMetafileUuid);

        boolean isRemoved = vufsService
                .removeMetafileFromParent(childMetafileUuid, parentMetafileUuid);
        assertTrue(isRemoved);
        assertEquals(parentMetafile.getChildFileUuidSet().size(), 0);
    }

    @Test
    public void getAllocationStrategyByMetafileUuid() throws MetafileDoesNotExistException {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.UNION);
        String metafileUUid = newMetafile.getFileUuid();
        vufsService.addMetafileToMetatable(newMetafile);
        AllocationStrategy result = vufsService.getAllocationStrategyByMetafileUuid(metafileUUid);
        assertEquals(AllocationStrategy.UNION, result);
    }

    @Test(expected = MetafileDoesNotExistException.class)
    public void getAllocationStrategyByMetafileUuidWithException()
            throws MetafileDoesNotExistException {
        //here throw exception
        AllocationStrategy result = vufsService
                .getAllocationStrategyByMetafileUuid(UUID.randomUUID().toString());
    }

    @Test
    public void setAllocationStrategyByMetafileUuid() throws MetafileDoesNotExistException {
        Metafile newMetafile = vufsService.createEmptyMetafile();
        newMetafile.setAllocationStrategy(AllocationStrategy.UNION);
        String metafileUUid = newMetafile.getFileUuid();
        vufsService.addMetafileToMetatable(newMetafile);
        vufsService.setAllocationStrategyByMetafileUuid(metafileUUid, AllocationStrategy.HIGH);

        Metafile changedMetafile = vufsService.getMetafileByUuid(metafileUUid);
        assertEquals(AllocationStrategy.HIGH, changedMetafile.getAllocationStrategy());
    }

    @Test(expected = MetafileDoesNotExistException.class)
    public void setAllocationStrategyByMetafileUuidWithException()
            throws MetafileDoesNotExistException {
        //here throw exception
        vufsService.setAllocationStrategyByMetafileUuid(UUID.randomUUID().toString(),
                AllocationStrategy.HIGH);
    }

    @After
    public void destroy() {
        vufsService = null;
    }
}
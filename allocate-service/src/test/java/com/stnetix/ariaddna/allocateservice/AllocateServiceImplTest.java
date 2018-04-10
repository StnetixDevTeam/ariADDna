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

package com.stnetix.ariaddna.allocateservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stnetix.ariaddna.allocateservice.exception.AllocateServiceException;
import com.stnetix.ariaddna.allocateservice.exception.AvailableSpaceNotExistException;
import com.stnetix.ariaddna.allocateservice.exception.BlockDoesNotExistInMetafileException;
import com.stnetix.ariaddna.allocateservice.service.AllocateServiceImpl;
import com.stnetix.ariaddna.allocateservice.stub.ResoucePolicyServiceStub;
import com.stnetix.ariaddna.allocateservice.stub.VufsServiceStub;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;
import com.stnetix.ariaddna.rps.service.IResourcePolicyService;
import com.stnetix.ariaddna.vufs.bo.Block;
import com.stnetix.ariaddna.vufs.service.IVufsService;

public class AllocateServiceImplTest {

    private AllocateServiceImpl service;

    @Before
    public void setUp() throws Exception {
        IResourcePolicyService rpService = new ResoucePolicyServiceStub();
        IVufsService vufsService = new VufsServiceStub();
        service = new AllocateServiceImpl();
        service.setResourcePolicyService(rpService);
        service.setVufsService(vufsService);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void getLocationForNewBlock()
            throws AvailableSpaceNotExistException, BlockDoesNotExistInMetafileException {
        Block block = getNewBlockBySize(5000000L);
        Set<String> newLocation = service.getLocationForNewBlock(block);

        assertNotNull(newLocation);
        assertEquals(3, newLocation.size());
    }

    @Test(expected = AvailableSpaceNotExistException.class)
    public void getLocationForNewBlockThrowException()
            throws AvailableSpaceNotExistException, BlockDoesNotExistInMetafileException {
        Block block = getNewBlockBySize(6000000L);
        //here throw exception
        Set<String> newLocation = service.getLocationForNewBlock(block);

    }

    @Test
    public void getLocationForExistBlockByUuid() throws AllocateServiceException {
        Set<String> locations = service
                .getLocationForExistBlockByUuid(UUID.randomUUID().toString());
        assertNotNull(locations);
        assertEquals(3, locations.size());
    }

    private Block getNewBlockBySize(Long size) {
        byte[] payload = new byte[Math.toIntExact(size)];
        for (int i = 0; i < size; i++) {
            payload[i] = (byte) (i % 127);
        }
        return new Block(MavenUtil.getCurrentVersion(), 0L, UUID.randomUUID().toString(),
                payload, System.currentTimeMillis(), size);
    }
}
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

package com.stnetix.ariaddna.rps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stnetix.ariaddna.rps.exception.CloudDoesNotExistException;
import com.stnetix.ariaddna.rps.service.IResourcePolicyService;
import com.stnetix.ariaddna.rps.service.ResourcePolicyServiceImpl;

public class ResourcePolicyServiceImplTest {

    private IResourcePolicyService service;
    private String[] cloudUuids;

    @Before
    public void setUp() throws Exception {
        ProfileStub profile = new ProfileStub();
        cloudUuids = profile.getCloudUuids();
        service = new ResourcePolicyServiceImpl(profile);
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

    @Test
    public void getAvailableCloudSpace() {
        Map<String, Long> availableCloudSpase = service.getAvailableCloudSpace();
        assertNotNull(availableCloudSpase);
        assertEquals(cloudUuids.length, availableCloudSpase.size());
        for (String cloudUuid : cloudUuids) {
            assertTrue(availableCloudSpase.get(cloudUuid) != null);
        }
    }

    @Test
    public void getMaxAvailableCloudSpace() {
        Map<String, Long> maxAvailableCloudSpase = service.getMaxAvailableCloudSpace();
        assertNotNull(maxAvailableCloudSpase);
        assertEquals(cloudUuids.length, maxAvailableCloudSpase.size());
        for (String cloudUuid : cloudUuids) {
            assertTrue(maxAvailableCloudSpase.get(cloudUuid).equals(Long.MAX_VALUE));
        }
    }

    @Test
    public void updateAvailableSpace() throws CloudDoesNotExistException {
        Long newAvailableSpace = 999L;
        service.updateAvailableSpace(cloudUuids[1], newAvailableSpace);
        Map<String, Long> availableCloudSpase = service.getAvailableCloudSpace();
        assertEquals(newAvailableSpace, availableCloudSpase.get(cloudUuids[1]));
    }

    @Test(expected = CloudDoesNotExistException.class)
    public void updateAvailableSpaceWithException() throws CloudDoesNotExistException {
        Long newAvailableSpace = 999L;
        //here throws exception
        service.updateAvailableSpace(UUID.randomUUID().toString(), newAvailableSpace);

    }
}
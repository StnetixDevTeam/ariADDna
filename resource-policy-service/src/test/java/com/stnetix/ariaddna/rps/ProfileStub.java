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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO;
import com.stnetix.ariaddna.userservice.CloudAvailableSpace;
import com.stnetix.ariaddna.userservice.IProfile;

public class ProfileStub implements IProfile {

    private static final Random randomGen = new Random(System.currentTimeMillis());
    private final String[] cloudUuids = { UUID.randomUUID().toString(),
            UUID.randomUUID().toString(), UUID.randomUUID().toString() };

    public String[] getCloudUuids() {
        return cloudUuids;
    }

    @Override

    public Set<MetatableDTO> getMetatables() {
        return null;
    }

    @Override
    public MetatableDTO getCurrentMasterTable() {
        return null;
    }

    @Override
    public CloudAvailableSpace getCloudAvailableSpace() {
        CloudAvailableSpace res = new CloudAvailableSpace();
        Map<String, Long> resources = new HashMap<>(3);
        Map<String, Long> maxResources = new HashMap<>(3);

        for (int i = 0; i < 3; i++) {
            resources.put(cloudUuids[i], randomGen.nextLong());
            maxResources.put(cloudUuids[i], Long.MAX_VALUE);
        }
        res.setMaxAvailableCloudSpace(maxResources);
        res.setAvailableCloudSpace(resources);
        return res;
    }
}

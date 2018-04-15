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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.stnetix.ariaddna.rps.exception.CloudDoesNotExistException;
import com.stnetix.ariaddna.rps.service.IResourcePolicyService;

public class ResoucePolicyServiceStub implements IResourcePolicyService {

    @Override
    public Map<String, Long> getAvailableCloudSpace() {
        Map<String, Long> availableCloudSpace = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            availableCloudSpace.put(UUID.randomUUID().toString(), 5242880L * (i + 1));
        }
        return availableCloudSpace;
    }

    @Override
    public Map<String, Long> getMaxAvailableCloudSpace() {
        return null;
    }

    @Override
    public void updateAvailableSpace(String cloudUuid, Long availableSpace)
            throws CloudDoesNotExistException {

    }
}

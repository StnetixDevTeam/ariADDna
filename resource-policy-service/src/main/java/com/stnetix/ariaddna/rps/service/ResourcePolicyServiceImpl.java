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

package com.stnetix.ariaddna.rps.service;

import java.util.Map;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stnetix.ariaddna.rps.exception.CloudDoesNotExistException;
import com.stnetix.ariaddna.userservice.CloudAvailableSpace;
import com.stnetix.ariaddna.userservice.IProfile;

@Service
@Scope(value = "session")
public class ResourcePolicyServiceImpl implements IResourcePolicyService {

    private CloudAvailableSpace cloudAvailableSpace;

    @Autowired
    public ResourcePolicyServiceImpl(IProfile profile) {
        cloudAvailableSpace = profile.getCloudAvailableSpace();
    }

    @Override
    public Map<String, Long> getAvailableCloudSpace() {
        return cloudAvailableSpace.getAvailableCloudSpace();
    }

    @Override
    public Map<String, Long> getMaxAvailableCloudSpace() {
        return cloudAvailableSpace.getMaxAvailableCloudSpace();
    }

    @Override
    public void updateAvailableSpace(String cloudUuid, Long availableSpace)
            throws CloudDoesNotExistException {
        Map<String, Long> existAvailableSpace = cloudAvailableSpace.getAvailableCloudSpace();
        if (!existAvailableSpace.containsKey(cloudUuid)) {
            throw new CloudDoesNotExistException(
                    "Cloud whith uuid:" + cloudUuid + "does not exist.");
        }
        existAvailableSpace.put(cloudUuid, availableSpace);
        cloudAvailableSpace.setAvailableCloudSpace(existAvailableSpace);
    }

    @PreDestroy
    public void detach() {
        // TODO: 06.04.2018 Add persistence logic of CloudAvailableSpace.class instace
    }
}

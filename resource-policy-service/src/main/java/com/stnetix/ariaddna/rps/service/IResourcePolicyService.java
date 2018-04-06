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

import com.stnetix.ariaddna.rps.exception.CloudDoesNotExistException;

/**
 * This interface responsible for the work with available cloud space.
 * Methods of this interface get max and available or update available
 * clouds space. Clouds represents as uuid of cloud with type {@link String}.
 * Space as value with type {@link Long}.
 * */
public interface IResourcePolicyService {

    /**
     * This method return available cloud space in
     * collection of type {@link java.util.HashMap}
     * in which key is Cloud uuid with type {@link String},
     * value is number of available bytes with type {@link Long}.
     * @return collection of HashMap<String, Long>
     * */
    Map<String, Long> getAvailableCloudSpace();

    /**
     * This method return maximum of available cloud space in
     * collection of type {@link java.util.HashMap}
     * in which key is Cloud uuid with type {@link String},
     * value is number of available bytes with type {@link Long}.
     * @return collection of HashMap<String, Long>
     * */
    Map<String, Long> getMaxAvailableCloudSpace();

    /**
     * Method update available cloud space
     * @param cloudUuid uuid of cloud, type {@link String}
     * @param availableSpace new available cloud space, type {@link Long}
     * throws {@link CloudDoesNotExistException} if <cloudUuid> does not exist
     * in external collection.
     * */
    void updateAvailableSpace(String cloudUuid, Long availableSpace) throws
            CloudDoesNotExistException;
}

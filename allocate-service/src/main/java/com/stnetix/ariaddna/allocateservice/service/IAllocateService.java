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

package com.stnetix.ariaddna.allocateservice.service;

import java.util.Set;


import com.stnetix.ariaddna.allocateservice.exception.AllocateServiceException;
import com.stnetix.ariaddna.allocateservice.exception.AvailableSpaceNotExistException;
import com.stnetix.ariaddna.allocateservice.exception.BlockDoesNotExistInMetafileException;
import com.stnetix.ariaddna.vufs.bo.Block;

/**
 * This interface contain methods of calculation allocation for new block,
 * or return allocation of exist block.
 * */
public interface IAllocateService {

    /**
     * This method return collection with type {@link java.util.HashSet}
     * which contains uuid of cloud, where this {@link Block} will saved.
     * @param newBlock new object of {@link Block}.
     * @return collection of cloud uuid.
     * */
    Set<String> getLocationForNewBlock(Block newBlock)
            throws BlockDoesNotExistInMetafileException, AvailableSpaceNotExistException;

    /**
     * This method return collection with type {@link java.util.HashSet}
     * which contains uuid of cloud, where block with uuid saved.
     * @param blockUuid uuid of {@link Block}.
     * @return collection of cloud uuid.
     * */
    Set<String> getLocationForExistBlockByUuid(String blockUuid) throws AllocateServiceException;

    /**
     * This method set the new allocation for block in VUFS.
     * @param allocations {@link Set} of cloud uuid.
     * @param blockUuid   uuid of {@link Block}.
     * @throws BlockDoesNotExistInMetafileException if block not exist in current snapshot VUFS.
     * */
    void setLocationForBlock(Set<String> allocations, String blockUuid)
            throws BlockDoesNotExistInMetafileException;
}

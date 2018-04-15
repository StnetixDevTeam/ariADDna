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

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stnetix.ariaddna.allocateservice.exception.AllocateServiceException;
import com.stnetix.ariaddna.allocateservice.exception.AvailableSpaceNotExistException;
import com.stnetix.ariaddna.allocateservice.exception.BlockDoesNotExistInMetafileException;
import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.rps.service.IResourcePolicyService;
import com.stnetix.ariaddna.vufs.bo.Block;
import com.stnetix.ariaddna.vufs.exception.BlockNotExistInMetatableException;
import com.stnetix.ariaddna.vufs.exception.MetafileDoesNotExistException;
import com.stnetix.ariaddna.vufs.service.IVufsService;

@Service
@Scope(value = "session")
public class AllocateServiceImpl implements IAllocateService {

    private IResourcePolicyService resourcePolicyService;

    private IVufsService vufsService;

    public AllocateServiceImpl() {

    }

    @Autowired
    public void setResourcePolicyService(IResourcePolicyService resourcePolicyService) {
        this.resourcePolicyService = resourcePolicyService;
    }

    @Autowired
    public void setVufsService(IVufsService vufsService) {
        this.vufsService = vufsService;
    }

    @Override
    public Set<String> getLocationForNewBlock(Block newBlock)
            throws BlockDoesNotExistInMetafileException, AvailableSpaceNotExistException {
        Map<String, Long> availableSpace = resourcePolicyService.getAvailableCloudSpace();
        AllocationStrategy allocationStrategy = null;
        try {
            allocationStrategy = vufsService
                    .getAllocationStrategyByMetafileUuid(newBlock.getFileUuid());
        } catch (MetafileDoesNotExistException e) {
            String message = MessageFormat.format(
                    "Block with uuid: {0}, with fileUuid: {1}, does not exist in Metafile",
                    newBlock.getBlockUuid(), newBlock.getFileUuid());
            throw new BlockDoesNotExistInMetafileException(message, e);
        }
        return calculateLocation(availableSpace, allocationStrategy, newBlock.getSize());
    }

    @Override
    public Set<String> getLocationForExistBlockByUuid(String blockUuid)
            throws AllocateServiceException {
        Set<String> locations = null;
        try {
            locations = vufsService.getAllocationByBlockUuid(blockUuid);
        } catch (BlockNotExistInMetatableException e) {
            throw new AllocateServiceException(
                    MessageFormat
                            .format("Exception by return location for existing block with uuid {0}. See more in inner exception.",
                                    blockUuid), e);
        }
        return locations;
    }

    private Set<String> calculateLocation(Map<String, Long> availableSpace,
            AllocationStrategy allocationStrategy, Long blockSize)
            throws AvailableSpaceNotExistException {
        Set<String> resultLocation = new HashSet<>();
        switch (allocationStrategy) {
        case UNION: {
            StringBuilder exceptionMessage = getExceptionInfo(availableSpace.entrySet());
            for (Map.Entry<String, Long> spaceEntry : availableSpace.entrySet()) {
                if (spaceEntry.getValue().compareTo(blockSize) >= 0) {
                    resultLocation.add(spaceEntry.getKey());
                    return resultLocation;
                }
            }
            throw new AvailableSpaceNotExistException(exceptionMessage.toString());
        }
        case MIRROR: {
            int copyCount = 0;
            StringBuilder exceptionMessage = getExceptionInfo(availableSpace.entrySet());
            for (Map.Entry<String, Long> spaceEntry : availableSpace.entrySet()) {
                if (spaceEntry.getValue().compareTo(blockSize) >= 0) {
                    resultLocation.add(spaceEntry.getKey());
                    copyCount++;
                    if (copyCount == 2) {
                        return resultLocation;
                    }
                }
            }
            throw new AvailableSpaceNotExistException(exceptionMessage.toString());
        }
        case HA: {
            int copyCount = 0;
            StringBuilder exceptionMessage = getExceptionInfo(availableSpace.entrySet());
            for (Map.Entry<String, Long> spaceEntry : availableSpace.entrySet()) {
                if (spaceEntry.getValue().compareTo(blockSize) >= 0) {
                    resultLocation.add(spaceEntry.getKey());
                    copyCount++;
                    if (copyCount == 3) {
                        return resultLocation;
                    }
                }
            }
            throw new AvailableSpaceNotExistException(exceptionMessage.toString());
        }
        default: {
            break;
        }
        }
        return resultLocation;
    }

    private StringBuilder getExceptionInfo(Set<Map.Entry<String, Long>> entries) {
        StringBuilder exceptionMessage = new StringBuilder();
        for (Map.Entry<String, Long> spaceEntry : entries) {
            exceptionMessage.append("Cloud uuid: ")
                    .append(spaceEntry.getKey())
                    .append(" available space: ")
                    .append(spaceEntry.getValue())
                    .append("; ");
        }
        return exceptionMessage;
    }
}

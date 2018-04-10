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

import java.text.MessageFormat;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;
import com.stnetix.ariaddna.persistence.services.IMetatableService;
import com.stnetix.ariaddna.userservice.IProfile;
import com.stnetix.ariaddna.vufs.bo.Metafile;
import com.stnetix.ariaddna.vufs.bo.Metatable;
import com.stnetix.ariaddna.vufs.exception.BlockNotExistInMetatableException;
import com.stnetix.ariaddna.vufs.exception.MetafileDoesNotExistException;
import com.stnetix.ariaddna.vufs.transformers.MetatableTransformer;

/**
 * Created by vasap87 on 12.03.18.
 */
@Service
@Scope(value = "session")
public class VufsServiceImpl implements IVufsService {

    private Metatable currentMetatable;

    private IProfile profile;

    private MetatableTransformer metatableTransformer;

    private IMetatableService persistingService;

    @Autowired
    public VufsServiceImpl(IProfile profile,
            MetatableTransformer metatableTransformer,
            IMetatableService persistingService) {
        this.profile = profile;
        this.metatableTransformer = metatableTransformer;
        this.persistingService = persistingService;
        currentMetatable = metatableTransformer
                .metatableDTOToBO(profile.getCurrentMasterTable());
    }

    @Override
    public Metafile createEmptyMetafile() {
        return new Metafile(MavenUtil.getCurrentVersion(), UUID.randomUUID().toString(), null);
    }

    @Override
    public Metafile getMetafileByUuid(String fileUuid) throws MetafileDoesNotExistException {
        for (Metafile metafile : currentMetatable.getMetafileSet()) {
            if (metafile.getFileUuid().equalsIgnoreCase(fileUuid)) {
                return metafile;
            }
        }
        throw new MetafileDoesNotExistException(getExceptionInfo(currentMetatable, fileUuid,
                "getMetafileByUuid"));
    }

    @Override
    public Metafile addBlockByUuidToMetafile(String blockUuid, Metafile metafile) {
        metafile.addBlockUuid(blockUuid);
        return metafile;
    }

    @Override
    public Metafile removeBlockByUuidFromMetafile(String blockUuid, Metafile metafile) {
        metafile.removeBlockUuid(blockUuid);
        return metafile;
    }

    @Override
    public boolean addMetafileToMetatable(Metafile metafile) {
        return currentMetatable.addMetafile(metafile);
    }

    @Override
    public boolean removeMetafileFromMetatable(Metafile metafile) {
        return currentMetatable.removeMetafile(metafile);
    }

    @Override
    public Set<String> getAllocationByBlockUuid(String blockUuid)
            throws BlockNotExistInMetatableException {
        for (Metafile metafile : currentMetatable.getMetafileSet()) {
            if (metafile.getBlockUuidList().contains(blockUuid)) {
                return metafile.getBlockAllocation(blockUuid);
            }
        }
        throw new BlockNotExistInMetatableException(MessageFormat.format(
                "Block with uuid: {0}, does not exist in currentMetatable with uuid: {1}, with type: {2}",
                blockUuid, currentMetatable.getUuid(), currentMetatable.getType()));
    }

    @Override
    public void setAllocationForBlockByUuid(String blockUuid, Set<String> allocationSet) {
        for (Metafile metafile : currentMetatable.getMetafileSet()) {
            if (metafile.getBlockUuidList().contains(blockUuid)) {
                for (String cloud : allocationSet) {
                    metafile.addBlockAllocation(blockUuid, cloud);
                }
                break;
            }
        }
    }

    @Override
    public boolean addMetafileAsChildToParent(Metafile childMetafile,
            String parentMetafileUuid) {
        boolean isAdded = false;
        //add Metafile to set of metafile in Metatable.
        if (currentMetatable.addMetafile(childMetafile)) {
            for (Metafile metafile : currentMetatable.getMetafileSet()) {
                //parent metafile exist and its a directory.
                if (metafile.getFileUuid().equalsIgnoreCase(parentMetafileUuid)) {
                    isAdded = metafile.addChildFileUUid(childMetafile.getFileUuid());
                }
            }
        }
        if (isAdded) {
            childMetafile.setParentFileUuid(parentMetafileUuid);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeMetafileFromParent(String childMetafileUuid,
            String parentMetafileUuid) {
        boolean isRemovedAsChild = false;
        // Remove child metafile uuid from list of childMetafileUuidList if Metafile with parentMetafileUuid.
        for (Metafile metafile : currentMetatable.getMetafileSet()) {
            if (metafile.getFileUuid().equalsIgnoreCase(parentMetafileUuid)) {
                isRemovedAsChild = metafile.removeChildFileUuid(childMetafileUuid);
                break;
            }
        }
        // If was removed from parent, remove metafile form metatable.
        return isRemovedAsChild && currentMetatable.removeMetafileByUuid(childMetafileUuid);
    }

    @Override
    public AllocationStrategy getAllocationStrategyByMetafileUuid(String metafileUuid)
            throws MetafileDoesNotExistException {
        for (Metafile metafile : currentMetatable.getMetafileSet()) {
            if (metafile.getFileUuid().equalsIgnoreCase(metafileUuid)) {
                return metafile.getAllocationStrategy();
            }
        }
        throw new MetafileDoesNotExistException(getExceptionInfo(currentMetatable, metafileUuid,
                "getAllocationStrategyByMetafileUuid"));
    }

    @Override
    public void setAllocationStrategyByMetafileUuid(String metafileUuid,
            AllocationStrategy allocationStrategy) throws MetafileDoesNotExistException {
        Metafile requestedMetafile = null;
        for (Metafile metafile : currentMetatable.getMetafileSet()) {
            if (metafile.getFileUuid().equalsIgnoreCase(metafileUuid)) {
                requestedMetafile = metafile;
                break;
            }
        }
        if (requestedMetafile != null) {
            requestedMetafile.setAllocationStrategy(allocationStrategy);
        } else {
            throw new MetafileDoesNotExistException(getExceptionInfo(currentMetatable, metafileUuid,
                    "setAllocationStrategyByMetafileUuid"));
        }
    }

    private String getExceptionInfo(Metatable currentMetatable, String metafileUuid,
            String methodName) {
        return MessageFormat
                .format("Method: {0}. In Metatable with uuid: {1}, version: {2}, type: {3}, Metafile with uuid: {4} does not exist.",
                        methodName, currentMetatable.getUuid(), currentMetatable.getVersion(),
                        currentMetatable.getType(), metafileUuid);
    }

    /**
     * This method called before destroy this bean. Also as current users session will die.
     * */
    @PreDestroy
    public void persistChanges() {
        persistingService.saveMetatable(metatableTransformer.metatableBOToDTO(currentMetatable));
    }

}

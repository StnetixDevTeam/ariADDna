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

import java.util.Set;

import com.stnetix.ariaddna.commonutils.dto.vufs.AllocationStrategy;
import com.stnetix.ariaddna.vufs.bo.Metafile;
import com.stnetix.ariaddna.vufs.exception.BlockDoesNotExistInMetafileInCurrentMasterMetatableException;
import com.stnetix.ariaddna.vufs.exception.MetafileDoesNotExistException;

/**
 * Created by vasap87 on 20.02.18.
 */
public interface IVufsService {

    /**
     * Method create empty Metafile in current master Metatable.
     * @return created Metafile.
     * */
    Metafile createEmptyMetafile();

    /**
     * Metod return Metafile object by Metafile uuid.
     * @param fileUuid Metafile uuid.
     * @return Metafile object.
     * */
    Metafile getMetafileByUuid(String fileUuid) throws MetafileDoesNotExistException;

    /**
     * Method add to Metafile`s block uuid collections new block.
     * @param blockUuid new block uuid. Type String.
     * @param metafile Metafile object.
     * @return changed Metafile.
     * */
    Metafile addBlockByUuidToMetafile(String blockUuid, Metafile metafile);

    /**
     * Method remove from Metafile`s block uuid collections required block.
     * @param blockUuid block uuid. Type String.
     * @param metafile Metafile object.
     * @return changed Metafile.
     * */
    Metafile removeBlockByUuidFromMetafile(String blockUuid, Metafile metafile);

    /**
     * Method add Metafile to current Metatable.
     * @param metafile Metafile object to add.
     * @return true if current Metatable does not have the specified Metatable.
     * */
    boolean addMetafileToMetatable(Metafile metafile);

    /**
     * Method remove Metafile from current Metatable.
     * @param metafile Metafile object to remove.
     * @return true if current Metatable has the specified Metatable.
     * */
    boolean removeMetafileFromMetatable(Metafile metafile);

    /**
     * Method return set of cloud uuid where specified block is placed.
     * @param blockUuid uuid of specified block.
     * @return set of cloud uuid.
     * */
    Set<String> getAllocationByBlockUuid(String blockUuid)
            throws BlockDoesNotExistInMetafileInCurrentMasterMetatableException;

    /**
     * Method set to block uuid specified set of cloud uuid.
     * @param blockUuid uuid of existing block.
     * @param allocationSet set of cloud uuid.
     * */
    void setAllocationForBlockByUuid(String blockUuid, Set<String> allocationSet);

    /**
     * Method add Metafile as child to parent Metafile.
     * @param childMetafile object of child Metafile.
     * @param parentMetafileUuid uuid of existing parent Metafile.
     * @return true if Metafile was added.
     * */
    boolean addMetafileAsChildToParent(Metafile childMetafile, String parentMetafileUuid);

    /**
     * Method remove Metafile from child of parent Metafile.
     * @param childMetafileUuid uuid of child Metafile.
     * @param parentMetafileUuid uuid of parent Metafile.
     * @return true if Metafile was removed.
     * */
    boolean removeMetafileFromParent(String childMetafileUuid, String parentMetafileUuid);

    /**
     * Method return the current allocation strategy by uuid of Metafile.
     * @param metafileUuid uuid of Metafile.
     * @return AllocationStrategy of requested Metafile.
     * @throws MetafileDoesNotExistException if metafile does not exist in current Metatable of current context.
     * */
    AllocationStrategy getAllocationStrategyByMetafileUuid(String metafileUuid)
            throws MetafileDoesNotExistException;

    /**
     * Method set allocation strategy by uuid of Metafile.
     * @param metafileUuid uuid of Metafile.
     * @param allocationStrategy new AllocationStrategy of Metafile.
     * */
    void setAllocationStrategyByMetafileUuid(String metafileUuid,
            AllocationStrategy allocationStrategy) throws MetafileDoesNotExistException;
}

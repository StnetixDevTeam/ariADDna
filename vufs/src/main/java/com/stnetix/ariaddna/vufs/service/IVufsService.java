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

import com.stnetix.ariaddna.vufs.businessobjects.Metafile;

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
    Metafile getMetafileByUuid(String fileUuid);

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


    boolean addMetafileToMetatable(Metafile metafile);

    boolean removeMetafileFromMetatable(Metafile metafile);

    Set<String> getAllocationByBlockUuid(String blockUuid);

    void setAllocationForBlockByUuid(String blockUuid, Set<String> allocationSet);
}

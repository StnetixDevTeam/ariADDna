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

package com.stnetix.ariaddna.blockmanipulation.service;

import com.stnetix.ariaddna.blockmanipulation.exception.BlockDoesNotExistException;
import com.stnetix.ariaddna.blockmanipulation.exception.MetafileIsFolderException;
import com.stnetix.ariaddna.vufs.bo.Metafile;

public interface IBlockManipulationService {

    /**
     * Method return Block uuid from Metafile. If Metafile represent a folder this method
     * throw exception {@link MetafileIsFolderException}. For other metafile objects, in first request
     * method return uuid for first block, in second request return request for second block
     * and so on. If all block uuids was returned, this method throws {@link BlockDoesNotExistException}.
     * @param metafile Metafile object.
     * @return uuid of next Block uuid.
     * @throws {@link MetafileIsFolderException}, {@link BlockDoesNotExistException}
     * */
    String getNextBlockUuid(Metafile metafile) throws MetafileIsFolderException,
            BlockDoesNotExistException;

    /**
     * Method return Block uuid by number from Metafile. If metafile is folder, method
     * throws {@link MetafileIsFolderException}.
     * if Metafile doesn`t exist block with external number, throws {@link BlockDoesNotExistException}.
     * @param metafile Metafil object.
     * @param blockNumber number of requested block.
     * @return uuid of block with requsted number.
     * @throws {@link BlockDoesNotExistException}, {@link MetafileIsFolderException}
     * */
    String getBlockUuidByNumber(Metafile metafile, int blockNumber)
            throws MetafileIsFolderException, BlockDoesNotExistException;
}

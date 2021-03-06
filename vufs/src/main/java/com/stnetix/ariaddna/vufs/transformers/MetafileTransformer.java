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

package com.stnetix.ariaddna.vufs.transformers;

import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.commonutils.dto.vufs.MetafileDTO;
import com.stnetix.ariaddna.vufs.bo.Metafile;

/**
 * Class to transform {@link com.stnetix.ariaddna.vufs.bo.Metafile} to
 * {@link com.stnetix.ariaddna.commonutils.dto.vufs.MetafileDTO} and vice versa.
 */
@Component
public class MetafileTransformer {

    public MetafileDTO metafileBOToDTO(Metafile metafile) {
        MetafileDTO metafileDTO = new MetafileDTO();
        metafileDTO.setVersion(metafile.getVersion());
        metafileDTO.setAllocationStrategy(metafile.getAllocationStrategy());
        metafileDTO.setFileUuid(metafile.getFileUuid());
        metafileDTO.setParentFileUuid(metafile.getParentFileUuid());
        metafileDTO.setBlockAllocateMap(metafile.getBlockAllocateMap());
        metafileDTO.setBlockUuidList(metafile.getBlockUuidList());
        metafileDTO.setChildFileUuidSet(metafile.getChildFileUuidSet());
        metafileDTO.setProperties(metafile.getProperties());
        return metafileDTO;
    }

    public Metafile metafileDTOToBO(MetafileDTO metafileDTO) {
        Metafile metafile = new Metafile("", "", "");
        metafile.setAllocationStrategy(metafileDTO.getAllocationStrategy());
        metafile.setBlockAllocateMap(metafileDTO.getBlockAllocateMap());
        metafile.setProperties(metafileDTO.getProperties());
        metafile.setBlockUuidList(metafileDTO.getBlockUuidList());
        metafile.setChildFileUuidSet(metafileDTO.getChildFileUuidSet());
        return metafile;
    }

}

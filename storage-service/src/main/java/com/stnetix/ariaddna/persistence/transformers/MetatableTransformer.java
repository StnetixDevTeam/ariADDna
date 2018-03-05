/*
 * Copyright (c) 2017 stnetix.com. All Rights Reserved.
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

package com.stnetix.ariaddna.persistence.transformers;

import java.util.HashSet;
import java.util.Set;

import com.stnetix.ariaddna.persistence.entities.vufs.MetafileEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.MetatableEntity;
import com.stnetix.ariaddna.vufs.businessobjects.MetatableType;
import com.stnetix.ariaddna.vufs.dto.MetafileDTO;
import com.stnetix.ariaddna.vufs.dto.MetatableDTO;

/**
 * 22.02.18.
 */
public class MetatableTransformer {
    private MetafileTransformer metafileTransformer;

    public MetatableTransformer() {
        metafileTransformer = new MetafileTransformer();
    }

    public MetatableDTO metafileEntityToDTO(MetatableEntity metatableEntity) {
        MetatableDTO metatableDTO = new MetatableDTO();

        metatableDTO.setUuid(metatableEntity.getUuid() != null ? metatableEntity.getUuid() : null);
        metatableDTO.setType(metatableEntity.getType() != null ?
                metatableEntity.getType() :
                null);

        Set<MetafileDTO> metafileDTOSet = new HashSet<>();
        metatableEntity.getMetafileSet().forEach(metafileEntity -> metafileDTOSet
                .add(metafileTransformer.metafileEntityToDTO(metafileEntity)));
        metatableDTO.setMetafileSet(metafileDTOSet);

        metatableDTO.setLastUpdateTimestamp(metatableEntity.getLastUpdateTimestamp() != null ?
                metatableEntity.getLastUpdateTimestamp() :
                null);
        metatableDTO.setVersion(
                metatableEntity.getVersion() != null ? metatableEntity.getVersion() : null);
        return metatableDTO;
    }

    public MetatableEntity metafileDTOtoEntity(MetatableDTO metatableDTO) {
        MetatableEntity metatableEntity = new MetatableEntity();

        metatableEntity.setUuid(metatableDTO.getUuid() != null ? metatableDTO.getUuid() : null);
        metatableEntity.setType(
                metatableDTO.getType() != null ? metatableDTO.getType() : null);

        Set<MetafileEntity> metafileEntitySet = new HashSet<>();
        metatableDTO.getMetafileSet().forEach(metafileDTO -> metafileEntitySet
                .add(metafileTransformer.metafileDTOtoEntity(metafileDTO)));
        metatableEntity.setMetafileSet(metafileEntitySet);

        metatableEntity.setLastUpdateTimestamp(metatableDTO.getLastUpdateTimestamp() != null ?
                metatableDTO.getLastUpdateTimestamp() : null);
        metatableEntity.setVersion(metatableDTO.getVersion() != null ? metatableDTO.getVersion() :
                null);
        return metatableEntity;
    }
}

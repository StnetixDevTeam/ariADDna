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

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stnetix.ariaddna.commonutils.dto.vufs.MetafileDTO;
import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO;
import com.stnetix.ariaddna.vufs.businessobjects.Metafile;
import com.stnetix.ariaddna.vufs.businessobjects.Metatable;

/**
 * Class to transform {@link com.stnetix.ariaddna.vufs.businessobjects.Metatable} to
 * {@link com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO} and vice versa.
 */
@Component
public class MetatableTransformer {

    private MetafileTransformer metafileTransformer;

    /**
     * Transform business object with type {@link Metatable} to data transfer object
     * with type {@link MetatableDTO}*/
    public MetatableDTO metatableBOToDTO(Metatable metatable) {
        MetatableDTO metatableDTO = new MetatableDTO();
        metatableDTO.setUuid(metatable.getUuid());
        metatableDTO.setVersion(metatable.getVersion());
        metatableDTO.setType(metatable.getType());
        metatableDTO.setLastUpdateTimestamp(metatable.getLastUpdateTimestamp());
        Set<MetafileDTO> metafileSet = new HashSet<>(metatable.getMetafileSet().size());
        metatable.getMetafileSet().forEach(
                metafile -> metafileSet.add(metafileTransformer.metafileBOToDTO(metafile)));
        metatableDTO.setMetafileSet(metafileSet);
        return metatableDTO;
    }

    /**
     * Transform data transfer object with type {@link MetatableDTO} to business object
     * with type {@link Metatable}*/
    public Metatable metatableDTOToBO(MetatableDTO metatableDTO) {
        Metatable metatable = new Metatable(metatableDTO.getType(), metatableDTO.getUuid(),
                metatableDTO.getVersion());
        metatable.setLastUpdateTimestamp(metatableDTO.getLastUpdateTimestamp());
        Set<Metafile> metafileSet = new CopyOnWriteArraySet<>();
        metatableDTO.getMetafileSet().forEach(
                metafileDTO -> metafileSet.add(metafileTransformer.metafileDTOToBO(metafileDTO)));
        metatable.setMetafileSet(metafileSet);
        return metatable;
    }

    @Autowired
    public void setMetafileTransformer(
            MetafileTransformer metafileTransformer) {
        this.metafileTransformer = metafileTransformer;
    }
}

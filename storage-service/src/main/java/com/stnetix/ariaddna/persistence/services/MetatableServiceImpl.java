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

package com.stnetix.ariaddna.persistence.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO;
import com.stnetix.ariaddna.persistence.repositories.MetatableRepository;
import com.stnetix.ariaddna.persistence.transformers.MetatableTransformer;

/**
 * 01.03.18.
 */
@Repository
@Transactional
public class MetatableServiceImpl implements IMetatableService {

    private MetatableRepository repository;
    private MetatableTransformer transformer;

    public MetatableServiceImpl() {
        transformer = new MetatableTransformer();
    }

    @Override
    public MetatableDTO getMetatableMaster(String metatableUuid) {
        return transformer.metafileEntityToDTO(repository.getMetatableEntityMaster(metatableUuid));
    }

    @Override
    public MetatableDTO saveMetatable(MetatableDTO metatable) {
        return transformer
                .metafileEntityToDTO(repository.save(transformer.metafileDTOtoEntity(metatable)));
    }

    @Autowired
    public void setRepository(MetatableRepository repository) {
        this.repository = repository;
    }
}

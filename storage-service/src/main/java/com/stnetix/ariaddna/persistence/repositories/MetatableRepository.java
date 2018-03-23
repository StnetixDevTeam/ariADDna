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

package com.stnetix.ariaddna.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.persistence.entities.vufs.MetatableEntity;

/**
 * 22.02.18.
 */
@Transactional
public interface MetatableRepository extends CrudRepository<MetatableEntity, String> {

    @Query(value = "select m from MetatableEntity m where m.type = "
            + "com.stnetix.ariaddna.commonutils.dto.vufs.MetatableType.MASTER and m.uuid = :metatableUuid")
    MetatableEntity getMetatableEntityMaster(@Param(value = "metatableUuid") String metatableUuid);
}

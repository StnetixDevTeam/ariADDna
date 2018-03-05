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

package com.stnetix.ariaddna.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.persistence.entities.Certificate;

/**
 * Created by alexkotov on 26.04.17.
 */
@Transactional
public interface CertificateRepository extends CrudRepository<Certificate, Long> {

    @Query(value = "select c from Certificate c where c.isActive = true")
    List<Certificate> getActiveCertificates();

    @Query(value = "select c from Certificate c where c.isActive = false")
    List<Certificate> getDisableCertificates();

}

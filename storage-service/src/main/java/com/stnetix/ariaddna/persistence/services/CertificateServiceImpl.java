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

package com.stnetix.ariaddna.persistence.services;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;
import com.stnetix.ariaddna.persistence.entities.Certificate;
import com.stnetix.ariaddna.persistence.repositories.CertificateRepository;
import com.stnetix.ariaddna.persistence.transformers.CertificateTransformer;

/**
 * Created by alexkotov on 26.04.17.
 */
@Repository
@Transactional(readOnly = true)
public class CertificateServiceImpl implements ICertificateService {

    private CertificateTransformer tranformer;
    @Autowired
    private CertificateRepository repository;

    public CertificateServiceImpl() {
        tranformer = Mappers.getMapper(CertificateTransformer.class);
    }

    @Override
    @Transactional
    public CertificateDTO save(CertificateDTO certificateDTO) {
        Certificate certificate = tranformer.certificateDTOToEntity(certificateDTO);
        return tranformer.certificateEntityToDTO(repository.save(certificate));
    }

    @Override
    public void remove(CertificateDTO certificateDTO) {
        Certificate certificate = tranformer.certificateDTOToEntity(certificateDTO);
        repository.delete(certificate);
    }

    @Override
    public List<CertificateDTO> getActiveCertificates() {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        repository.getActiveCertificates().stream().forEach(certificate -> certificateDTOList
                .add(tranformer.certificateEntityToDTO(certificate)));
        return certificateDTOList;
    }

    @Override
    public List<CertificateDTO> getDisableCertificates() {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        repository.getDisableCertificates().stream().forEach(certificate -> certificateDTOList
                .add(tranformer.certificateEntityToDTO(certificate)));
        return certificateDTOList;
    }

    @Override
    public List<CertificateDTO> getAllCertificates() {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        repository.findAll().forEach(certificate -> certificateDTOList
                .add(tranformer.certificateEntityToDTO(certificate)));
        return certificateDTOList;
    }
}

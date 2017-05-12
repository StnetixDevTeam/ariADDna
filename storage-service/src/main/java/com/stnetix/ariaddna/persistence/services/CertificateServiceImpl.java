package com.stnetix.ariaddna.persistence.services;


import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;
import com.stnetix.ariaddna.persistence.entities.Certificate;
import com.stnetix.ariaddna.persistence.repositories.CertificateRepository;
import com.stnetix.ariaddna.persistence.transformers.CertificateTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 26.04.17.
 */
@Repository
@Transactional(readOnly = true)
public class CertificateServiceImpl implements ICertificateService {

    private CertificateTransformer tranformer;
    @Autowired
    private CertificateRepository repository;

    public CertificateServiceImpl(){
        tranformer = Mappers.getMapper(CertificateTransformer.class);
    }

    @Override
    @Transactional
    public CertificateDTO save(CertificateDTO certificateDTO) {
        Certificate certificate = tranformer.certificateDTOToEntity(certificateDTO);
        return tranformer.certificateEntityToDTO(repository.save(certificate));
    }

    @Override
    public List<CertificateDTO> getActiveCertificates() {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        repository.getActiveCertificates().stream().forEach(certificate -> certificateDTOList.add(tranformer.certificateEntityToDTO(certificate)));
        return certificateDTOList;
    }

    @Override
    public List<CertificateDTO> getDisableCertificates() {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        repository.getDisableCertificates().stream().forEach(certificate -> certificateDTOList.add(tranformer.certificateEntityToDTO(certificate)));
        return certificateDTOList;
    }

    @Override
    public List<CertificateDTO> getAllCertificates() {
        List<CertificateDTO> certificateDTOList = new ArrayList<>();
        repository.findAll().forEach(certificate -> certificateDTOList.add(tranformer.certificateEntityToDTO(certificate)));
        return certificateDTOList;
    }
}

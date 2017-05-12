package com.stnetix.ariaddna.persistence.services;



import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;

import java.util.List;

/**
 * Created by alexkotov on 26.04.17.
 */
public interface ICertificateService {
    CertificateDTO save(CertificateDTO certificate);

    List<CertificateDTO> getActiveCertificates();

    List<CertificateDTO> getDisableCertificates();

    List<CertificateDTO> getAllCertificates();
}

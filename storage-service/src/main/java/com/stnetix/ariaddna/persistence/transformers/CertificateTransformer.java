package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;
import com.stnetix.ariaddna.persistence.entities.Certificate;
import org.mapstruct.Mapper;

/**
 * Created by alexkotov on 03.05.17.
 */
@Mapper
public interface CertificateTransformer {
    CertificateDTO certificateEntityToDTO(Certificate certificate);
    Certificate certificateDTOToEntity(CertificateDTO certificateDTO);
}

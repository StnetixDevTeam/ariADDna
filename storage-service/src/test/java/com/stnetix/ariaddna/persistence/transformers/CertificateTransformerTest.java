package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;
import com.stnetix.ariaddna.persistence.entities.Certificate;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 03.05.17.
 */
public class CertificateTransformerTest {
    private CertificateTransformer transformer = Mappers.getMapper(CertificateTransformer.class);
    @Test
    public void certificateEntityToDTOTest() throws Exception {
        Certificate certificate = new Certificate();
        UUID uuid = UUID.randomUUID();
        certificate.setId(1L);
        certificate.setUuid(uuid.toString());
        certificate.setActive(true);

        CertificateDTO certificateDTO = transformer.certificateEntityToDTO(certificate);
        assertNotEquals(certificateDTO, certificate);
        assertEquals(certificate.getUuid(), certificateDTO.getUuid());
        assertEquals(certificate.getId(), certificateDTO.getId());
        assertEquals(certificate.getActive(), certificateDTO.getActive());


    }

    @Test
    public void certificateDTOToEntityTest() throws Exception {
        CertificateDTO certificateDTO = new CertificateDTO();
        UUID uuid = UUID.randomUUID();
        certificateDTO.setId(1L);
        certificateDTO.setUuid(uuid.toString());
        certificateDTO.setActive(true);

        Certificate certificate = transformer.certificateDTOToEntity(certificateDTO);
        assertNotEquals(certificate, certificateDTO);
        assertEquals(certificate.getUuid(), certificateDTO.getUuid());
        assertEquals(certificate.getId(), certificateDTO.getId());
        assertEquals(certificate.getActive(), certificateDTO.getActive());
    }

}
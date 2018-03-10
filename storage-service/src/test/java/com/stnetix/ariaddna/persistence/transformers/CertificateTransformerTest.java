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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.UUID;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import com.stnetix.ariaddna.commonutils.dto.CertificateDTO;
import com.stnetix.ariaddna.persistence.entities.Certificate;

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
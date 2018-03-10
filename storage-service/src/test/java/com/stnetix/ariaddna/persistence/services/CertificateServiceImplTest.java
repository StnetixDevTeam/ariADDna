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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.dto.CertificateDTO;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;

/**
 * Created by alexkotov on 03.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfiguration.class)
//Transactional mark this test to rollback all changes after test
@Transactional
public class CertificateServiceImplTest {

    @Autowired
    private ICertificateService certificateService;

    @Test
    public void saveTest() throws Exception {

        CertificateDTO certificateDTO = new CertificateDTO();
        certificateDTO.setActive(true);
        UUID uuid = UUID.randomUUID();
        certificateDTO.setUuid(uuid.toString());

        CertificateDTO savedCertDTO = certificateService.save(certificateDTO);
        assertNotNull(savedCertDTO);
        assertNotNull(savedCertDTO.getId());

    }

    @Test
    public void getActiveCertificatesTest() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        CertificateDTO certificateDTO1 = new CertificateDTO();
        CertificateDTO certificateDTO2 = new CertificateDTO();
        CertificateDTO certificateDTO3 = new CertificateDTO();
        certificateDTO1.setActive(true);
        certificateDTO3.setActive(true);
        certificateDTO1.setUuid(uuid1.toString());
        certificateDTO2.setUuid(uuid2.toString());
        certificateDTO3.setUuid(uuid3.toString());
        certificateService.save(certificateDTO1);
        certificateService.save(certificateDTO2);
        certificateService.save(certificateDTO3);

        List<CertificateDTO> certificateDTOList = certificateService.getActiveCertificates();
        assertEquals(certificateDTOList.size(), 2);
    }

    @Test
    public void getDisableCertificatesTest() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        CertificateDTO certificateDTO1 = new CertificateDTO();
        CertificateDTO certificateDTO2 = new CertificateDTO();
        CertificateDTO certificateDTO3 = new CertificateDTO();
        certificateDTO1.setActive(true);
        certificateDTO1.setUuid(uuid1.toString());
        certificateDTO2.setUuid(uuid2.toString());
        certificateDTO3.setUuid(uuid3.toString());
        certificateService.save(certificateDTO1);
        certificateService.save(certificateDTO2);
        certificateService.save(certificateDTO3);

        List<CertificateDTO> certificateDTOList = certificateService.getDisableCertificates();
        assertEquals(certificateDTOList.size(), 2);
    }

    @Test
    public void getAllCertificatesTest() throws Exception {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        CertificateDTO certificateDTO1 = new CertificateDTO();
        CertificateDTO certificateDTO2 = new CertificateDTO();
        CertificateDTO certificateDTO3 = new CertificateDTO();
        certificateDTO1.setActive(true);
        certificateDTO1.setUuid(uuid1.toString());
        certificateDTO2.setUuid(uuid2.toString());
        certificateDTO3.setUuid(uuid3.toString());
        certificateService.save(certificateDTO1);
        certificateService.save(certificateDTO2);
        certificateService.save(certificateDTO3);

        List<CertificateDTO> certificateDTOList = certificateService.getAllCertificates();
        assertEquals(certificateDTOList.size(), 3);
    }

}
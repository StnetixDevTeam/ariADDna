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

package com.stnetix.ariaddna.keystore.utils;

import java.util.HashSet;
import java.util.UUID;

import com.stnetix.ariaddna.commonutils.dto.CertificateDTO;

/**
 * Created by localadmin on 25.07.17.
 */
public class PersistHelperStubImpl implements IPersistHelper {

    private char[] pass;
    private HashSet<CertificateDTO> allCertificates;

    public PersistHelperStubImpl() {
        pass = UUID.randomUUID().toString().toCharArray();
        allCertificates = new HashSet<>();
    }

    @Override
    public char[] getPassword() {
        return pass;
    }

    @Override
    public CertificateDTO storeCertificete(CertificateDTO certificate) {
        allCertificates.add(certificate);
        return certificate;
    }

    @Override
    public void deleteCertificate(String alias) {
        CertificateDTO target = getTargetCert(alias);
        if (target != null) {
            allCertificates.remove(target);
        }
    }

    @Override
    public boolean isActiveCertificate(String alias) {
        CertificateDTO target = getTargetCert(alias);
        if (target != null) {
            return target.getActive();
        }
        return false;
    }

    @Override
    public void setCertificateDisable(String alias) {
        CertificateDTO target = getTargetCert(alias);
        if (target != null) {
            target.setActive(false);
        }
    }

    private CertificateDTO getTargetCert(String alias) {
        CertificateDTO target = null;
        for (CertificateDTO cert : allCertificates) {
            if (cert.getUuid().equalsIgnoreCase(alias)) {
                target = cert;
                break;
            }
        }
        return target;
    }
}

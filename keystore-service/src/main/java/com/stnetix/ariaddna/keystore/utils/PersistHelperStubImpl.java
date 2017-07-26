package com.stnetix.ariaddna.keystore.utils;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

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
        if(target!=null){
            allCertificates.remove(target);
        }
    }

    @Override
    public boolean isActiveCertificate(String alias) {
        CertificateDTO target = getTargetCert(alias);
        if(target!=null){
            return target.getActive();
        }
        return false;
    }

    @Override
    public void setCertificateDisable(String alias) {
        CertificateDTO target = getTargetCert(alias);
        if(target!=null){
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

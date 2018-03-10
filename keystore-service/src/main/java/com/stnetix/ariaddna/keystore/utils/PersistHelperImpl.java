package com.stnetix.ariaddna.keystore.utils;

import com.stnetix.ariaddna.commonutils.dto.CertificateDTO;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by alexkotov on 05.06.17.
 */
public class PersistHelperImpl implements IPersistHelper {

    @Autowired
    private IKeyStorePasswordService keyStorePasswordService;

    @Autowired
    private ICertificateService certificateService;

    @Override
    public char[] getPassword(){
        return keyStorePasswordService.getPassword().getPass().toCharArray();
    }

    @Override
    public CertificateDTO storeCertificete(CertificateDTO certificate){
        return certificateService.save(certificate);
    }

    @Override
    public void deleteCertificate(String alias){
       CertificateDTO target = getTargetCert(alias);
        if(target!=null){
            certificateService.remove(target);
        }

    }

    @Override
    public boolean isActiveCertificate(String alias){
        CertificateDTO target = getTargetCert(alias);
        if(target!=null){
            return target.getActive();
        }
        return false;
    }

    @Override
    public void setCertificateDisable(String alias){
        CertificateDTO target = getTargetCert(alias);
        if(target!=null){
            target.setActive(false);
            certificateService.save(target);
        }
    }

    private CertificateDTO getTargetCert(String alias){
        List<CertificateDTO> certList = certificateService.getAllCertificates();
        CertificateDTO target = null;
        for (CertificateDTO cert: certList) {
            if(cert.getUuid().equalsIgnoreCase(alias)) {
                target = cert;
                break;
            }
        }
        return target;
    }

}

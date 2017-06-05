package com.stnetix.ariaddna.keystore.utils;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;
import com.stnetix.ariaddna.persistence.services.ICertificateService;
import com.stnetix.ariaddna.persistence.services.IKeyStorePasswordService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by alexkotov on 05.06.17.
 */
public class PersistHelper {
    private static PersistHelper ourInstance = new PersistHelper();

    public static PersistHelper getInstance() {
        return ourInstance;
    }

    private PersistHelper() {
    }

    @Autowired
    private IKeyStorePasswordService keyStorePasswordService;

    @Autowired
    private ICertificateService certificateService;

    public char[] getPassword(){
        return keyStorePasswordService.getPassword().getPass().toCharArray();
    }

    public CertificateDTO storeCertificete(CertificateDTO certificate){
        return certificateService.save(certificate);
    }

    public void deleteCertificate(String alias){

    }

    public boolean isActiveCertificate(String alias){
        return false;
    }

    public void setCertificateDisable(String alias){

    }

}

package com.stnetix.ariaddna.keystore.utils;

import com.stnetix.ariaddna.commonutils.DTO.CertificateDTO;

/**
 * Created by localadmin on 25.07.17.
 */
public interface IPersistHelper {
    char[] getPassword();
    CertificateDTO storeCertificete(CertificateDTO certificate);
    void deleteCertificate(String alias);
    boolean isActiveCertificate(String alias);
    void setCertificateDisable(String alias);

}

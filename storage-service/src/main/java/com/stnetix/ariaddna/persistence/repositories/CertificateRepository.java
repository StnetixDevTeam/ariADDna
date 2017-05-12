package com.stnetix.ariaddna.persistence.repositories;


import com.stnetix.ariaddna.persistence.entities.Certificate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexkotov on 26.04.17.
 */
@Transactional(readOnly = true)
public interface CertificateRepository extends CrudRepository<Certificate, Long> {

    @Query(value = "select c from Certificate c where c.isActive = true")
    List<Certificate> getActiveCertificates();

    @Query(value = "select c from Certificate c where c.isActive = false")
    List<Certificate> getDisableCertificates();

}

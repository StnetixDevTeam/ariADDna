package com.stnetix.ariaddna.persistence.repositories;

import com.stnetix.ariaddna.persistence.entities.CloudCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CloudCredentialsRepository extends CrudRepository<CloudCredentials, Long> {

}

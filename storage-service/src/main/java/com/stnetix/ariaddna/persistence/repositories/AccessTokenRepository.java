package com.stnetix.ariaddna.persistence.repositories;

import com.stnetix.ariaddna.persistence.entities.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

}

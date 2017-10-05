package com.stnetix.ariaddna.persistence.repositories;

import com.stnetix.ariaddna.persistence.entities.KeyStorePassword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexkotov on 01.05.17.
 */
@Transactional
public interface KeyStorePasswordRepository extends CrudRepository<KeyStorePassword, Long> {


}

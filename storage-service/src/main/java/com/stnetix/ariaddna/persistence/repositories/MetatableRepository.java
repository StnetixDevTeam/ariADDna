package com.stnetix.ariaddna.persistence.repositories;


import com.stnetix.ariaddna.persistence.entities.vufs.MetatableEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 22.02.18.
 */
@Transactional
public interface MetatableRepository extends CrudRepository<MetatableEntity, String> {
}

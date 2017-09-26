package com.stnetix.ariaddna.persistence.repositories;

import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by alexkotov on 11.09.17.
 */
@Transactional(readOnly = true)
public interface VirtualFileRepository extends CrudRepository<VirtualFileEntity, Long> {

    @Query(value = "select v.* from VirtualFileEntity v where v.owner_id in (select u.id from UserEntity u where u.uuid = CAST(?1 AS uuid)) and v.vfile_id is null", nativeQuery = true)
    List<VirtualFileEntity> getRootVirtualFileByUser(String userUUID);
}

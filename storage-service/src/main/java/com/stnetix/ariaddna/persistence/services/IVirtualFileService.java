package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;

import java.util.List;

/**
 * Created by alexkotov on 12.09.17.
 */
public interface IVirtualFileService {
    VirtualFileEntity save(VirtualFileEntity virtualFile);
    List<VirtualFileEntity> save(List<VirtualFileEntity> virtualFileEntities);
    List<VirtualFileEntity> getRootVirtualFilesByUser(UserEntity user);
}

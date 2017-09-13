package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;
import com.stnetix.ariaddna.persistence.repositories.VirtualFileRepository;
import com.stnetix.ariaddna.persistence.transformers.VirtualFileTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 13.09.17.
 */
@Repository
@Transactional(readOnly = true)
public class VirtualFileServiceImpl implements IVirtualFileService {
    @Autowired
    private VirtualFileRepository repository;

    private VirtualFileTransformer transformer;

    public VirtualFileServiceImpl(){
        transformer = new VirtualFileTransformer();
    }

    @Override
    public VirtualFileEntity save(VirtualFileEntity virtualFile) {
        return repository.save(virtualFile);
    }

    @Override
    public List<VirtualFileEntity> save(List<VirtualFileEntity> virtualFileEntities) {
        List<VirtualFileEntity> entities = new ArrayList<>();
        virtualFileEntities.stream()
                .forEach(virtualFileEntity -> entities.add(save(virtualFileEntity)));
        return entities;
    }

    @Override
    public List<VirtualFileEntity> getRootVirtualFilesByUser(UserEntity user) {
        return repository.getRootByUserUUID(user.getUuid());
    }
}

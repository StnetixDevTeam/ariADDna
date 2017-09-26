package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.repositories.VirtualFileRepository;
import com.stnetix.ariaddna.persistence.transformers.UserTransformer;
import com.stnetix.ariaddna.persistence.transformers.VirtualFileTransformer;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 13.09.17.
 */
@Repository
@Transactional
public class VirtualFileServiceImpl implements IVirtualFileService {
    @Autowired
    private VirtualFileRepository repository;

    private VirtualFileTransformer virtualFileTransformer;


    public VirtualFileServiceImpl(){
        virtualFileTransformer = new VirtualFileTransformer();
    }

    @Override
    public VirtualFileDTO save(VirtualFileDTO virtualFile) {
        return virtualFileTransformer.virtualFileEntityToDTO(repository.save(virtualFileTransformer.virtualFileDTOToEntity(virtualFile)));
    }

    @Override
    public List<VirtualFileDTO> save(List<VirtualFileDTO> virtualFileDTOs) {
        List<VirtualFileDTO> dtos = new ArrayList<>();
        virtualFileDTOs.stream()
                .forEach(virtualFileDTO -> dtos.add(virtualFileTransformer.virtualFileEntityToDTO(repository.save(virtualFileTransformer.virtualFileDTOToEntity(virtualFileDTO)))));
        return dtos;
    }

    @Override
    public List<VirtualFileDTO> getRootVirtualFilesByUser(UserDTO userDTO) {
        List<VirtualFileDTO> dtos = new ArrayList<>();
        repository.getRootVirtualFileByUser(userDTO.getUuid().toString()).forEach(virtualFileEntity -> dtos.add(virtualFileTransformer.virtualFileEntityToDTO(virtualFileEntity)));
        return dtos;
    }
}

package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;

import java.util.List;

/**
 * Created by alexkotov on 12.09.17.
 */
public interface IVirtualFileService {
    VirtualFileDTO save(VirtualFileDTO virtualFile);
    List<VirtualFileDTO> save(List<VirtualFileDTO> virtualFileDTOs);
    List<VirtualFileDTO> getRootVirtualFilesByUser(UserDTO userDTO);
}

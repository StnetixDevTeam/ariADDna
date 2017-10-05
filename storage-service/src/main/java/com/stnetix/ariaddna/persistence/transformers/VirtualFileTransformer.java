package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.entities.vufs.ChunkFileEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.FilePropertyEntity;
import com.stnetix.ariaddna.vufs.DTO.ChunkFileDTO;
import com.stnetix.ariaddna.vufs.DTO.FilePropertyDTO;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 12.09.17.
 */
public class VirtualFileTransformer {
    private UserTransformer userTransformer;
    private FilePropertyTransformer filePropertyTransformer;
    private ChunkFileTransformer chunkFileTransformer;

    public VirtualFileTransformer() {
        userTransformer = Mappers.getMapper(UserTransformer.class);
        filePropertyTransformer = Mappers.getMapper(FilePropertyTransformer.class);
        chunkFileTransformer = Mappers.getMapper(ChunkFileTransformer.class);
    }

    public com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity virtualFileDTOToEntity(VirtualFileDTO vfDTO) {
        if (vfDTO == null) {
            return null;
        }
        com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity virtualFileEntity = new com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity();

        virtualFileEntity.setAllocationStrategy(vfDTO.getAllocationStrategy());
        virtualFileEntity.setUuid(vfDTO.getUuid());
        if (vfDTO.getOwner() != null) {
            virtualFileEntity.setOwner(userTransformer.userDTOToEntity(vfDTO.getOwner()));
        }

        if (vfDTO.getProperties() != null) {
            List<FilePropertyEntity> filePropertyEntities = new ArrayList<>();
            vfDTO.getProperties()
                    .stream()
                    .forEach(filePropertyDTO -> filePropertyEntities.add(filePropertyTransformer.filePropertyDTOToEntity(filePropertyDTO)));
            virtualFileEntity.setProperties(filePropertyEntities);
        }

        if (vfDTO.getChunks() != null) {
            List<ChunkFileEntity> chunkFileEntities = new ArrayList<>();
            vfDTO.getChunks()
                    .stream()
                    .forEach(chunkFileDTO -> chunkFileEntities.add(chunkFileTransformer.chunkFileDTOToEntity(chunkFileDTO)));
            virtualFileEntity.setChunks(chunkFileEntities);
        }

        if (vfDTO.getChilds() != null) {
            List<com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity> virtualFileEntities = new ArrayList<>();
            vfDTO.getChilds()
                    .stream()
                    .forEach(virtualFileDTO -> virtualFileEntities.add(virtualFileDTOToEntity(virtualFileDTO)));
            virtualFileEntity.setChilds(virtualFileEntities);
        }
        return virtualFileEntity;
    }

    public VirtualFileDTO virtualFileEntityToDTO(com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity vfEntity) {
        if (vfEntity == null) {
            return null;
        }
        VirtualFileDTO virtualFileDTO = new VirtualFileDTO();
        virtualFileDTO.setAllocationStrategy(vfEntity.getAllocationStrategy());
        virtualFileDTO.setUuid(vfEntity.getUuid());
        if (vfEntity.getOwner() != null) {
            virtualFileDTO.setOwner(userTransformer.userEntityToDTO(vfEntity.getOwner()));
        }

        if (vfEntity.getProperties() != null) {
            List<FilePropertyDTO> filePropertyDTOs = new ArrayList<>();
            vfEntity.getProperties()
                    .stream()
                    .forEach(filePropertyEntity -> filePropertyDTOs.add(filePropertyTransformer.filePropertyEntityToDTO(filePropertyEntity)));
            virtualFileDTO.setProperties(filePropertyDTOs);
        }

        if (vfEntity.getChunks() != null) {
            List<ChunkFileDTO> chunkFileDTOs = new ArrayList<>();
            vfEntity.getChunks()
                    .stream()
                    .forEach(chunkFileEntity -> chunkFileDTOs.add(chunkFileTransformer.chunkFileEntityToDTO(chunkFileEntity)));
            virtualFileDTO.setChunks(chunkFileDTOs);
        }

        if (vfEntity.getChilds() != null) {
            List<VirtualFileDTO> virtualFileDTOs = new ArrayList<>();
            vfEntity.getChilds()
                    .stream()
                    .forEach(virtualFileEntity -> virtualFileDTOs.add(virtualFileEntityToDTO(virtualFileEntity)));
            virtualFileDTO.setChilds(virtualFileDTOs);
        }

        return virtualFileDTO;
    }

}

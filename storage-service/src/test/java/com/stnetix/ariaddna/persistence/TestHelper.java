package com.stnetix.ariaddna.persistence;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.ChunkFileEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.FilePropertyEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;
import com.stnetix.ariaddna.vufs.BusinessObjects.AllocationStrategy;
import com.stnetix.ariaddna.vufs.DTO.ChunkFileDTO;
import com.stnetix.ariaddna.vufs.DTO.FilePropertyDTO;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by alexkotov on 15.09.17.
 */
public class TestHelper {

    public static FilePropertyEntity getFilePropertyEntity(){
        FilePropertyEntity entity = new FilePropertyEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setPropertyName("propertyName");
        entity.setPropertyValue("propertyValue");
        return entity;
    }

    public static FilePropertyDTO getFilePropertyDTO(){
        FilePropertyDTO dto = new FilePropertyDTO();
        dto.setUuid(UUID.randomUUID());
        dto.setPropertyName("propertyName");
        dto.setPropertyValue("propertyValue");
        return dto;
    }

    public static ChunkFileEntity getChunkFileEntity(){
        ChunkFileEntity entity = new ChunkFileEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setUrls(new ArrayList<>(Arrays.asList(new String[]{"link1","link2","link3"})));
        entity.setVerifyHash(UUID.randomUUID().toString());
        return entity;
    }

    public static ChunkFileDTO getChunkFileDTO(){
        ChunkFileDTO dto = new ChunkFileDTO();
        dto.setUuid(UUID.randomUUID());
        dto.setUrls(new ArrayList<>(Arrays.asList(new String[]{"link1","link2","link3"})));
        dto.setVerifyHash(UUID.randomUUID().toString());
        return dto;
    }

    public static UserEntity getUserEntity(){
        UserEntity entity = new UserEntity();
        entity.setNickname("nickname");
        entity.setUuid(UUID.randomUUID());
        return entity;
    }

    public static UserDTO getUserDTO(UUID uuid, String nickname){
        UserDTO dto = new UserDTO();
        dto.setNickname(nickname);
        dto.setUuid(uuid);
        return dto;
    }


    public static UserDTO getUserDTO(){
        return getUserDTO(UUID.randomUUID(), "nickname");
    }

    public static List<VirtualFileEntity> getVirtualFileEntityList(int size){
        List<VirtualFileEntity> entityList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            entityList.add(getVirtualFileEntity());
        }
        return entityList;
    }

    public static VirtualFileEntity getVirtualFileEntity(){
        VirtualFileEntity entity = new VirtualFileEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setAllocationStrategy(AllocationStrategy.HIGH_AVAILABILITY);
        entity.setOwner(getUserEntity());

        List<FilePropertyEntity> filePropertyEntityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            filePropertyEntityList.add(getFilePropertyEntity());
        }
        entity.setProperties(filePropertyEntityList);

        List<ChunkFileEntity> chunkFileEntityList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            chunkFileEntityList.add(getChunkFileEntity());
        }
        entity.setChunks(chunkFileEntityList);

        return entity;
    }

    public static List<VirtualFileDTO> getVirtualFileDTOList(int size, UUID userUuid){
        List<VirtualFileDTO> dtoList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dtoList.add(getVirtualFileDTO(userUuid));
        }
        return dtoList;
    }

    public static List<VirtualFileDTO> getVirtualFileDTOList(int size){
        return getVirtualFileDTOList(size, UUID.randomUUID());
    }

    public static VirtualFileDTO getVirtualFileDTO(UUID userUuid){
        VirtualFileDTO dto = new VirtualFileDTO();
        dto.setUuid(UUID.randomUUID());
        dto.setAllocationStrategy(AllocationStrategy.HIGH_AVAILABILITY);
        dto.setOwner(getUserDTO(userUuid, "nickname"));

        List<FilePropertyDTO> filePropertyDTOList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            filePropertyDTOList.add(getFilePropertyDTO());
        }
        dto.setProperties(filePropertyDTOList);

        List<ChunkFileDTO> chunkFileDTOList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            chunkFileDTOList.add(getChunkFileDTO());
        }
        dto.setChunks(chunkFileDTOList);

        return dto;
    }

    public static VirtualFileDTO getVirtualFileDTO(){
        return getVirtualFileDTO(UUID.randomUUID());
    }
}

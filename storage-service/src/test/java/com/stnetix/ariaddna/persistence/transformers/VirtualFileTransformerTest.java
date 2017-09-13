package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.ChunkFileEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.FilePropertyEntity;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;
import com.stnetix.ariaddna.vufs.BusinessObjects.AllocationStrategy;
import com.stnetix.ariaddna.vufs.DTO.ChunkFileDTO;
import com.stnetix.ariaddna.vufs.DTO.FilePropertyDTO;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class VirtualFileTransformerTest {
    VirtualFileTransformer transformer = new VirtualFileTransformer();

    @Test
    public void virtualFileDTOToEntity() throws Exception {
        VirtualFileDTO dto = new VirtualFileDTO();

        dto.setUuid(UUID.randomUUID());
        dto.setAllocationStrategy(AllocationStrategy.HIGH_AVAILABILITY);

        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(UUID.randomUUID());
        userDTO.setNickname("nickname");

        dto.setOwner(userDTO);

        List<FilePropertyDTO> filePropertyDTOs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FilePropertyDTO tmpFilePropertyDTO = new FilePropertyDTO();
            tmpFilePropertyDTO.setUuid(UUID.randomUUID());
            tmpFilePropertyDTO.setPropertyName("propertyName"+i);
            tmpFilePropertyDTO.setPropertyValue("propertyValue"+i);
            filePropertyDTOs.add(tmpFilePropertyDTO);
        }
        dto.setProperties(filePropertyDTOs);

        List<ChunkFileDTO> chunkFileDTOs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ChunkFileDTO tmpChunkFileDTO = new ChunkFileDTO();
            tmpChunkFileDTO.setUuid(UUID.randomUUID());
            tmpChunkFileDTO.setVerifyHash(UUID.randomUUID().toString());
            tmpChunkFileDTO.setUrls(Arrays.asList("link1","link2","link3"));
        }
        dto.setChunks(chunkFileDTOs);

        com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity entity = transformer.virtualFileDTOToEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getUuid(),entity.getUuid());
        assertEquals(dto.getAllocationStrategy(), entity.getAllocationStrategy());
        assertEquals(dto.getOwner().getUuid(), entity.getOwner().getUuid());
        //assertArrayEquals(dto.getProperties().toArray(), entity.getProperties().toArray());
        //assertArrayEquals(dto.getChunks().toArray(),entity.getChunks().toArray());

    }

    @Test
    public void virtualFileEntityToDTO() throws Exception {
        VirtualFileEntity entity = new VirtualFileEntity();

        entity.setUuid(UUID.randomUUID());
        entity.setAllocationStrategy(AllocationStrategy.HIGH_AVAILABILITY);

        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID());
        userEntity.setNickname("nickname");

        entity.setOwner(userEntity);

        List<FilePropertyEntity> filePropertyEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FilePropertyEntity tmpFilePropertyEntity = new FilePropertyEntity();
            tmpFilePropertyEntity.setUuid(UUID.randomUUID());
            tmpFilePropertyEntity.setPropertyName("propertyName"+i);
            tmpFilePropertyEntity.setPropertyValue("propertyValue"+i);
            filePropertyEntities.add(tmpFilePropertyEntity);
        }
        entity.setProperties(filePropertyEntities);

        List<ChunkFileEntity> chunkFileEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ChunkFileEntity tmpChunkFileEntity = new ChunkFileEntity();
            tmpChunkFileEntity.setUuid(UUID.randomUUID());
            tmpChunkFileEntity.setVerifyHash(UUID.randomUUID().toString());
            tmpChunkFileEntity.setUrls(Arrays.asList("link1","link2","link3"));
        }
        entity.setChunks(chunkFileEntities);

        VirtualFileDTO dto = transformer.virtualFileEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getUuid(),dto.getUuid());
        assertEquals(entity.getAllocationStrategy(), dto.getAllocationStrategy());
        assertEquals(entity.getOwner().getUuid(), dto.getOwner().getUuid());
        //assertArrayEquals(entity.getProperties().toArray(), dto.getProperties().toArray());
        //assertArrayEquals(entity.getChunks().toArray(),dto.getChunks().toArray());
    }

}
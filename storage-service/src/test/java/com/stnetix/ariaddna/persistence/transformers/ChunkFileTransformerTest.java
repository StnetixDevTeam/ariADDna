package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.entities.vufs.ChunkFileEntity;
import com.stnetix.ariaddna.vufs.DTO.ChunkFileDTO;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class ChunkFileTransformerTest {
    ChunkFileTransformer transformer = Mappers.getMapper(ChunkFileTransformer.class);
    @Test
    public void chunkFileDTOToEntity() throws Exception {
        ChunkFileDTO dto = new ChunkFileDTO();
        dto.setUuid(UUID.randomUUID());
        dto.setUrls(new ArrayList<>(Arrays.asList(new String[]{"link1","link2","link3"})));
        dto.setVerifyHash(UUID.randomUUID().toString());

        ChunkFileEntity entity = transformer.chunkFileDTOToEntity(dto);

        assertNotNull(entity);
        assertEquals(entity.getUuid(), dto.getUuid());
        assertEquals(entity.getVerifyHash(), dto.getVerifyHash());
        assertArrayEquals(entity.getUrls().toArray(), dto.getUrls().toArray());
    }

    @Test
    public void chunkFileEntityToDTO() throws Exception {
        ChunkFileEntity entity = new ChunkFileEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setUrls(new ArrayList<>(Arrays.asList(new String[]{"link1","link2","link3"})));
        entity.setVerifyHash(UUID.randomUUID().toString());

        ChunkFileDTO dto = transformer.chunkFileEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(dto.getUuid(), entity.getUuid());
        assertEquals(dto.getVerifyHash(), entity.getVerifyHash());
        assertArrayEquals(dto.getUrls().toArray(), entity.getUrls().toArray());
    }

}
package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.entities.vufs.ChunkFileEntity;
import com.stnetix.ariaddna.vufs.DTO.ChunkFileDTO;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class ChunkFileTransformerTest {
    private ChunkFileTransformer transformer = Mappers.getMapper(ChunkFileTransformer.class);
    @Test
    public void chunkFileDTOToEntity() throws Exception {
        ChunkFileDTO dto = TestHelper.getChunkFileDTO();

        ChunkFileEntity entity = transformer.chunkFileDTOToEntity(dto);

        assertNotNull(entity);
        assertEquals(entity.getUuid(), dto.getUuid());
        assertEquals(entity.getVerifyHash(), dto.getVerifyHash());
        assertArrayEquals(entity.getUrls().toArray(), dto.getUrls().toArray());
    }

    @Test
    public void chunkFileEntityToDTO() throws Exception {
        ChunkFileEntity entity = TestHelper.getChunkFileEntity();

        ChunkFileDTO dto = transformer.chunkFileEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(dto.getUuid(), entity.getUuid());
        assertEquals(dto.getVerifyHash(), entity.getVerifyHash());
        assertArrayEquals(dto.getUrls().toArray(), entity.getUrls().toArray());
    }

}
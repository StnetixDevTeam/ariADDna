package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.entities.vufs.VirtualFileEntity;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class VirtualFileTransformerTest {
    VirtualFileTransformer transformer = new VirtualFileTransformer();

    @Test
    public void virtualFileDTOToEntity() throws Exception {
        VirtualFileDTO dto = TestHelper.getVirtualFileDTO();
        dto.setChilds(TestHelper.getVirtualFileDTOList(3));

        VirtualFileEntity entity = transformer.virtualFileDTOToEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getUuid(), entity.getUuid());
        assertEquals(dto.getAllocationStrategy(), entity.getAllocationStrategy());
        assertEquals(dto.getOwner().getUuid(), entity.getOwner().getUuid());
        for (int i = 0; i < entity.getProperties().size(); i++) {
            assertEquals(dto.getProperties().get(i).getUuid(),entity.getProperties().get(i).getUuid());
        }
        for (int i = 0; i < entity.getChunks().size(); i++) {
            assertEquals(dto.getChunks().get(i).getUuid(),entity.getChunks().get(i).getUuid());
        }
        for (int i = 0; i < entity.getChilds().size(); i++) {
            assertEquals(dto.getChilds().get(i).getUuid(),entity.getChilds().get(i).getUuid());
        }

    }

    @Test
    public void virtualFileEntityToDTO() throws Exception {
        VirtualFileEntity entity = TestHelper.getVirtualFileEntity();
        entity.setChilds(TestHelper.getVirtualFileEntityList(3));

        VirtualFileDTO dto = transformer.virtualFileEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getUuid(), dto.getUuid());
        assertEquals(entity.getAllocationStrategy(), dto.getAllocationStrategy());
        assertEquals(entity.getOwner().getUuid(), dto.getOwner().getUuid());
        for (int i = 0; i < dto.getProperties().size(); i++) {
            assertEquals(entity.getProperties().get(i).getUuid(),dto.getProperties().get(i).getUuid());
        }
        for (int i = 0; i < dto.getChunks().size(); i++) {
            assertEquals(entity.getChunks().get(i).getUuid(),dto.getChunks().get(i).getUuid());
        }
        for (int i = 0; i < dto.getChilds().size(); i++) {
            assertEquals(entity.getChilds().get(i).getUuid(),dto.getChilds().get(i).getUuid());
        }
    }

}
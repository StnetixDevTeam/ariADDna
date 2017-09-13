package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.entities.vufs.FilePropertyEntity;
import com.stnetix.ariaddna.vufs.DTO.FilePropertyDTO;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class FilePropertyTransformerTest {
    FilePropertyTransformer transformer = Mappers.getMapper(FilePropertyTransformer.class);

    @Test
    public void filePropertyDTOToEntity() throws Exception {
        FilePropertyDTO dto = new FilePropertyDTO();
        dto.setUuid(UUID.randomUUID());
        dto.setPropertyName("propertyName");
        dto.setPropertyValue("propertyValue");

        FilePropertyEntity entity = transformer.filePropertyDTOToEntity(dto);

        assertNotNull(entity);
        assertEquals(entity.getUuid(), dto.getUuid());
        assertEquals(entity.getPropertyName(), dto.getPropertyName());
        assertEquals(entity.getPropertyValue(), dto.getPropertyValue());
    }

    @Test
    public void filePropertyEntityToDTO() throws Exception {
        FilePropertyEntity entity = new FilePropertyEntity();
        entity.setUuid(UUID.randomUUID());
        entity.setPropertyName("propertyName");
        entity.setPropertyValue("propertyValue");

        FilePropertyDTO dto = transformer.filePropertyEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(dto.getUuid(), entity.getUuid());
        assertEquals(dto.getPropertyName(), entity.getPropertyName());
        assertEquals(dto.getPropertyValue(), entity.getPropertyValue());
    }

}
package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.entities.vufs.FilePropertyEntity;
import com.stnetix.ariaddna.vufs.DTO.FilePropertyDTO;
import org.mapstruct.Mapper;

/**
 * Created by alexkotov on 13.09.17.
 */
@Mapper
public interface FilePropertyTransformer {

    FilePropertyEntity filePropertyDTOToEntity (FilePropertyDTO filePropertyDTO);
    FilePropertyDTO filePropertyEntityToDTO (FilePropertyEntity filePropertyEntity);
}

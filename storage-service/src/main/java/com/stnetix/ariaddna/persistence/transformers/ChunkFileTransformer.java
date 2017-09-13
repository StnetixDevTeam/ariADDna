package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.persistence.entities.vufs.ChunkFileEntity;
import com.stnetix.ariaddna.vufs.DTO.ChunkFileDTO;
import org.mapstruct.Mapper;

/**
 * Created by alexkotov on 13.09.17.
 */
@Mapper
public interface ChunkFileTransformer {

    ChunkFileEntity chunkFileDTOToEntity (ChunkFileDTO chunkFileDTO);
    ChunkFileDTO chunkFileEntityToDTO (ChunkFileEntity chunkFileEntity);
}

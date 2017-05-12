package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.KeyStorePasswordDTO;
import com.stnetix.ariaddna.persistence.entities.KeyStorePassword;
import org.mapstruct.Mapper;

/**
 * Created by alexkotov on 03.05.17.
 */
@Mapper
public interface KeyStorePasswordTransformer {
    KeyStorePasswordDTO keyStorePasswordEntityToDTO(KeyStorePassword keyStorePassword);
    KeyStorePassword keyStorePasswordDTOToEntity(KeyStorePasswordDTO keyStorePasswordDTO);
}

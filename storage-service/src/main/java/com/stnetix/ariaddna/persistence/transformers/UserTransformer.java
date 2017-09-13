package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

/**
 * Created by alexkotov on 13.09.17.
 */
@Mapper
public interface UserTransformer {
    UserEntity userDTOToEntity (UserDTO userDTO);
    UserDTO userEntityToDTO (UserEntity userEntity);
}

package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class UserTransformerTest {
    UserTransformer transformer = Mappers.getMapper(UserTransformer.class);

    @Test
    public void userDTOToEntity() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(UUID.randomUUID());
        userDTO.setNickname("nickname");

        UserEntity userEntity = transformer.userDTOToEntity(userDTO);
        assertNotNull(userEntity);
        assertEquals(userEntity.getUuid(), userDTO.getUuid());
        assertEquals(userEntity.getNickname(), userDTO.getNickname());
    }

    @Test
    public void userEntityToDTO() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setNickname("nickname");
        userEntity.setUuid(UUID.randomUUID());

        UserDTO userDTO = transformer.userEntityToDTO(userEntity);
        assertNotNull(userDTO);
        assertEquals(userDTO.getNickname(), userEntity.getNickname());
        assertEquals(userDTO.getUuid(), userEntity.getUuid());
    }

}
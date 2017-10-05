package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.entities.UserEntity;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 13.09.17.
 */
public class UserTransformerTest {
    private UserTransformer transformer = Mappers.getMapper(UserTransformer.class);

    @Test
    public void userDTOToEntity() throws Exception {
        UserDTO userDTO = TestHelper.getUserDTO();

        UserEntity userEntity = transformer.userDTOToEntity(userDTO);
        assertNotNull(userEntity);
        assertEquals(userEntity.getUuid(), userDTO.getUuid());
        assertEquals(userEntity.getNickname(), userDTO.getNickname());
    }

    @Test
    public void userEntityToDTO() throws Exception {
        UserEntity userEntity = TestHelper.getUserEntity();

        UserDTO userDTO = transformer.userEntityToDTO(userEntity);
        assertNotNull(userDTO);
        assertEquals(userDTO.getNickname(), userEntity.getNickname());
        assertEquals(userDTO.getUuid(), userEntity.getUuid());
    }

}
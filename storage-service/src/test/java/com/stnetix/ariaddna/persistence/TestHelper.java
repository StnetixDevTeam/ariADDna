package com.stnetix.ariaddna.persistence;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.entities.UserEntity;

import java.util.UUID;

/**
 * Created by alexkotov on 15.09.17.
 */
public class TestHelper {



    public static UserEntity getUserEntity(){
        UserEntity entity = new UserEntity();
        entity.setNickname("nickname");
        entity.setUuid(UUID.randomUUID());
        return entity;
    }

    public static UserDTO getUserDTO(UUID uuid, String nickname){
        UserDTO dto = new UserDTO();
        dto.setNickname(nickname);
        dto.setUuid(uuid);
        return dto;
    }


    public static UserDTO getUserDTO(){
        return getUserDTO(UUID.randomUUID(), "nickname");
    }


}

package com.stnetix.ariaddna.persistence.transformers;

import com.stnetix.ariaddna.commonutils.DTO.KeyStorePasswordDTO;
import com.stnetix.ariaddna.persistence.entities.KeyStorePassword;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 03.05.17.
 */
public class KeyStorePasswordTransformerTest {
    KeyStorePasswordTransformer transformer = Mappers.getMapper(KeyStorePasswordTransformer.class);

    @Test
    public void keyStorePasswordEntityToDTO() throws Exception {
        KeyStorePassword keyStorePassword = new KeyStorePassword();
        keyStorePassword.setId(2L);
        keyStorePassword.setPass("1234567890");
        KeyStorePasswordDTO keyStorePasswordDTO = transformer.keyStorePasswordEntityToDTO(keyStorePassword);

        assertNotEquals(keyStorePasswordDTO, keyStorePassword);
        assertEquals(keyStorePasswordDTO.getId(), keyStorePassword.getId());
        assertEquals(keyStorePasswordDTO.getPass(), keyStorePassword.getPass());
    }

    @Test
    public void keyStorePasswordDTOToEntity() throws Exception {
        KeyStorePasswordDTO keyStorePasswordDTO = new KeyStorePasswordDTO("1234567890");
        keyStorePasswordDTO.setId(2L);
        KeyStorePassword keyStorePassword = transformer.keyStorePasswordDTOToEntity(keyStorePasswordDTO);

        assertNotEquals(keyStorePassword, keyStorePasswordDTO);
        assertEquals(keyStorePassword.getId(), keyStorePasswordDTO.getId());
        assertEquals(keyStorePassword.getPass(), keyStorePasswordDTO.getPass());
    }

}
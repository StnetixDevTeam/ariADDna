package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.KeyStorePasswordDTO;

import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;



import static org.junit.Assert.*;

/**
 * Created by alexkotov on 11.05.17.
 */
public class KeyStorePasswordServiceImplTest {
    private ConfigurableApplicationContext context;
    private KeyStorePasswordServiceImpl keyStorePasswordService;

    @Before
    public void before() {
        context = SpringApplication.run(AppConfiguration.class);
        keyStorePasswordService = context.getBean(KeyStorePasswordServiceImpl.class);
    }

    @Test
    public void saveTest() throws Exception {
        KeyStorePasswordDTO keyStorePasswordDTO1 = new KeyStorePasswordDTO("0123456789");
        KeyStorePasswordDTO keyStorePasswordDTO2 = new KeyStorePasswordDTO("9876543210");
        KeyStorePasswordDTO savedKeyStorePasswordDTO1 = keyStorePasswordService.save(keyStorePasswordDTO1);
        KeyStorePasswordDTO savedKeyStorePasswordDTO2 = keyStorePasswordService.save(keyStorePasswordDTO2);

        assertEquals(savedKeyStorePasswordDTO1.getPass(),savedKeyStorePasswordDTO2.getPass());

    }

    @Test
    public void getPasswordTest() throws Exception {
        KeyStorePasswordDTO keyStorePasswordDTO1 = new KeyStorePasswordDTO("0123456789");
        KeyStorePasswordDTO keyStorePasswordDTO2 = new KeyStorePasswordDTO("9876543210");
        KeyStorePasswordDTO savedKeyStorePasswordDTO1 = keyStorePasswordService.save(keyStorePasswordDTO1);
        KeyStorePasswordDTO savedKeyStorePasswordDTO2 = keyStorePasswordService.save(keyStorePasswordDTO2);
        KeyStorePasswordDTO keyStorePasswordDTO = keyStorePasswordService.getPassword();

        assertEquals(keyStorePasswordDTO.getPass(),savedKeyStorePasswordDTO1.getPass());
        assertEquals(keyStorePasswordDTO.getPass(),savedKeyStorePasswordDTO2.getPass());

    }

}
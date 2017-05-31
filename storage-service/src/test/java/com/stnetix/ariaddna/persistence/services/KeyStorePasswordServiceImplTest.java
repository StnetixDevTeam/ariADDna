package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.KeyStorePasswordDTO;

import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

/**
 * Created by alexkotov on 11.05.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class KeyStorePasswordServiceImplTest {

    @Autowired
    private IKeyStorePasswordService keyStorePasswordService;


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
/*
 * Copyright (c) 2017 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.persistence.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.dto.KeyStorePasswordDTO;
import com.stnetix.ariaddna.persistence.entities.KeyStorePassword;
import com.stnetix.ariaddna.persistence.repositories.KeyStorePasswordRepository;
import com.stnetix.ariaddna.persistence.transformers.KeyStorePasswordTransformer;

/**
 * Created by alexkotov on 01.05.17.
 */
@Repository
@Transactional
public class KeyStorePasswordServiceImpl implements IKeyStorePasswordService {

    private KeyStorePasswordTransformer transformer;
    @Autowired
    private KeyStorePasswordRepository repository;

    public KeyStorePasswordServiceImpl() {
        transformer = Mappers.getMapper(KeyStorePasswordTransformer.class);
    }

    @Override
    public KeyStorePasswordDTO save(KeyStorePasswordDTO passwordDTO) {
        List<KeyStorePasswordDTO> keyStorePasswordDTOs = getAllPasswords();
        if (keyStorePasswordDTOs.size() > 0) {
            return keyStorePasswordDTOs.get(0);
        }
        KeyStorePassword password = transformer.keyStorePasswordDTOToEntity(passwordDTO);
        return transformer.keyStorePasswordEntityToDTO(repository.save(password));
    }

    @Override
    public KeyStorePasswordDTO getPassword() {
        List<KeyStorePasswordDTO> keyStorePasswordDTOList = getAllPasswords();
        if (keyStorePasswordDTOList.size() == 0) {
            KeyStorePasswordDTO newKeyStorePasswordDTO = new KeyStorePasswordDTO(
                    UUID.randomUUID().toString());
            return save(newKeyStorePasswordDTO);
        }
        return keyStorePasswordDTOList.get(0);
    }

    private List<KeyStorePasswordDTO> getAllPasswords() {
        List<KeyStorePasswordDTO> keyStorePasswordDTOList = new ArrayList<>();
        repository.findAll().forEach(keyStorePassword -> keyStorePasswordDTOList
                .add(transformer.keyStorePasswordEntityToDTO(keyStorePassword)));
        return keyStorePasswordDTOList;
    }
}

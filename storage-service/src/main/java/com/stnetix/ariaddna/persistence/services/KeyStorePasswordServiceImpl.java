package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.KeyStorePasswordDTO;
import com.stnetix.ariaddna.persistence.entities.KeyStorePassword;
import com.stnetix.ariaddna.persistence.repositories.KeyStorePasswordRepository;
import com.stnetix.ariaddna.persistence.transformers.KeyStorePasswordTransformer;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by alexkotov on 01.05.17.
 */
@Repository
@Transactional
public class KeyStorePasswordServiceImpl implements IKeyStorePasswordService {

    private KeyStorePasswordTransformer transformer;
    @Autowired
    private KeyStorePasswordRepository repository;

    public KeyStorePasswordServiceImpl(){
        transformer = Mappers.getMapper(KeyStorePasswordTransformer.class);
    }

    @Override
    public KeyStorePasswordDTO save(KeyStorePasswordDTO passwordDTO) {
        List<KeyStorePasswordDTO> keyStorePasswordDTOs = getAllPasswords();
        if(keyStorePasswordDTOs.size()>0) return keyStorePasswordDTOs.get(0);
        KeyStorePassword password = transformer.keyStorePasswordDTOToEntity(passwordDTO);
        return transformer.keyStorePasswordEntityToDTO(repository.save(password));
    }

    @Override
    public KeyStorePasswordDTO getPassword() {
        List<KeyStorePasswordDTO> keyStorePasswordDTOList = getAllPasswords();
        if(keyStorePasswordDTOList.size()==0){
            KeyStorePasswordDTO newKeyStorePasswordDTO = new KeyStorePasswordDTO(UUID.randomUUID().toString());
            return save(newKeyStorePasswordDTO);
        }
        return keyStorePasswordDTOList.get(0);
    }

    private List<KeyStorePasswordDTO> getAllPasswords(){
        List<KeyStorePasswordDTO> keyStorePasswordDTOList = new ArrayList<>();
        repository.findAll().forEach(keyStorePassword -> keyStorePasswordDTOList.add(transformer.keyStorePasswordEntityToDTO(keyStorePassword)));
        return keyStorePasswordDTOList;
    }
}

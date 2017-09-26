package com.stnetix.ariaddna.persistence.services;

import com.stnetix.ariaddna.commonutils.DTO.UserDTO;
import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import com.stnetix.ariaddna.vufs.DTO.VirtualFileDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 15.09.17.
 */
@SpringBootTest(classes = AppConfiguration.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class VirtualFileServiceTest {

    @Autowired
    private IVirtualFileService service;
    @Test
    public void saveOneVirtualFileTest() throws Exception {
        VirtualFileDTO dto = TestHelper.getVirtualFileDTO();
        dto.setChilds(TestHelper.getVirtualFileDTOList(3));

        VirtualFileDTO savedDTO = service.save(dto);
        assertNotNull(savedDTO);
        assertEquals(savedDTO.getUuid(), dto.getUuid());
        assertEquals(savedDTO.getOwner().getNickname(), dto.getOwner().getNickname());
        assertEquals(savedDTO.getProperties().size(), dto.getProperties().size());
        assertEquals(savedDTO.getChunks().size(), dto.getChunks().size());
    }

    @Test
    public void saveListOfVirtualFileTest() throws Exception {
        int count = 3;
        List<VirtualFileDTO> dtoList = TestHelper.getVirtualFileDTOList(count);
        List<VirtualFileDTO> savedDtoList = service.save(dtoList);

        assertEquals(savedDtoList.size(), dtoList.size());
        for (int i = 0; i < count; i++) {
            assertEquals(savedDtoList.get(i).getUuid(), dtoList.get(i).getUuid());
        }
    }

    @Test
    public void getRootVirtualFilesByUserTest() throws Exception {

        VirtualFileDTO rootDTO = TestHelper.getVirtualFileDTO();
        List<VirtualFileDTO> childs = TestHelper.getVirtualFileDTOList(3);
        UserDTO owner = TestHelper.getUserDTO(UUID.randomUUID(), "testNickName");
        for (VirtualFileDTO VirtualFileDTO : childs) {
            VirtualFileDTO.setOwner(owner);
        }
        rootDTO.setChilds(childs);
        rootDTO.setOwner(owner);

        VirtualFileDTO rootDTO2 = TestHelper.getVirtualFileDTO();
        rootDTO2.setOwner(owner);

        service.save(rootDTO);
        service.save(rootDTO2);

        VirtualFileDTO anotherDTO = TestHelper.getVirtualFileDTO();

        service.save(anotherDTO);
        List<VirtualFileDTO> rootDTOs = service.getRootVirtualFilesByUser(owner);

        assertTrue(rootDTOs.size()==2);
        // assertEquals(rootDTOs.get(0).getUuid(), rootDTO.getUuid());

    }

}
/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.stnetix.ariaddna.commonutils.datetime.DateTime;
import com.stnetix.ariaddna.commonutils.mavenutil.MavenUtil;
import com.stnetix.ariaddna.persistence.TestHelper;
import com.stnetix.ariaddna.persistence.utils.AppConfiguration;
import com.stnetix.ariaddna.vufs.businessobjects.MetatableType;
import com.stnetix.ariaddna.vufs.dto.MetatableDTO;

/**
 * Created by vasap87 on 05.03.18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfiguration.class)
@Transactional
public class MetatableServiceImplTest {

    private static String metatableSnapShot1Uuid = UUID.randomUUID().toString();
    private static String metatableSnapShot2Uuid = UUID.randomUUID().toString();
    private static String metatableMasterUuid = UUID.randomUUID().toString();
    private static Long lastUpdateTimeStamp = new DateTime().getTimeInMillisec();
    private static Long lastUpdateTimeStamp1 = lastUpdateTimeStamp + 1000L;
    private static Long lastUpdateTimeStamp2 = lastUpdateTimeStamp + 2000L;
    private static Long lastUpdateTimeStamp3 = lastUpdateTimeStamp + 3000L;

    @Autowired
    private IMetatableService service;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void getMetatableMaster() throws Exception {
        MetatableDTO metatableDTO1 = TestHelper
                .getMetatableDTO(metatableMasterUuid, MavenUtil.getCurrentVersion(),
                        MetatableType.MASTER, lastUpdateTimeStamp1);
        MetatableDTO metatableDTO2 = TestHelper
                .getMetatableDTO(metatableSnapShot1Uuid, MavenUtil.getCurrentVersion(),
                        MetatableType.SNAPSHOT, lastUpdateTimeStamp2);
        MetatableDTO metatableDTO3 = TestHelper
                .getMetatableDTO(metatableSnapShot2Uuid, MavenUtil.getCurrentVersion(),
                        MetatableType.SNAPSHOT, lastUpdateTimeStamp3);

        MetatableDTO saved1 = service.saveMetatable(metatableDTO1);
        MetatableDTO saved2 = service.saveMetatable(metatableDTO2);
        MetatableDTO saved3 = service.saveMetatable(metatableDTO3);

        MetatableDTO masterMetatableDTO = service.getMetatableMaster(metatableMasterUuid);

        assertNotNull(masterMetatableDTO);
        assertEquals(masterMetatableDTO.getLastUpdateTimestamp(), lastUpdateTimeStamp1);

    }

    @Test
    public void saveMetatable() throws Exception {

        MetatableDTO metatableDTO = TestHelper
                .getMetatableDTO(metatableSnapShot2Uuid, MavenUtil.getCurrentVersion(),
                        MetatableType.MASTER, lastUpdateTimeStamp);

        MetatableDTO savedMetatableDTO = service.saveMetatable(metatableDTO);

        assertNotNull(savedMetatableDTO);

    }

}
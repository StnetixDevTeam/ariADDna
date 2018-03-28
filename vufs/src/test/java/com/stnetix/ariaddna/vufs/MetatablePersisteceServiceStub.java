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

package com.stnetix.ariaddna.vufs;

import com.stnetix.ariaddna.commonutils.dto.vufs.MetatableDTO;
import com.stnetix.ariaddna.persistence.services.IMetatableService;

public class MetatablePersisteceServiceStub implements IMetatableService {
    @Override public MetatableDTO getMetatableMaster(String metatableUuid) {
        return new MetatableDTO();
    }

    @Override public MetatableDTO saveMetatable(MetatableDTO metatable) {
        return new MetatableDTO();
    }
}

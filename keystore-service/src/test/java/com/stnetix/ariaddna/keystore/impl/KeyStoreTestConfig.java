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

package com.stnetix.ariaddna.keystore.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stnetix.ariaddna.keystore.IKeyStore;
import com.stnetix.ariaddna.keystore.utils.CertFactory;
import com.stnetix.ariaddna.keystore.utils.IPersistHelper;
import com.stnetix.ariaddna.keystore.utils.KeyFactory;
import com.stnetix.ariaddna.keystore.utils.PersistHelperStubImpl;

/**
 * Created by alexkotov on 25.07.17.
 */
@Configuration
public class KeyStoreTestConfig {
    @Bean
    public IKeyStore keyStore() {
        return new KeyStoreService();
    }

    @Bean
    public IPersistHelper persistHelper() {
        return new PersistHelperStubImpl();
    }

    @Bean
    public CertFactory certFactory(IPersistHelper persistHelper) {
        return new CertFactory(persistHelper);

    }

    @Bean
    public KeyFactory keyFactory(IPersistHelper persistHelper, CertFactory certFactory) {
        return new KeyFactory(persistHelper, certFactory);
    }

}

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

package com.stnetix.ariaddna.pushnotification.server;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * Created by LugovoyAV on 06.04.2017.
 */
@Component
public class SharedMap<T, E> {
    private final ConcurrentHashMap<T, E> map;

    public SharedMap() {
        this.map = new ConcurrentHashMap<T, E>();
    }

    public Set<Map.Entry<T, E>> entrySet() {
        return map.entrySet();
    }

    public E put(T key, E value) {
        return map.put(key, value);
    }

    public E get(T key) {
        return map.get(key);
    }
}

package com.stnetix.ariaddna.pushnotification.server;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by LugovoyAV on 06.04.2017.
 */
@Component
public class SharedMap<T,E> {
    private final ConcurrentHashMap<T,E> map;

    public SharedMap() {
        this.map = new ConcurrentHashMap<T,E>();
    }

    public Set<Map.Entry<T,E>> entrySet(){
        return map.entrySet();
    }
    public E put(T key, E value){ return map.put(key, value);}
    public E get(T key){return  map.get(key);}
}

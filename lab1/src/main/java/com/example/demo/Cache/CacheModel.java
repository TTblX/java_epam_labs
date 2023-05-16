package com.example.demo.Cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component(value="cache")
@Scope("prototype")
public class CacheModel<K, V> {

    private Map<K, V> cache = Collections.synchronizedMap(new HashMap<K, V>());

    public void push(K request, V response) {
        cache.put(request, response);
    }

    public V Get(K request) {
        return cache.get(request);
    }
}

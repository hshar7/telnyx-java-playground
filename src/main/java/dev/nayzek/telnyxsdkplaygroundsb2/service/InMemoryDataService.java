package dev.nayzek.telnyxsdkplaygroundsb2.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryDataService {

    private final ConcurrentHashMap<String, String> dataMap = new ConcurrentHashMap<>();

    public void putData(String key, String value) {
        dataMap.put(key, value);
    }

    public String getData(String key) {
        return dataMap.get(key);
    }

    public void removeData(String key) {
        dataMap.remove(key);
    }
}

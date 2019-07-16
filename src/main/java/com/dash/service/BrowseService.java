package com.dash.service;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BrowseService {

    public List<String> searchKey(RedisConnection redisConnection, String key) {
        key = "*" + key + "*";
        Set<byte[]> keys = redisConnection.keys(key.getBytes());
        List<String> keyList = new ArrayList<>();
        for (byte[] arr:keys) {
            keyList.add(arr.toString());
        }
        return keyList;
    }
}

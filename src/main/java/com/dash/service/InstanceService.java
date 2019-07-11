package com.dash.service;

import com.dash.model.Info;
import com.dash.model.Instance;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InstanceService {
    List<Instance> instances;
    Map<String, RedisConnection> instanceConnections;

    public InstanceService() {
        instances = new ArrayList<>();
        instanceConnections = new HashMap<>();
        Instance one = new Instance();
        one.setHostname("192.168.0.1");
        one.setName("Sample Instance");
        one.setPort(6379);
        one.setUuid(UUID.randomUUID().toString());

        instances.add(one);
        instanceConnections.put(one.getUuid(),getRedisConnection(one.getHostname(),one.getPort()));
        /*instances.add(one);
        instances.add(one);
        instances.add(one);
        instances.add(one);
        instances.add(one);*/
    }

    public Instance registerInstance(String name, String hostname, int port) {
        Instance instance = new Instance();
        instance.setName(name);
        instance.setHostname(hostname);
        instance.setPort(port);
        instance.setUuid(UUID.randomUUID().toString());
        instances.add(instance);
        instanceConnections.put(instance.getUuid(),getRedisConnection(hostname,port));
        return instance;
    }

    public List<Instance> getRegisteredInstances() {
        return instances;
    }

    public Optional<Instance> getInstance(String uuid) {
        return instances.stream().filter(instance -> instance.getUuid().equals(uuid)).findFirst();
    }

    public Info getInstanceOverview(Instance instance) {
        RedisConnection connection = instanceConnections.get(instance.getUuid());
        Properties properties = connection.info();
        Info info = new Info();
        info.setTotalKeys(connection.dbSize());
        info.setConnectedClients(Integer.parseInt(properties.getProperty("connected_clients")));
        info.setConnectionsReceived(Integer.parseInt(properties.getProperty("total_connections_received")));
        int hits = Integer.parseInt(properties.getProperty("keyspace_hits"));
        int misses = Integer.parseInt(properties.getProperty("keyspace_misses"));
        float hitRatio = (float) hits/(hits+misses);
        info.setHitRatio(String.format("%.2f",hitRatio));
        info.setUptime(Float.parseFloat(properties.getProperty("uptime_in_seconds")));
        info.setUptimeDays(properties.getProperty("uptime_in_days"));
        info.setConnectionsRejected(Integer.parseInt(properties.getProperty("rejected_connections")));
        info.setMaxMemory(Long.parseLong(properties.getProperty("maxmemory")));
        info.setMaxMemoryHuman(properties.getProperty("maxmemory_human"));
        info.setUsedMemory(Long.parseLong(properties.getProperty("used_memory")));
        info.setUsedMemoryHuman(properties.getProperty("used_memory_human"));
        info.setVersion(properties.getProperty("redis_version"));
        info.setRole(properties.getProperty("role"));
        return info;
    }

    private RedisConnection getRedisConnection(String hostname, int port) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostname, port);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(config);
        return jedisConnectionFactory.getConnection();
    }

    public List<RedisClientInfo> getClients(Instance instance) {
        RedisConnection redisConnection = instanceConnections.get(instance.getUuid());
        return redisConnection.getClientList();
    }
}

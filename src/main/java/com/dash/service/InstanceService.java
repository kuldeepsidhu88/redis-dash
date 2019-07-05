package com.dash.service;

import com.dash.model.Info;
import com.dash.model.Instance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InstanceService {
    List<Instance> instances = new ArrayList<>();

    public InstanceService() {
        Instance one = new Instance();
        one.setHostname("192.168.0.1");
        one.setName("Sample Instance");
        one.setPort(6379);
        one.setUuid(UUID.randomUUID().toString());

        instances.add(one);
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
        return instance;
    }

    public List<Instance> getRegisteredInstances() {
        return instances;
    }

    public Optional<Instance> getInstance(String uuid) {
        return instances.stream().filter(instance -> instance.getUuid().equals(uuid)).findFirst();
    }

    public Info getInstanceOverview(Instance instance) {
        return null;
    }
}

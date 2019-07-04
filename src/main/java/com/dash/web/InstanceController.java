package com.dash.web;

import com.dash.model.Info;
import com.dash.model.Instance;
import com.dash.service.InfoService;
import com.dash.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instances")
public class InstanceController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private InstanceService instanceService;

    @GetMapping
    public List<Instance> getRegisteredInstances() {
        return instanceService.getRegisteredInstances();
    }

    @GetMapping("/{uuid}")
    public Info getInstanceDetails(@PathVariable String uuid) {
        return infoService.getInfo();
    }

    @PostMapping
    public Instance registerInstance(@RequestBody Instance instance) {
        return instanceService.registerInstance(instance.getName(), instance.getHostname(), instance.getPort());
    }

    @DeleteMapping("/{uuid}")
    public void removeInstance(@PathVariable String uuid) {
        // TODO: remove instance
    }
}

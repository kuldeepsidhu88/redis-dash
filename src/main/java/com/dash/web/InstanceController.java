package com.dash.web;

import com.dash.model.Info;
import com.dash.model.Instance;
import com.dash.service.BrowserService;
import com.dash.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/instances")
public class InstanceController {

    @Autowired
    private InstanceService instanceService;

    @Autowired
    private BrowserService browserService;

    @GetMapping
    public List<Instance> getRegisteredInstances() {
        return instanceService.getRegisteredInstances();
    }

    @GetMapping("/{uuid}")
    public String getInstanceDetails(@PathVariable String uuid, Model model) {
        Optional<Instance> instance = instanceService.getInstance(uuid);
        if(instance.isPresent()) {
            Info instanceOverview = instanceService.getInstanceOverview(instance.get());
            model.addAttribute("instance",instance.get());
            model.addAttribute("overview",instanceOverview);
            model.addAttribute("info",instanceOverview);
        }
        return "overview.html";
    }

    @GetMapping("/{uuid}/refresh")
    @ResponseBody
    public Info refreshInstanceDetails(@PathVariable String uuid) {
        Optional<Instance> instance = instanceService.getInstance(uuid);
        if(instance.isPresent()) {
            Info instanceOverview = instanceService.getInstanceOverview(instance.get());
            return instanceOverview;
        }
        return null;
    }

    @GetMapping("/{uuid}/browser")
    public String instanceBrowser(@PathVariable String uuid, Model model) {
        Optional<Instance> instance = instanceService.getInstance(uuid);
        if(instance.isPresent()) {
            model.addAttribute("instance",instance.get());
        }
        return "browser.html";
    }

    @GetMapping("/{uuid}/clients")
    public String getClients(@PathVariable String uuid, Model model) {
        Optional<Instance> instance = instanceService.getInstance(uuid);
        if(instance.isPresent()) {
            List<RedisClientInfo> clients = instanceService.getClients(instance.get());
            model.addAttribute("instance",instance.get());
            model.addAttribute("clients",clients);
        }
        return "clients.html";
    }

    @PostMapping
    @ResponseBody
    public Instance registerInstance(@RequestBody Instance instance) {
        return instanceService.registerInstance(instance.getName(), instance.getHostname(), instance.getPort());
    }

    @DeleteMapping("/{uuid}")
    public void removeInstance(@PathVariable String uuid) {
        // TODO: remove instance
    }
}

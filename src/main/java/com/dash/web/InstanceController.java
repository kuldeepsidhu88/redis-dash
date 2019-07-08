package com.dash.web;

import com.dash.model.Info;
import com.dash.model.Instance;
import com.dash.service.InfoService;
import com.dash.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
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

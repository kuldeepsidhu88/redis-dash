package com.dash.web;

import com.dash.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private InstanceService instanceService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("instances", instanceService.getRegisteredInstances());
        return "index.html";
    }
}
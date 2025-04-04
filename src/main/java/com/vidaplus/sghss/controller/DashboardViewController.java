package com.vidaplus.sghss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardViewController {

    @GetMapping
    public String exibirDashboard() {
        return "dashboard";
    }
}

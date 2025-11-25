package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/panel")
    public String panelAdmin(Model model) {
        model.addAttribute("titulo", "Panel del Administrador");
        return "panel_admin";
    }

}

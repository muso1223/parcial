package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoordinadorController {

    @GetMapping("/coordinador/panel")
    public String panelCoordinador(Model model) {
        model.addAttribute("titulo", "Panel del Coordinador");
        return "panel_coordinador";
    }
}

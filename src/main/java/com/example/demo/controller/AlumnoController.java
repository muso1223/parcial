package com.example.demo.controller;

import com.example.demo.entidades.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coordinador/alumnos")
public class AlumnoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LISTAR ALUMNOS
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alumnos", usuarioRepository.findByRol("ALUMNO"));
        return "coordinador/alumnos_list";
    }

    // FORMULARIO NUEVO ALUMNO
    @GetMapping("/nuevo")
    public String nuevoAlumno(Model model) {
        model.addAttribute("alumno", new Usuario());
        return "coordinador/alumnos_form";
    }

    // GUARDAR (CREAR o EDITAR)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("alumno") Usuario alumno) {

        alumno.setRol("ALUMNO"); // aseguramos que siempre sea ALUMNO

        usuarioRepository.save(alumno);

        return "redirect:/coordinador/alumnos";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario user = usuarioRepository.findById(id).orElse(null);

        if (user == null || !"ALUMNO".equalsIgnoreCase(user.getRol())) {
            return "redirect:/coordinador/alumnos";
        }

        model.addAttribute("alumno", user);
        return "coordinador/alumnos_form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        Usuario user = usuarioRepository.findById(id).orElse(null);

        if (user != null && "ALUMNO".equalsIgnoreCase(user.getRol())) {
            usuarioRepository.delete(user);
        }

        return "redirect:/coordinador/alumnos";
    }
}

package com.example.demo.controller;

import com.example.demo.entidades.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/coordinadores")
public class CoordinadorAdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LISTAR COORDINADORES
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("coordinadores", usuarioRepository.findByRol("COORDINADOR"));
        return "admin/coordinadores_list";
    }

    // FORMULARIO DE CREACIÓN
    @GetMapping("/nuevo")
    public String nuevoCoordinador(Model model) {
        model.addAttribute("coordinador", new Usuario());
        return "admin/coordinadores_form";
    }

    // GUARDAR (CREAR o EDITAR)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("coordinador") Usuario coordinador, Model model) {

        coordinador.setRol("COORDINADOR");
        coordinador.setRegistro(null); // los coordinadores NO tienen registro

        if (coordinador.getId() != null) {
            // Es edición: traemos el objeto original de la DB
            Usuario existente = usuarioRepository.findById(coordinador.getId()).orElse(null);
            if (existente != null) {
                existente.setTdoc(coordinador.getTdoc());
                existente.setNumdoc(coordinador.getNumdoc());
                existente.setPapellido(coordinador.getPapellido());
                existente.setSapellido(coordinador.getSapellido());
                existente.setPnomb(coordinador.getPnomb());
                existente.setSnomb(coordinador.getSnomb());
                existente.setCorreo(coordinador.getCorreo());
                existente.setPassword(coordinador.getPassword());
                existente.setTelefono(coordinador.getTelefono());
                usuarioRepository.save(existente);
                return "redirect:/admin/coordinadores";
            }
        }

        // Si no existe ID, es creación
        usuarioRepository.save(coordinador);
        return "redirect:/admin/coordinadores";
    }


    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario user = usuarioRepository.findById(id).orElse(null);

        if (user == null || !"COORDINADOR".equalsIgnoreCase(user.getRol())) {
            return "redirect:/admin/coordinadores";
        }

        model.addAttribute("coordinador", user);
        return "admin/coordinadores_form";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        Usuario user = usuarioRepository.findById(id).orElse(null);

        if (user != null && "COORDINADOR".equalsIgnoreCase(user.getRol())) {
            usuarioRepository.delete(user);
        }

        return "redirect:/admin/coordinadores";
    }
}

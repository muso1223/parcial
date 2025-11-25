package com.example.demo.controller;

import com.example.demo.entidades.Usuario;
import com.example.demo.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Página principal: el login
    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("error", false);
        return "login";
    }

    // Procesar formulario de login
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("numdoc") String numdoc,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        var usuarioOpt = usuarioRepository.findByNumdoc(numdoc);

        if (usuarioOpt.isEmpty()) {
            model.addAttribute("error", true);
            model.addAttribute("mensaje", "Usuario no encontrado");
            return "login";
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getPassword().equals(password)) {
            model.addAttribute("error", true);
            model.addAttribute("mensaje", "Contraseña incorrecta");
            return "login";
        }

        // Guardar usuario en sesión
        session.setAttribute("usuarioLogueado", usuario);

        switch (usuario.getRol().toUpperCase()) {
            case "ADMIN":
                return "redirect:/admin/panel";

            case "COORDINADOR":
                return "redirect:/coordinador/panel";

            case "ALUMNO":
                return "redirect:/alumno/panel/" + usuario.getId();

            default:
                model.addAttribute("error", true);
                model.addAttribute("mensaje", "Rol no reconocido");
                return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("usuarioLogueado");
            session.invalidate();
        }
        return "redirect:/";
    }

}

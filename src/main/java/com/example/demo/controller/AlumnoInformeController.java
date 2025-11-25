package com.example.demo.controller;

import com.example.demo.entidades.Examen;
import com.example.demo.entidades.Usuario;
import com.example.demo.repository.ExamenRepository;
import com.example.demo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumno")
public class AlumnoInformeController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExamenRepository examenRepository;

    // Panel de alumno
    @GetMapping("/panel/{id}")
    public String panelAlumno(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        // Validar sesión y que sea el mismo alumno
        if (usuarioLogueado == null || !"ALUMNO".equalsIgnoreCase(usuarioLogueado.getRol())
                || !usuarioLogueado.getId().equals(id)) {
            return "redirect:/"; // Redirige al login
        }

        model.addAttribute("alumno", usuarioLogueado);
        model.addAttribute("titulo", "Panel de Alumno");
        return "alumnos/panel_alumno";
    }

    // Informe único
    @GetMapping("/informes/unico/{id}")
    public String informeUnico(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || !"ALUMNO".equalsIgnoreCase(usuarioLogueado.getRol())
                || !usuarioLogueado.getId().equals(id)) {
            return "redirect:/";
        }

        Examen examen = examenRepository.findByUsuario_Id(id).orElse(null);
        if (examen == null) {
            return "alumnos/informes/informe_vacio";
        }

        model.addAttribute("examen", examen);
        return "alumnos/informes/informe_alumno";
    }

    // Informe detallado
    @GetMapping("/informes/detallado/{id}")
    public String informeDetallado(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || !"ALUMNO".equalsIgnoreCase(usuarioLogueado.getRol())
                || !usuarioLogueado.getId().equals(id)) {
            return "redirect:/";
        }

        Examen examen = examenRepository.findByUsuario_Id(id).orElse(null);
        if (examen == null) {
            return "alumnos/informes/informe_vacio";
        }

        model.addAttribute("examen", examen);
        return "alumnos/informes/informe_detallado";
    }

    // Informe de beneficios
    @GetMapping("/informes/beneficios/{id}")
    public String informeBeneficios(@PathVariable Long id, HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado == null || !"ALUMNO".equalsIgnoreCase(usuarioLogueado.getRol())
                || !usuarioLogueado.getId().equals(id)) {
            return "redirect:/";
        }

        Examen examen = examenRepository.findByUsuario_Id(id).orElse(null);
        if (examen == null) {
            return "alumnos/informes/informe_vacio";
        }

        model.addAttribute("examen", examen);
        return "alumnos/informes/informe_beneficios";
    }
}

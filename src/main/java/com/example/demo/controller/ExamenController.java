package com.example.demo.controller;

import com.example.demo.entidades.Examen;
import com.example.demo.entidades.Usuario;
import com.example.demo.repository.ExamenRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coordinador/examenes")
public class ExamenController {

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LISTAR EXAMENES
 // LISTAR EXAMENES
    @GetMapping
    public String listar(Model model) {
        // Traer todos los examenes, anulados o no
        List<Examen> examenes = examenRepository.findAll();
        model.addAttribute("examenes", examenes);
        return "coordinador/examenes_list";
    }


    // FORMULARIO NUEVO EXAMEN
 // FORMULARIO NUEVO EXAMEN
    @GetMapping("/nuevo")
    public String nuevoExamen(Model model) {
        model.addAttribute("examen", new Examen());

        // Solo alumnos que no tengan examen
        List<Usuario> alumnosSinExamen = usuarioRepository.findByRol("ALUMNO").stream()
                .filter(alumno -> examenRepository.findByUsuario(alumno).isEmpty())
                .toList();

        model.addAttribute("alumnos", alumnosSinExamen);
        return "coordinador/examenes_form";
    }


    // GUARDAR (CREAR o EDITAR)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("examen") Examen examen,
                          @RequestParam("usuarioId") Long usuarioId) {

        Usuario alumno = usuarioRepository.findById(usuarioId).orElse(null);

        if (alumno == null || !"ALUMNO".equalsIgnoreCase(alumno.getRol())) {
            return "redirect:/coordinador/examenes";
        }

        // Asignamos el alumno
        examen.setUsuario(alumno);

        // El puntaje total viene directamente del formulario (no se calcula)
        examenRepository.save(examen);
        return "redirect:/coordinador/examenes";
    }

    // EDITAR EXAMEN
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(null);

        if (examen == null) {
            return "redirect:/coordinador/examenes";
        }

        model.addAttribute("examen", examen);
        model.addAttribute("alumnos", usuarioRepository.findByRol("ALUMNO"));
        return "coordinador/examenes_form";
    }

    // ELIMINAR EXAMEN
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        Examen examen = examenRepository.findById(id).orElse(null);
        if (examen != null) {
            examenRepository.delete(examen);
        }
        return "redirect:/coordinador/examenes";
    }
    
    @GetMapping("/informes/alumno/{id}")
    public String verInformeAlumno(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(null);
        if (examen == null) return "redirect:/coordinador/examenes";
        model.addAttribute("examen", examen);
        return "coordinador/informes/informe_alumno";
    }

    // Informe detallado
    @GetMapping("/informes/detallado/{id}")
    public String verInformeDetallado(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(null);
        if (examen == null) return "redirect:/coordinador/examenes";
        model.addAttribute("examen", examen);
        return "coordinador/informes/informe_detallado";
    }

    // Informe de beneficios
    @GetMapping("/informes/beneficios/{id}")
    public String verInformeBeneficios(@PathVariable Long id, Model model) {
        Examen examen = examenRepository.findById(id).orElse(null);
        if (examen == null) return "redirect:/coordinador/examenes";
        model.addAttribute("examen", examen);
        return "coordinador/informes/informe_beneficios";
    }
}

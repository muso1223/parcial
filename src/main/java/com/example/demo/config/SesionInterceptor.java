package com.example.demo.config;

import com.example.demo.entidades.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SesionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        // No hay sesión: redirigir al login
        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        String uri = request.getRequestURI();

        // Bloquear accesos por rol
        if (uri.startsWith("/alumno/") && !"ALUMNO".equalsIgnoreCase(usuario.getRol())) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        if (uri.startsWith("/coordinador/") && !"COORDINADOR".equalsIgnoreCase(usuario.getRol())) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        if (uri.startsWith("/admin/") && !"ADMIN".equalsIgnoreCase(usuario.getRol())) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        // Si todo está bien, deja pasar
        return true;
    }
}

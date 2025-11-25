package com.example.demo.controller;

import com.example.demo.entidades.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class SesionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // Obtener el usuario de la sesión
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogueado");

        // Si no hay usuario logueado, redirigir al login
        if (usuario == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }

        // Si hay usuario, permitir continuar
        return true;
    }
}

package com.example.demo.repository;

import com.example.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por número de documento (útil para login)
    Optional<Usuario> findByNumdoc(String numdoc);

    // Listar usuarios por rol (ADMIN, COORDINADOR, ALUMNO)
    List<Usuario> findByRol(String rol);

    // Buscar por registro (solo alumnos tienen registro)
    Optional<Usuario> findByRegistro(String registro);

    // Comprobaciones extra
    boolean existsByNumdoc(String numdoc);
    boolean existsByRegistro(String registro);
}

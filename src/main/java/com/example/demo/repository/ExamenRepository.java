package com.example.demo.repository;

import com.example.demo.entidades.Examen;
import com.example.demo.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    // Buscar todos los examenes de un usuario
    List<Examen> findByUsuario(Usuario usuario);

    // Si en tu modelo cada usuario tendrá un solo examen, usa esto:
    Optional<Examen> findByUsuarioNumdoc(String numdoc);

    // Alternativa por id del usuario
    Optional<Examen> findByUsuario_Id(Long usuarioId);

    // Buscar examenes por si están anulados o no
    List<Examen> findByAnulado(Boolean anulado);
}

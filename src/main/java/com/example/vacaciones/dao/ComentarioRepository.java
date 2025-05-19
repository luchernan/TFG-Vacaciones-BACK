package com.example.vacaciones.dao;

import com.example.vacaciones.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByViajeId(Integer viajeId);
}

package com.example.vacaciones.dao;

import com.example.vacaciones.entity.Destino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinoRepository extends JpaRepository<Destino, Integer> {
}

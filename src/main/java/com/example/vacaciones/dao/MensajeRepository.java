package com.example.vacaciones.dao;

import com.example.vacaciones.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByRemitenteId(Integer remitenteId);
    List<Mensaje> findByDestinatarioId(Integer destinatarioId);
}

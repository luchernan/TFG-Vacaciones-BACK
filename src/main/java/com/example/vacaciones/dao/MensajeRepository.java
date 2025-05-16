package com.example.vacaciones.dao;

import com.example.vacaciones.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findByRemitenteIdOrDestinatarioId(Long remitenteId, Long destinatarioId);
    List<Mensaje> findByDestinatarioIdAndLeidoFalse(Long destinatarioId);
}


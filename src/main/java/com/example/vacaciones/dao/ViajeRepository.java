package com.example.vacaciones.dao;


import com.example.vacaciones.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    List<Viaje> findByDestinoId(Integer destinoId);

}
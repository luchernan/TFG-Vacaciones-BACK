package com.example.vacaciones.dao;


import com.example.vacaciones.entity.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
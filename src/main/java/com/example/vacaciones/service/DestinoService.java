package com.example.vacaciones.service;

import com.example.vacaciones.dto.DestinoDto;

import java.util.List;

import com.example.vacaciones.dto.DestinoDto;
import java.util.List;

public interface DestinoService {
    /**
     * Obtiene un destino por su ID
     * @param id ID del destino a buscar
     * @return DestinoDto con la información del destino
     * @throws com.example.vacaciones.exception.NotFoundException si no se encuentra el destino
     */
    DestinoDto findById(Integer id);

    /**
     * Obtiene todos los destinos disponibles
     * @return Lista de DestinoDto con todos los destinos
     */
    List<DestinoDto> findAll();
}
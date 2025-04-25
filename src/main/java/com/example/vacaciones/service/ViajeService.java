package com.example.vacaciones.service;

import com.example.vacaciones.dto.ViajeDto;

import java.util.List;

public interface ViajeService {
    ViajeDto create(ViajeDto viajeDto);
    ViajeDto update(Integer id, ViajeDto dto);
    void deleteById(Integer id);
    ViajeDto findById(Integer id);
    List<ViajeDto> findAll();
}

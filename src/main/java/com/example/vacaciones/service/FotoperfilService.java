package com.example.vacaciones.service;

import com.example.vacaciones.dto.FotoperfilDto;

import java.util.List;

public interface FotoperfilService {
    List<FotoperfilDto> findAll();
    FotoperfilDto findById(Integer id);
    FotoperfilDto findByUrl(String url);
}
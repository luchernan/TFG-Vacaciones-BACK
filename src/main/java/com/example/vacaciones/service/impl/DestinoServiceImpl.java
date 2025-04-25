package com.example.vacaciones.service.impl;

import com.example.vacaciones.dto.*;
import com.example.vacaciones.entity.Destino;
import com.example.vacaciones.exception.NotFoundException;
import com.example.vacaciones.dao.DestinoRepository;
import com.example.vacaciones.service.DestinoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class DestinoServiceImpl implements DestinoService {

    private final DestinoRepository destinoRepository;

    @Autowired
    public DestinoServiceImpl(DestinoRepository destinoRepository) {
        this.destinoRepository = destinoRepository;
    }

    @Override
    public DestinoDto findById(Integer id) {
        Destino destino = destinoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Destino no encontrado con ID: " + id));

        return convertToDto(destino);
    }

    @Override
    public List<DestinoDto> findAll() {
        return destinoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DestinoDto convertToDto(Destino destino) {
        return new DestinoDto(
                destino.getId(),
                destino.getNombre(),
                destino.getPais(),
                destino.getDescripcion(),
                destino.getWikipediaSlug()
                //destino.getFotoDestino()
        );
    }
}
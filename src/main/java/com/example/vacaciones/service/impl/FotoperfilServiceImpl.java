package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.FotoperfilRepository;
import com.example.vacaciones.dto.FotoperfilDto;
import com.example.vacaciones.exception.NotFoundException;
import com.example.vacaciones.service.FotoperfilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.vacaciones.entity.Fotoperfil;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FotoperfilServiceImpl implements FotoperfilService {
    private final FotoperfilRepository fotoperfilRepository;
    private final ModelMapper mapper;

    @Autowired
    public FotoperfilServiceImpl(FotoperfilRepository fotoperfilRepository, ModelMapper mapper) {
        this.fotoperfilRepository = fotoperfilRepository;
        this.mapper = mapper;
    }

    @Override
    public List<FotoperfilDto> findAll() {
        return fotoperfilRepository.findAll().stream()
                .map(f -> mapper.map(f, FotoperfilDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FotoperfilDto findById(Integer id) {
        Fotoperfil foto = fotoperfilRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Foto de perfil no encontrada"));
        return mapper.map(foto, FotoperfilDto.class);
    }

    @Override
    public FotoperfilDto findByUrl(String url) {
        Fotoperfil foto = fotoperfilRepository.findByUrl(url)
                .orElseThrow(() -> new NotFoundException("Foto de perfil no encontrada por URL"));
        return mapper.map(foto, FotoperfilDto.class);
    }
}

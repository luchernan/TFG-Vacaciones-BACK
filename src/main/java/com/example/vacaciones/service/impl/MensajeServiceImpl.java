package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.MensajeRepository;
import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dto.MensajeDto;
import com.example.vacaciones.entity.Mensaje;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.service.MensajeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MensajeServiceImpl implements MensajeService {

    private final MensajeRepository mensajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper mapper;

    @Autowired
    public MensajeServiceImpl(MensajeRepository mensajeRepository, UsuarioRepository usuarioRepository, ModelMapper mapper) {
        this.mensajeRepository = mensajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public MensajeDto create(MensajeDto dto) {
        Usuario remitente = usuarioRepository.findById(dto.getRemitenteId()).orElseThrow();
        Usuario destinatario = usuarioRepository.findById(dto.getDestinatarioId()).orElseThrow();
        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente(remitente);
        mensaje.setDestinatario(destinatario);
        mensaje.setContenido(dto.getContenido());
        mensajeRepository.save(mensaje);
        return mapper.map(mensaje, MensajeDto.class);
    }

    @Override
    public List<MensajeDto> findAllByDestinatarioId(Integer destinatarioId) {
        return mensajeRepository.findByDestinatarioId(destinatarioId).stream()
                .map(m -> mapper.map(m, MensajeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MensajeDto> findAllByRemitenteId(Integer remitenteId) {
        return mensajeRepository.findByRemitenteId(remitenteId).stream()
                .map(m -> mapper.map(m, MensajeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MensajeDto marcarComoLeido(Integer id) {
        Mensaje mensaje = mensajeRepository.findById(id).orElseThrow();
        mensaje.setLeido(true);
        mensajeRepository.save(mensaje);
        return mapper.map(mensaje, MensajeDto.class);
    }
    @Override
    public MensajeDto findById(Integer id) {
        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensaje no encontrado"));
        return mapper.map(mensaje, MensajeDto.class);
    }

    @Override
    public List<MensajeDto> findAll() {
        return mensajeRepository.findAll().stream()
                .map(m -> mapper.map(m, MensajeDto.class))
                .collect(Collectors.toList());
    }
}

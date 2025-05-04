package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.DestinoRepository;
import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dao.ViajeRepository;
import com.example.vacaciones.dto.UsuarioDto;
import com.example.vacaciones.dto.ViajeDto;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.entity.Viaje;
import com.example.vacaciones.service.ViajeService;
import com.example.vacaciones.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeServiceImpl implements ViajeService {

    private final ViajeRepository viajeRepository;
    private final UsuarioRepository usuarioRepository;
    private final DestinoRepository destinoRepository;
    private final ModelMapper mapper;

    @Autowired
    public ViajeServiceImpl(ViajeRepository viajeRepository, UsuarioRepository usuarioRepository, DestinoRepository destinoRepository, ModelMapper mapper) {
        this.viajeRepository = viajeRepository;
        this.usuarioRepository = usuarioRepository;
        this.destinoRepository = destinoRepository;
        this.mapper = mapper;
    }
    @Override
    public List<ViajeDto> findByDestinoId(Integer destinoId) {
        List<Viaje> viajes = viajeRepository.findByDestinoId(destinoId);
        return viajes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ViajeDto create(ViajeDto dto) {
        Viaje viaje = new Viaje();
        viaje.setUsuario(usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new NotFoundException("Usuario no encontrado")));
        viaje.setDestino(destinoRepository.findById(dto.getDestinoId()).orElseThrow(() -> new NotFoundException("Destino no encontrado")));
        viaje.setFechaInicio(dto.getFechaInicio());
        viaje.setFechaFin(dto.getFechaFin());

        Viaje guardado = viajeRepository.save(viaje);
        return convertToDto(guardado);
    }

    @Override
    public void deleteById(Integer id) {
        viajeRepository.deleteById(id);
    }

    @Override
    public ViajeDto findById(Integer id) {
        Viaje viaje = viajeRepository.findById(id).orElseThrow(() -> new NotFoundException("Viaje no encontrado"));
        return convertToDto(viaje);
    }

    @Override
    public List<ViajeDto> findAll() {
        return viajeRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ViajeDto update(Integer id, ViajeDto dto) {
        Viaje viaje = viajeRepository.findById((int) id.longValue())
                .orElseThrow(() -> new NotFoundException("Viaje no encontrado con id " + id));

        viaje.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado")));
        viaje.setDestino(destinoRepository.findById(dto.getDestinoId())
                .orElseThrow(() -> new NotFoundException("Destino no encontrado")));
        viaje.setFechaInicio(dto.getFechaInicio());
        viaje.setFechaFin(dto.getFechaFin());

        viaje = viajeRepository.save(viaje);
        return mapper.map(viaje, ViajeDto.class);
    }

    private ViajeDto convertToDto(Viaje viaje) {
        System.out.println("Fecha inicio: " + viaje.getFechaInicio());
        System.out.println("Fecha fin: " + viaje.getFechaFin());
        return new ViajeDto(
                viaje.getId(),
                viaje.getUsuario().getId(),
                viaje.getDestino().getId(),
                viaje.getFechaInicio(),
                viaje.getFechaFin(),
                new UsuarioDto(viaje.getUsuario())        );
    }
}

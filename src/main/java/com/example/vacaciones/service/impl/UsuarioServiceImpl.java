package com.example.vacaciones.service.impl;

import com.example.vacaciones.dao.UsuarioRepository;
import com.example.vacaciones.dto.UsuarioDto;
import com.example.vacaciones.entity.Usuario;
import com.example.vacaciones.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper mapper;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ModelMapper mapper) {
        this.usuarioRepository = usuarioRepository;
        this.mapper = mapper;
    }

    @Override
    public UsuarioDto create(UsuarioDto usuarioDto) {
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);
        usuarioRepository.save(usuario);
        return mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public UsuarioDto login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
        return mapper.map(usuario, UsuarioDto.class);
    }

    @Override
    public int calcularEdad(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getFechaNacimiento() == null) return -1;
        return Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
    }
}

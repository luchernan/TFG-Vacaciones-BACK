package com.example.vacaciones.service;


import com.example.vacaciones.dto.UsuarioDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    UsuarioDto findById(Long id);
    UsuarioDto create(UsuarioDto usuarioDto);
    UsuarioDto updateById(Integer id, UsuarioDto usuarioDto);
    UsuarioDto login(String email, String password);
    int calcularEdad(Integer usuarioId);
    UsuarioDto alternarTipoUsuario(Integer idUsuario);
    List<UsuarioDto> filtrarUsuarios(String genero, Integer edadMinima, Integer edadMaxima, String idioma);
//    Optional<UsuarioDto> findByEmailAndPassword(String email, String password);
    void updateProfilePhoto(String email, MultipartFile file) throws IOException;
}
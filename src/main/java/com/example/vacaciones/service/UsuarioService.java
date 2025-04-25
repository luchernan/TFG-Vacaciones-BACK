package com.example.vacaciones.service;


import com.example.vacaciones.dto.UsuarioDto;
public interface UsuarioService {
    UsuarioDto create(UsuarioDto usuarioDto);
    UsuarioDto login(String email, String password);
    int calcularEdad(Integer usuarioId);
}
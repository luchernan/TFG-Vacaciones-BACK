package com.example.vacaciones.service;

import com.example.vacaciones.dto.ComentarioDto;

import java.util.List;

public interface ComentarioService {
    ComentarioDto crearComentario(ComentarioDto comentarioDto);
    List<ComentarioDto> obtenerComentariosPorViaje(Integer viajeId);
    void eliminarComentario(Integer id);
}

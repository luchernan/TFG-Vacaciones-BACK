package com.example.vacaciones.service;
import java.util.List;

import com.example.vacaciones.dto.MensajeDto;
import com.example.vacaciones.entity.Mensaje;
public interface MensajeService {
    void enviarMensaje(MensajeDto mensajeDto);
    List<MensajeDto> obtenerConversacionesUsuario(Long usuarioId);
    List<MensajeDto> obtenerMensajesEntreUsuarios(Long usuario1Id, Long usuario2Id, Long viajeId);
}


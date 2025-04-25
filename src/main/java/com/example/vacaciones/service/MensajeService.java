package com.example.vacaciones.service;



import com.example.vacaciones.dto.MensajeDto;
import java.util.List;

public interface MensajeService {
    MensajeDto create(MensajeDto mensajeDto);
    List<MensajeDto> findAllByDestinatarioId(Integer destinatarioId);
    List<MensajeDto> findAllByRemitenteId(Integer remitenteId);
    MensajeDto marcarComoLeido(Integer id);
    MensajeDto findById(Integer id);
    List<MensajeDto> findAll();
}
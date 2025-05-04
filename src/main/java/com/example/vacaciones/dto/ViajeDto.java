package com.example.vacaciones.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.vacaciones.entity.Viaje}
 */
@Value
public class ViajeDto implements Serializable {
    Integer id;
    Integer usuarioId;
    Integer destinoId;
    LocalDate fechaInicio;
    LocalDate fechaFin;
    private UsuarioDto usuario;
}

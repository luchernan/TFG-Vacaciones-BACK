package com.example.vacaciones.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.example.vacaciones.entity.Comentario}
 */

@Getter
@Setter
@NoArgsConstructor
public class ComentarioDto implements Serializable {
    private Integer id;
    @NotNull
    private String contenido;
    private Instant fecha;
    private Integer usuarioId;
    private Integer viajeId;

}

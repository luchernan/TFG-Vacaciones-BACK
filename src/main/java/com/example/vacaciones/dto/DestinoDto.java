package com.example.vacaciones.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.vacaciones.entity.Destino}
 */
import lombok.Value;
import lombok.With;
import org.springframework.lang.Nullable;

@Value
@With
public class DestinoDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String nombre;
    @Size(max = 100)
    String pais;
    String descripcion;
    @Size(max = 100)
    String wikipediaSlug;
}

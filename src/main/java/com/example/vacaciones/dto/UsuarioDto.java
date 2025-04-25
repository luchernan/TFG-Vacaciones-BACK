package com.example.vacaciones.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link com.example.vacaciones.entity.Usuario}
 */
@Value
public class UsuarioDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 100)
    String email;
    @NotNull
    @Size(max = 255)
    String password;
    LocalDate fechaNacimiento;
    @NotNull
    @Size(max = 100)
    String nombre;
    String genero;
    String descripcion;
    @NotNull
    String tipoUsuario;
    @Size(max = 50)
    String idioma;
    @Size(max = 100)
    String pais;
    @Size(max = 100)
    String ciudadLocal;
    @NotNull
    @Size(max = 255)
    String fotoPerfil;
    Set<MensajeDto> mensajesRemitente;
    Set<MensajeDto> mensajesDestinatario;
}
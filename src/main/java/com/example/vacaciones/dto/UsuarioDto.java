package com.example.vacaciones.dto;

import com.example.vacaciones.entity.Usuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

/**
 * DTO for {@link com.example.vacaciones.entity.Usuario}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {

    public UsuarioDto(Integer id, @Size(max = 100) @NotNull String nombre) {
    }

    public UsuarioDto(Integer id, @Size(max = 100) @NotNull String nombre, LocalDate fechaNacimiento, String genero, @NotNull @Size(max = 255) String fotoPerfil) {
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    private Integer id;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 255)
    private String password;

    private LocalDate fechaNacimiento;

    @NotNull
    @Size(max = 100)
    private String nombre;

    private String genero;
    private String descripcion;

    @NotNull
    private String tipoUsuario;

    @Size(max = 50)
    private String idioma;

    @Size(max = 100)
    private String pais;

    @Size(max = 100)
    private String ciudadLocal;

    @NotNull
    @Size(max = 255)
    private String fotoPerfil;

    @Min(0)
    @Max(150)
    private Integer edad;



    private Set<MensajeDto> mensajesRemitente;
    private Set<MensajeDto> mensajesDestinatario;

    public UsuarioDto(Usuario usuario) {
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.nombre = usuario.getNombre();
        this.genero = usuario.getGenero();
        this.descripcion = usuario.getDescripcion();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.idioma = usuario.getIdioma();
        this.pais = usuario.getPais();
        this.ciudadLocal = usuario.getCiudadLocal();
        this.fotoPerfil = usuario.getFotoPerfil();
        if (usuario.getFechaNacimiento() != null) {
            this.edad = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
        } else {
            this.edad = null;
        }

    }

}


package com.example.vacaciones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ColumnDefault("'otro'")
    @Lob
    @Column(name = "genero")
    private String genero;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Lob
    @Column(name = "instagram")
    private String instagram;

    @NotNull
    @Lob
    @Column(name = "tipo_usuario", nullable = false)
    private String tipoUsuario;

    @Size(max = 50)
    @Column(name = "idioma", length = 50)
    private String idioma;

    @Size(max = 100)
    @Column(name = "pais", length = 100)
    private String pais;

    @Size(max = 100)
    @Column(name = "ciudad_local", length = 100)
    private String ciudadLocal;

    @NotNull
    @Size(max = 255)
    @Column(name = "fotoPerfil", nullable = false)
    private String fotoPerfil;

    @OneToMany(mappedBy = "remitente")
    private Set<Mensaje> mensajesRemitente = new LinkedHashSet<>();

    @OneToMany(mappedBy = "destinatario")
    private Set<Mensaje> mensajesDestinatario = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Viaje> viajes = new ArrayList<>();

}
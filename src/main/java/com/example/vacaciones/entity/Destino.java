package com.example.vacaciones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "destinos")
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 100)
    @Column(name = "pais", length = 100)
    private String pais;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Size(max = 100)
    @Column(name = "wikipedia_slug", length = 100)
    private String wikipediaSlug;

    @Size(max = 255)
    @Column(name = "fotoDestino")
    private String fotoDestino;

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Viaje> viajes = new ArrayList<>();
}
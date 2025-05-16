package com.example.vacaciones.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDateTime;
@Entity
@Table(name = "mensajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "remitente_id", nullable = false)
    private Usuario remitente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Usuario destinatario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viaje_id", nullable = false)
    private Viaje viaje;

    @Lob
    @Column(nullable = false)
    private String contenido;

    @Column(name = "fecha_envio", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaEnvio = LocalDateTime.now();

    private boolean leido = false;
}

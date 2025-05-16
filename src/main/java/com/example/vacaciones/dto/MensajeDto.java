package com.example.vacaciones.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.vacaciones.entity.Mensaje}
 */

import lombok.Getter;
import lombok.Setter;

public class MensajeDto {
    private Integer id;
    private Integer remitenteId;
    private Integer destinatarioId;
    private Integer viajeId;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private Boolean leido;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getRemitenteId() { return remitenteId; }
    public void setRemitenteId(Integer remitenteId) { this.remitenteId = remitenteId; }

    public Integer getDestinatarioId() { return destinatarioId; }
    public void setDestinatarioId(Integer destinatarioId) { this.destinatarioId = destinatarioId; }

    public Integer getViajeId() { return viajeId; }
    public void setViajeId(Integer viajeId) { this.viajeId = viajeId; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public Boolean getLeido() { return leido; }
    public void setLeido(Boolean leido) { this.leido = leido; }
}


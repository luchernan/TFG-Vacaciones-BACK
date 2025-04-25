package com.example.vacaciones.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.vacaciones.entity.Mensaje}
 */
public class MensajeDto {
    private Integer id;
    private Integer remitenteId;
    private Integer destinatarioId;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private Boolean leido;

    public MensajeDto() {}

    public MensajeDto(Integer id, Integer remitenteId, Integer destinatarioId, String contenido, LocalDateTime fechaEnvio, Boolean leido) {
        this.id = id;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.leido = leido;
    }

    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getRemitenteId() { return remitenteId; }
    public void setRemitenteId(Integer remitenteId) { this.remitenteId = remitenteId; }

    public Integer getDestinatarioId() { return destinatarioId; }
    public void setDestinatarioId(Integer destinatarioId) { this.destinatarioId = destinatarioId; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public Boolean getLeido() { return leido; }
    public void setLeido(Boolean leido) { this.leido = leido; }
}
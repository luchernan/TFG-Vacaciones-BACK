package com.example.vacaciones.service.impl;

public class MensajeRequest {
    private Integer remitenteId;
    private Integer destinatarioId;
    private Integer viajeId;
    private String contenido;


    public Integer getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(Integer remitenteId) {
        this.remitenteId = remitenteId;
    }


    public Integer getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Integer destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Integer getViajeId() {
        return viajeId;
    }

    public void setViajeId(Integer viajeId) {
        this.viajeId = viajeId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}


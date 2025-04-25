package com.example.vacaciones.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.vacaciones.entity.Fotoperfil}
 */
public class FotoperfilDto {

    private Integer id;
    private String url;

    // Constructor vacío obligatorio para ModelMapper
    public FotoperfilDto() {
    }

    public FotoperfilDto(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

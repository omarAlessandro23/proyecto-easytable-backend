package com.example.easytable.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public class RoleDTO {
    @Schema(example = "USER", description = "Rol asignado al usuario") // <-- Agrega esto
    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

package com.condominio.cibertec.business.api.dto;

import jakarta.validation.constraints.NotNull;

public record RegistroAccesoRequestDto(

        @NotNull(message = "Debe indicar el visitante")
        Integer idVisitante,

        @NotNull(message = "Debe indicar el departamento visitado")
        Integer idDepartamento,

        @NotNull(message = "Debe indicar el trabajador que registra el acceso")
        Integer idTrabajador
) {
}
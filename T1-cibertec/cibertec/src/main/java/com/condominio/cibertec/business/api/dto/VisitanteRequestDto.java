package com.condominio.cibertec.business.api.dto;

import jakarta.validation.constraints.NotBlank;

public record VisitanteRequestDto(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        String apellido,

        @NotBlank(message = "El DNI es obligatorio")
        String dni,

        String motivo
) {
}
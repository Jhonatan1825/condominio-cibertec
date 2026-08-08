package com.condominio.cibertec.business.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TrabajadorRequestDto(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no debe superar los 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100, message = "El apellido no debe superar los 100 caracteres")
        String apellido,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos")
        String dni,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "^9\\d{8}$", message = "El teléfono debe tener 9 dígitos y comenzar con 9")
        String telefono,

        String correo,

        @NotBlank(message = "El cargo es obligatorio")
        String cargo,

        @NotBlank(message = "El turno es obligatorio")
        String turno
) {
}
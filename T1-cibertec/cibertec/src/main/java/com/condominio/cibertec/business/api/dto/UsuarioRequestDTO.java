package com.condominio.cibertec.business.api.dto;

import jakarta.validation.constraints.*;

public record UsuarioRequestDTO(

        @NotNull(message = "El rol es obligatorio")
        Integer idRol,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100)
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100)
        String apellido,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(
                regexp="^\\d{8}$",
                message="El DNI debe contener 8 dígitos"
        )
        String dni,

        @Pattern(
                regexp="^9\\d{8}$",
                message="El teléfono debe comenzar con 9 y tener 9 dígitos"
        )
        String telefono,

        @Email(message="Correo inválido")
        @Size(max=150)
        String correo,

        @NotBlank(message="La contraseña es obligatoria")
        String passwordHash,

        Boolean estado

) {
}

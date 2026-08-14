package com.condominio.cibertec.Security.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistroUsuarioRequestDto(

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        @Size(
                max = 150,
                message = "El correo no debe superar los 150 caracteres"
        )
        String correo,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(
                min = 8,
                max = 72,
                message = "La contraseña debe tener entre 8 y 72 caracteres"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
                message = "La contraseña debe contener una mayúscula, una minúscula y un número"
        )
        String password,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(
                max = 100,
                message = "El nombre no debe superar los 100 caracteres"
        )
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(
                max = 100,
                message = "El apellido no debe superar los 100 caracteres"
        )
        String apellido,

        @NotBlank(message = "El DNI es obligatorio")
        @Pattern(
                regexp = "^\\d{8}$",
                message = "El DNI debe tener 8 dígitos numéricos"
        )
        String dni,

        @Pattern(
                regexp = "^$|^\\d{6,20}$",
                message = "El teléfono debe contener solo números"
        )
        String telefono,

        @NotBlank(message = "El rol es obligatorio")
        String rol
) {
}
package com.condominio.cibertec.Security.api.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record UsuarioResponseDto(

        Integer id,

        String correo,

        String nombre,

        boolean activo,

        LocalDateTime fechaCreacion,

        Set<String> roles
) {
}

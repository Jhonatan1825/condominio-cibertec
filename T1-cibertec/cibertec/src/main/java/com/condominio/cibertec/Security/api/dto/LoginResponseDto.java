package com.condominio.cibertec.Security.api.dto;

import java.util.Set;

public record LoginResponseDto(

        String token,

        String tipo,

        long expiresIn,

        String correo,

        Set<String> roles
) {
}

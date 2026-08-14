package com.condominio.cibertec.Security.api.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDto(

        Integer idUsuario,

        String correo,

        String nombre,

        String apellido,

        String dni,

        String telefono,

        Boolean estado,

        LocalDateTime createdAt,

        String rol

) {
}
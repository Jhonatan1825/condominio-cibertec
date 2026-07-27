package com.condominio.cibertec.api.dto;

public record UsuarioResponseDTO(

        Integer idUsuario,

        String nombre,

        String apellido,

        String dni,

        String telefono,

        String correo,

        String rol,

        Boolean estado

) {
}
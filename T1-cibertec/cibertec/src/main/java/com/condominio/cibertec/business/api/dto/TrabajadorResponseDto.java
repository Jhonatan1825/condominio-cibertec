package com.condominio.cibertec.business.api.dto;

import java.time.LocalDateTime;

public record TrabajadorResponseDto(
        Integer idTrabajador,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String correo,
        String cargo,
        String turno,
        Boolean estado,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
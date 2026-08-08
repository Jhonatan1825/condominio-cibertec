package com.condominio.cibertec.business.api.dto;

public record VisitanteResponseDto(
        Integer idVisitante,
        String nombre,
        String apellido,
        String dni,
        String motivo
) {
}
package com.condominio.cibertec.business.api.dto;

import java.time.LocalDateTime;

public record RegistroAccesoResponseDto(
        Integer idRegistro,
        Integer idVisitante,
        String nombreVisitante,
        Integer idDepartamento,
        String numeroDepartamento,
        Integer idTrabajador,
        String nombreTrabajador,
        LocalDateTime fechaHoraIngreso,
        LocalDateTime fechaHoraSalida
) {
}
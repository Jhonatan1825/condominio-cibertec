package com.condominio.cibertec.business.api.dto;

import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CuotaMensualResponseDto(

        Integer idCuotaMensual,
        Integer idDepartamento,
        String departamento,
        BigDecimal montoBase,
        BigDecimal montoMora,
        BigDecimal montoTotal,
        LocalDate fechaEmision,
        LocalDate fechaVencimiento,
        EstadoCuota estado

) {
}

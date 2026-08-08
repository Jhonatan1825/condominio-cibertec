package com.condominio.cibertec.business.api.dto;

import com.condominio.cibertec.business.data.entity.enums.EstadoPago;
import com.condominio.cibertec.business.data.entity.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagoMantenimientoResponseDto(

        Integer idPagoMantenimiento,

        Integer idCuotaMensual,

        Integer idDepartamento,

        String departamento,

        Integer idUsuario,

        String usuario,

        MetodoPago metodoPago,

        BigDecimal monto,

        LocalDateTime fechaPago,

        EstadoPago estado

) {
}

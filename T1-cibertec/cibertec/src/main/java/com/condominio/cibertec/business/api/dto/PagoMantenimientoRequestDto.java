package com.condominio.cibertec.business.api.dto;

import com.condominio.cibertec.business.data.entity.enums.MetodoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PagoMantenimientoRequestDto(

        @NotNull(message = "La cuota mensual es obligatoria")
        @Positive(message = "El ID de la cuota debe ser positivo")
        Integer idCuotaMensual,

        @NotNull(message = "El usuario es obligatorio")
        @Positive(message = "El ID del usuario debe ser positivo")
        Integer idUsuario,

        @NotNull(message = "El método de pago es obligatorio")
        MetodoPago metodoPago,

        @NotNull(message = "El monto es obligatorio")
        @DecimalMin(
                value = "0.01",
                message = "El monto debe ser mayor que cero"
        )
        @Digits(
                integer = 8,
                fraction = 2,
                message = "El monto debe tener hasta 8 enteros y 2 decimales"
        )
        BigDecimal monto

) {
}

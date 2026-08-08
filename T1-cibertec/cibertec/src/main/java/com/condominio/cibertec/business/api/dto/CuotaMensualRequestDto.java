package com.condominio.cibertec.business.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CuotaMensualRequestDto(


        @NotNull(message = "El departamento es obligatorio")
        @Positive(message = "El ID del departamento debe ser positivo")
        Integer idDepartamento,

        @NotNull(message = "El monto base es obligatorio")
        @DecimalMin(
                value = "0.01",
                message = "El monto base debe ser mayor que cero"
        )
        @Digits(
                integer = 8,
                fraction = 2,
                message = "El monto base debe tener hasta 8 enteros y 2 decimales"
        )
        BigDecimal montoBase,

        @DecimalMin(
                value = "0.00",
                message = "El monto de mora no puede ser negativo"
        )
        @Digits(
                integer = 8,
                fraction = 2,
                message = "El monto de mora debe tener hasta 8 enteros y 2 decimales"
        )
        BigDecimal montoMora,

        @NotNull(message = "La fecha de emisión es obligatoria")
        LocalDate fechaEmision,

        @NotNull(message = "La fecha de vencimiento es obligatoria")
        LocalDate fechaVencimiento

) {
}

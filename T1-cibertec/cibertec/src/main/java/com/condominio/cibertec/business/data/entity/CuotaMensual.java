package com.condominio.cibertec.business.data.entity;

import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "cuota_mensual")
public class CuotaMensual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuota_mensual")
    private Integer idCuotaMensual;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_departamento",
            nullable = false
    )
    private Departamento departamento;

    @Column(
            name = "monto_base",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal montoBase;

    @Column(
            name = "monto_mora",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal montoMora;

    @Column(
            name = "monto_total",
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal montoTotal;

    @Column(
            name = "fecha_emision",
            nullable = false
    )
    private LocalDate fechaEmision;

    @Column(
            name = "fecha_vencimiento",
            nullable = false
    )
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private EstadoCuota estado;
}
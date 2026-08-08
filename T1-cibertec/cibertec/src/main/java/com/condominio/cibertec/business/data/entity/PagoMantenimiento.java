package com.condominio.cibertec.business.data.entity;

import com.condominio.cibertec.business.data.entity.enums.EstadoPago;
import com.condominio.cibertec.business.data.entity.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pago_mantenimiento")
public class PagoMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_mantenimiento")
    private Integer idPagoMantenimiento;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_cuota_mensual",
            nullable = false,
            unique = true
    )
    private CuotaMensual cuotaMensual;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_usuario",
            nullable = false
    )
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "metodo_pago",
            nullable = false,
            length = 30
    )
    private MetodoPago metodoPago;

    @Column(
            nullable = false,
            precision = 10,
            scale = 2
    )
    private BigDecimal monto;

    @Column(
            name = "fecha_pago",
            nullable = false
    )
    private LocalDateTime fechaPago;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private EstadoPago estado;
}


package com.condominio.cibertec.business.data.repository;

import com.condominio.cibertec.business.data.entity.PagoMantenimiento;
import com.condominio.cibertec.business.data.entity.enums.EstadoPago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PagoMantenimientoRepository
        extends JpaRepository<PagoMantenimiento, Integer> {

    @EntityGraph(
            attributePaths = {
                    "cuotaMensual",
                    "cuotaMensual.departamento",
                    "usuario"
            }
    )
    Optional<PagoMantenimiento> findOneByIdPagoMantenimiento(
            Integer idPagoMantenimiento
    );

    Page<PagoMantenimiento> findByUsuarioIdUsuario(
            Integer idUsuario,
            Pageable pageable
    );

    Page<PagoMantenimiento> findByEstado(
            EstadoPago estado,
            Pageable pageable
    );

    Page<PagoMantenimiento> findByFechaPagoBetween(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable
    );

    boolean existsByCuotaMensualIdCuotaMensual(
            Integer idCuotaMensual
    );
}
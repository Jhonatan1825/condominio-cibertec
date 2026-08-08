package com.condominio.cibertec.business.data.repository;

import com.condominio.cibertec.business.data.entity.CuotaMensual;
import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CuotaMensualRepository extends JpaRepository<CuotaMensual, Integer> {

    @EntityGraph(
            attributePaths = {
                    "departamento"
            }
    )
    Optional<CuotaMensual> findOneByIdCuotaMensual(
            Integer idCuotaMensual
    );

    Page<CuotaMensual> findByDepartamentoIdDepartamento(
            Integer idDepartamento,
            Pageable pageable
    );

    Page<CuotaMensual> findByEstado(
            EstadoCuota estado,
            Pageable pageable
    );

    Page<CuotaMensual> findByFechaVencimientoBetween(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Pageable pageable
    );

    boolean existsByDepartamentoIdDepartamentoAndFechaEmisionBetween(
            Integer idDepartamento,
            LocalDate fechaInicio,
            LocalDate fechaFin
    );
}
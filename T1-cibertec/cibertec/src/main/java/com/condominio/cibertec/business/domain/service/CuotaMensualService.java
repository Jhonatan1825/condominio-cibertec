package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.api.dto.CuotaMensualRequestDto;
import com.condominio.cibertec.business.api.dto.CuotaMensualResponseDto;
import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CuotaMensualService {

    List<CuotaMensualResponseDto> obtenerTodas();

    CuotaMensualResponseDto obtenerPorId(Integer id);

    CuotaMensualResponseDto crear(CuotaMensualRequestDto requestDto);

    CuotaMensualResponseDto actualizar(Integer id, CuotaMensualRequestDto requestDto);

    void eliminar(Integer id);

    Page<CuotaMensualResponseDto> obtenerPorDepartamento(
            Integer idDepartamento,
            Pageable pageable
    );

    Page<CuotaMensualResponseDto> obtenerPorEstado(
            EstadoCuota estado,
            Pageable pageable
    );

    Page<CuotaMensualResponseDto> obtenerEntreFechas(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Pageable pageable
    );
}
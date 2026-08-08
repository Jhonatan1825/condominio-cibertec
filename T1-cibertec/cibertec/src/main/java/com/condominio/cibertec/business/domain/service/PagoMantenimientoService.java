package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.api.dto.PagoMantenimientoRequestDto;
import com.condominio.cibertec.business.api.dto.PagoMantenimientoResponseDto;
import com.condominio.cibertec.business.data.entity.enums.EstadoPago;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface PagoMantenimientoService {

    List<PagoMantenimientoResponseDto> obtenerTodos();

    PagoMantenimientoResponseDto obtenerPorId(Integer id);

    PagoMantenimientoResponseDto registrar(PagoMantenimientoRequestDto requestDto);

    void anular(Integer id);

    Page<PagoMantenimientoResponseDto> obtenerPorUsuario(
            Integer idUsuario,
            Pageable pageable
    );

    Page<PagoMantenimientoResponseDto> obtenerPorEstado(
            EstadoPago estado,
            Pageable pageable
    );

    Page<PagoMantenimientoResponseDto> obtenerEntreFechas(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable
    );
}

package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.api.dto.VisitanteRequestDto;
import com.condominio.cibertec.business.api.dto.VisitanteResponseDto;

import java.util.List;

public interface VisitanteService {
    List<VisitanteResponseDto> obtenerTodos();
    VisitanteResponseDto obtenerPorId(Integer id);
    VisitanteResponseDto crear(VisitanteRequestDto requestDto);
    VisitanteResponseDto actualizar(Integer id, VisitanteRequestDto requestDto);
    void eliminar(Integer id);
}
package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.api.dto.RegistroAccesoRequestDto;
import com.condominio.cibertec.business.api.dto.RegistroAccesoResponseDto;

import java.util.List;

public interface RegistroAccesoService {
    List<RegistroAccesoResponseDto> obtenerTodos();
    RegistroAccesoResponseDto obtenerPorId(Integer id);
    RegistroAccesoResponseDto registrarIngreso(RegistroAccesoRequestDto requestDto);
    RegistroAccesoResponseDto registrarSalida(Integer idRegistro);
}
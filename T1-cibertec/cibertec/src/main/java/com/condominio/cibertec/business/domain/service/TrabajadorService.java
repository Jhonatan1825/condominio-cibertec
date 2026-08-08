package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.business.api.dto.TrabajadorResponseDto;
import com.condominio.cibertec.business.data.entity.Trabajador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TrabajadorService {
    List<TrabajadorResponseDto> obtenerTodos();
    TrabajadorResponseDto obtenerPorId(Integer id);
    TrabajadorResponseDto crear(TrabajadorRequestDto requestDto);
    TrabajadorResponseDto actualizar(Integer id, TrabajadorRequestDto requestDto);
    void eliminar(Integer id);
    TrabajadorResponseDto buscarPorDni(String dni);
    Page<TrabajadorResponseDto> consultar(String nombre, String cargo, Boolean estado, Pageable pageable);
}
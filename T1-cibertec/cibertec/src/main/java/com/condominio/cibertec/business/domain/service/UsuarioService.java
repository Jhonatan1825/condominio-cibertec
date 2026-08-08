package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.api.dto.UsuarioRequestDTO;
import com.condominio.cibertec.business.api.dto.UsuarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> obtenerTodos();

    UsuarioResponseDTO obtenerPorId(Integer id);

    UsuarioResponseDTO crear(UsuarioRequestDTO request);

    UsuarioResponseDTO actualizar(Integer id, UsuarioRequestDTO request);

    void eliminar(Integer id);

    Page<UsuarioResponseDTO> consultar(
            String nombre,
            Boolean estado,
            Pageable pageable
    );
}
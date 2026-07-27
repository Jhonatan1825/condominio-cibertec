package com.condominio.cibertec.domain.service;

import com.condominio.cibertec.data.entity.DepartamentoPropietario;
import java.util.List;

public interface DepartamentoPropietarioService {
    List<DepartamentoPropietario> obtenerTodos();
    DepartamentoPropietario obtenerPorId(Integer id);
    DepartamentoPropietario crear(DepartamentoPropietario dp);
    DepartamentoPropietario actualizar(Integer id, DepartamentoPropietario dp);
    void eliminar(Integer id);
    List<DepartamentoPropietario> buscarPorPropietario(Integer idUsuario);
}
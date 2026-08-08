package com.condominio.cibertec.business.domain.service;

import com.condominio.cibertec.business.data.entity.Departamento;

import java.util.List;

public interface DepartamentoService {
    List<Departamento> obtenerTodos();
    Departamento obtenerPorId(Integer id);
    Departamento crear(Departamento departamento);
    Departamento actualizar(Integer id, Departamento departamento);
    void eliminar(Integer id);
}
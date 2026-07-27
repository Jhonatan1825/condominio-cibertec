package com.condominio.cibertec.domain.service;

import com.condominio.cibertec.data.entity.Departamento;

import java.util.List;

public interface DepartamentoService {
    List<Departamento> obtenerTodos();
    Departamento obtenerPorId(Integer id);
    Departamento crear(Departamento departamento);
    Departamento actualizar(Integer id, Departamento departamento);
    void eliminar(Integer id);
}
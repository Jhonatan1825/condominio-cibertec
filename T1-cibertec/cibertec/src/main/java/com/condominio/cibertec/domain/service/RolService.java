package com.condominio.cibertec.domain.service;

import com.condominio.cibertec.data.entity.Rol;

import java.util.List;

public interface RolService {
    List<Rol> obtenerTodos();
    Rol obtenerPorId(Integer id);
    Rol crear(Rol rol);
    Rol actualizar(Integer id, Rol rol);
    void eliminar(Integer id);
}
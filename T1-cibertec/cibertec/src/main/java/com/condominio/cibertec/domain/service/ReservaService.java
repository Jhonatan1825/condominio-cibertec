package com.condominio.cibertec.domain.service;

import com.condominio.cibertec.data.entity.Reserva;

import java.util.List;

public interface ReservaService {
    List<Reserva> obtenerTodas();
    Reserva obtenerPorId(Integer id);
    Reserva crear(Reserva reserva);
    Reserva actualizar(Integer id, Reserva reserva);
    void eliminar(Integer id);
    List<Reserva> buscarPorEstado(String estado);
}
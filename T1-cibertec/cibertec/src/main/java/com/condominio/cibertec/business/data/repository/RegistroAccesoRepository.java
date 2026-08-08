package com.condominio.cibertec.business.data.repository;

import com.condominio.cibertec.business.data.entity.RegistroAcceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroAccesoRepository extends JpaRepository<RegistroAcceso, Integer> {

    // Busca si el visitante ya tiene un ingreso sin salida registrada
    Optional<RegistroAcceso> findByVisitante_IdVisitanteAndFechaHoraSalidaIsNull(Integer idVisitante);

    List<RegistroAcceso> findByDepartamento_IdDepartamento(Integer idDepartamento);
}
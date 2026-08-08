package com.condominio.cibertec.business.data.repository;

import com.condominio.cibertec.business.data.entity.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Integer> {
}
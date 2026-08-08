package com.condominio.cibertec.business.data.repository;

import com.condominio.cibertec.business.data.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Integer> {
}
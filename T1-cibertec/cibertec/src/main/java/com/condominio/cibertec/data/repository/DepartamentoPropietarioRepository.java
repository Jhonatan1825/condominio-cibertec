package com.condominio.cibertec.data.repository;

import com.condominio.cibertec.data.entity.DepartamentoPropietario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartamentoPropietarioRepository extends JpaRepository<DepartamentoPropietario, Integer> {
    List<DepartamentoPropietario> findByPropietarioIdUsuario(Integer idUsuario);
}

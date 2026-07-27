package com.condominio.cibertec.data.repository;

import com.condominio.cibertec.data.entity.Trabajador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {

    Optional<Trabajador> findByDni(String dni);

    boolean existsByDni(String dni);

    boolean existsByDniAndIdTrabajadorNot(String dni, Integer idTrabajador);

    @Query("""
            SELECT t
            FROM Trabajador t
            WHERE (:nombre IS NULL OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
            AND (:cargo IS NULL OR t.cargo = :cargo)
            AND (:estado IS NULL OR t.estado = :estado)
            """)
    Page<Trabajador> buscarTrabajadores(
            @Param("nombre") String nombre,
            @Param("cargo") String cargo,
            @Param("estado") Boolean estado,
            Pageable pageable
    );
}
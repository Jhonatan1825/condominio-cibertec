package com.condominio.cibertec.business.data.repository;

import com.condominio.cibertec.business.data.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByCorreo(String correo);

    Optional<Usuario> findByDni(String dni);

    boolean existsByCorreo(String correo);

    boolean existsByDni(String dni);

    boolean existsByCorreoAndIdUsuarioNot(
            String correo,
            Integer idUsuario
    );

    boolean existsByDniAndIdUsuarioNot(
            String dni,
            Integer idUsuario
    );

    @Query("""
            SELECT u
            FROM Usuario u
            WHERE (
                :nombre IS NULL
                OR LOWER(u.nombre)
                    LIKE LOWER(CONCAT('%', :nombre, '%'))
            )
            AND (
                :estado IS NULL
                OR u.estado = :estado
            )
            """)
    Page<Usuario> consultarUsuarios(
            @Param("nombre") String nombre,
            @Param("estado") Boolean estado,
            Pageable pageable
    );

}
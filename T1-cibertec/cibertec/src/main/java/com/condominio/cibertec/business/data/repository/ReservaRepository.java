package com.condominio.cibertec.business.data.repository;


import com.condominio.cibertec.business.data.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByEstado(String estado);
    List<Reserva> findByUsuarioIdUsuario(Integer idUsuario);
}

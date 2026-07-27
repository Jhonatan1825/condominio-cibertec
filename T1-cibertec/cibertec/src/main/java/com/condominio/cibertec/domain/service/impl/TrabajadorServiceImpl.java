package com.condominio.cibertec.domain.service.impl;

import com.condominio.cibertec.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.api.dto.TrabajadorResponseDto;
import com.condominio.cibertec.api.exception.RecursoDuplicadoException;
import com.condominio.cibertec.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.domain.mapper.TrabajadorMapper;
import com.condominio.cibertec.domain.service.TrabajadorService;
import com.condominio.cibertec.data.entity.Trabajador;
import com.condominio.cibertec.data.repository.TrabajadorRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TrabajadorServiceImpl implements TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final TrabajadorMapper trabajadorMapper;
    private final EntityManager entityManager;

    public TrabajadorServiceImpl(
            TrabajadorRepository trabajadorRepository,
            TrabajadorMapper trabajadorMapper,
            EntityManager entityManager
    ) {
        this.trabajadorRepository = trabajadorRepository;
        this.trabajadorMapper = trabajadorMapper;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrabajadorResponseDto> obtenerTodos() {
        return trabajadorRepository.findAll()
                .stream()
                .map(trabajadorMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TrabajadorResponseDto obtenerPorId(Integer id) {
        return trabajadorMapper.toResponseDto(buscarTrabajadorPorId(id));
    }

    @Override
    public TrabajadorResponseDto crear(TrabajadorRequestDto requestDto) {
        if (trabajadorRepository.existsByDni(requestDto.dni())) {
            throw new RecursoDuplicadoException("El DNI ya está registrado");
        }

        Trabajador trabajador = trabajadorMapper.toEntity(requestDto);
        trabajador.setEstado(true);
        trabajador.setCreatedAt(LocalDateTime.now());
        trabajador.setUpdatedAt(LocalDateTime.now());

        Trabajador guardado = trabajadorRepository.save(trabajador);

        return trabajadorMapper.toResponseDto(guardado);
    }

    @Override
    public TrabajadorResponseDto actualizar(Integer id, TrabajadorRequestDto requestDto) {
        Trabajador trabajador = buscarTrabajadorPorId(id);

        if (trabajadorRepository.existsByDniAndIdTrabajadorNot(requestDto.dni(), id)) {
            throw new RecursoDuplicadoException("El DNI ya está registrado por otro trabajador");
        }

        trabajadorMapper.actualizarEntidad(requestDto, trabajador);
        trabajador.setUpdatedAt(LocalDateTime.now());

        return trabajadorMapper.toResponseDto(trabajadorRepository.save(trabajador));
    }

    @Override
    public void eliminar(Integer id) {
        Trabajador trabajador = buscarTrabajadorPorId(id);
        trabajador.setEstado(false);
        trabajador.setUpdatedAt(LocalDateTime.now());
        trabajadorRepository.save(trabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public TrabajadorResponseDto buscarPorDni(String dni) {
        Trabajador trabajador = trabajadorRepository.findByDni(dni)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró un trabajador con DNI: " + dni));
        return trabajadorMapper.toResponseDto(trabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrabajadorResponseDto> consultar(String nombre, String cargo, Boolean estado, Pageable pageable) {
        String nombreNormalizado = (nombre == null || nombre.isBlank()) ? null : nombre.trim();
        return trabajadorRepository.buscarTrabajadores(nombreNormalizado, cargo, estado, pageable)
                .map(trabajadorMapper::toResponseDto);
    }

    private Trabajador buscarTrabajadorPorId(Integer id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el trabajador con id: " + id));
    }
}
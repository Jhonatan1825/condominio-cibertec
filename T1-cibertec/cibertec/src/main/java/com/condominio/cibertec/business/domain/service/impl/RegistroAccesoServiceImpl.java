package com.condominio.cibertec.business.domain.service.impl;

import com.condominio.cibertec.business.api.dto.RegistroAccesoRequestDto;
import com.condominio.cibertec.business.api.dto.RegistroAccesoResponseDto;
import com.condominio.cibertec.business.api.exception.RecursoDuplicadoException;
import com.condominio.cibertec.business.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.business.data.entity.*;
import com.condominio.cibertec.business.data.repository.*;
import com.condominio.cibertec.business.domain.mapper.RegistroAccesoMapper;
import com.condominio.cibertec.business.domain.service.RegistroAccesoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RegistroAccesoServiceImpl implements RegistroAccesoService {

    private final RegistroAccesoRepository registroAccesoRepository;
    private final VisitanteRepository visitanteRepository;
    private final DepartamentoRepository departamentoRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final RegistroAccesoMapper registroAccesoMapper;

    public RegistroAccesoServiceImpl(
            RegistroAccesoRepository registroAccesoRepository,
            VisitanteRepository visitanteRepository,
            DepartamentoRepository departamentoRepository,
            TrabajadorRepository trabajadorRepository,
            RegistroAccesoMapper registroAccesoMapper
    ) {
        this.registroAccesoRepository = registroAccesoRepository;
        this.visitanteRepository = visitanteRepository;
        this.departamentoRepository = departamentoRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.registroAccesoMapper = registroAccesoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroAccesoResponseDto> obtenerTodos() {
        return registroAccesoRepository.findAll()
                .stream()
                .map(registroAccesoMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public RegistroAccesoResponseDto obtenerPorId(Integer id) {
        return registroAccesoMapper.toResponseDto(buscarPorId(id));
    }

    @Override
    public RegistroAccesoResponseDto registrarIngreso(RegistroAccesoRequestDto requestDto) {
        // Regla de negocio: un visitante no puede tener dos ingresos abiertos a la vez
        boolean tieneIngresoAbierto = registroAccesoRepository
                .findByVisitante_IdVisitanteAndFechaHoraSalidaIsNull(requestDto.idVisitante())
                .isPresent();

        if (tieneIngresoAbierto) {
            throw new RecursoDuplicadoException("Este visitante ya tiene un ingreso sin registrar salida");
        }

        Visitante visitante = visitanteRepository.findById(requestDto.idVisitante())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el visitante con id: " + requestDto.idVisitante()));

        Departamento departamento = departamentoRepository.findById(requestDto.idDepartamento())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el departamento con id: " + requestDto.idDepartamento()));

        Trabajador trabajador = trabajadorRepository.findById(requestDto.idTrabajador())
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el trabajador con id: " + requestDto.idTrabajador()));

        RegistroAcceso registro = new RegistroAcceso();
        registro.setVisitante(visitante);
        registro.setDepartamento(departamento);
        registro.setTrabajador(trabajador);
        registro.setFechaHoraIngreso(LocalDateTime.now());

        return registroAccesoMapper.toResponseDto(registroAccesoRepository.save(registro));
    }

    @Override
    public RegistroAccesoResponseDto registrarSalida(Integer idRegistro) {
        RegistroAcceso registro = buscarPorId(idRegistro);

        if (registro.getFechaHoraSalida() != null) {
            throw new RecursoDuplicadoException("Este registro ya tiene una salida registrada");
        }

        registro.setFechaHoraSalida(LocalDateTime.now());

        return registroAccesoMapper.toResponseDto(registroAccesoRepository.save(registro));
    }

    private RegistroAcceso buscarPorId(Integer id) {
        return registroAccesoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el registro con id: " + id));
    }
}
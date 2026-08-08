package com.condominio.cibertec.business.domain.service.impl;

import com.condominio.cibertec.business.api.dto.VisitanteRequestDto;
import com.condominio.cibertec.business.api.dto.VisitanteResponseDto;
import com.condominio.cibertec.business.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.business.data.entity.Visitante;
import com.condominio.cibertec.business.data.repository.VisitanteRepository;
import com.condominio.cibertec.business.domain.mapper.VisitanteMapper;
import com.condominio.cibertec.business.domain.service.VisitanteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VisitanteServiceImpl implements VisitanteService {

    private final VisitanteRepository visitanteRepository;
    private final VisitanteMapper visitanteMapper;

    public VisitanteServiceImpl(VisitanteRepository visitanteRepository, VisitanteMapper visitanteMapper) {
        this.visitanteRepository = visitanteRepository;
        this.visitanteMapper = visitanteMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitanteResponseDto> obtenerTodos() {
        return visitanteRepository.findAll()
                .stream()
                .map(visitanteMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VisitanteResponseDto obtenerPorId(Integer id) {
        return visitanteMapper.toResponseDto(buscarPorId(id));
    }

    @Override
    public VisitanteResponseDto crear(VisitanteRequestDto requestDto) {
        Visitante visitante = visitanteMapper.toEntity(requestDto);
        return visitanteMapper.toResponseDto(visitanteRepository.save(visitante));
    }

    @Override
    public VisitanteResponseDto actualizar(Integer id, VisitanteRequestDto requestDto) {
        Visitante visitante = buscarPorId(id);
        visitanteMapper.actualizarEntidad(requestDto, visitante);
        return visitanteMapper.toResponseDto(visitanteRepository.save(visitante));
    }

    @Override
    public void eliminar(Integer id) {
        visitanteRepository.delete(buscarPorId(id));
    }

    private Visitante buscarPorId(Integer id) {
        return visitanteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el visitante con id: " + id));
    }
}
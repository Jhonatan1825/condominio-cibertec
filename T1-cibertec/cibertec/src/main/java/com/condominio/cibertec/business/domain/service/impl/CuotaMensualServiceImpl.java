package com.condominio.cibertec.business.domain.service.impl;

import com.condominio.cibertec.business.api.dto.CuotaMensualRequestDto;
import com.condominio.cibertec.business.api.dto.CuotaMensualResponseDto;
import com.condominio.cibertec.business.api.exception.RecursoDuplicadoException;
import com.condominio.cibertec.business.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.business.api.exception.SolicitudInvalidaException;
import com.condominio.cibertec.business.data.entity.CuotaMensual;
import com.condominio.cibertec.business.data.entity.Departamento;
import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;
import com.condominio.cibertec.business.data.repository.CuotaMensualRepository;
import com.condominio.cibertec.business.data.repository.DepartamentoRepository;
import com.condominio.cibertec.business.domain.mapper.CuotaMensualMapper;
import com.condominio.cibertec.business.domain.service.CuotaMensualService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@Transactional
public class CuotaMensualServiceImpl
        implements CuotaMensualService {

    private final CuotaMensualRepository cuotaMensualRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CuotaMensualMapper cuotaMensualMapper;

    public CuotaMensualServiceImpl(
            CuotaMensualRepository cuotaMensualRepository,
            DepartamentoRepository departamentoRepository,
            CuotaMensualMapper cuotaMensualMapper
    ) {
        this.cuotaMensualRepository =
                cuotaMensualRepository;

        this.departamentoRepository =
                departamentoRepository;

        this.cuotaMensualMapper =
                cuotaMensualMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CuotaMensualResponseDto> obtenerTodas() {

        return cuotaMensualRepository.findAll()
                .stream()
                .map(cuotaMensualMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CuotaMensualResponseDto obtenerPorId(
            Integer id
    ) {

        CuotaMensual cuota =
                buscarCuotaCompleta(id);

        return cuotaMensualMapper.toResponseDto(cuota);
    }

    @Override
    public CuotaMensualResponseDto crear(
            CuotaMensualRequestDto requestDto
    ) {

        validarFechas(requestDto);

        Departamento departamento =
                buscarDepartamento(
                        requestDto.idDepartamento()
                );

        validarCuotaDuplicada(
                requestDto.idDepartamento(),
                requestDto.fechaEmision()
        );

        CuotaMensual cuota =
                cuotaMensualMapper.toEntity(requestDto);

        cuota.setDepartamento(departamento);

        BigDecimal mora =
                obtenerMora(requestDto.montoMora());

        cuota.setMontoMora(mora);

        cuota.setMontoTotal(
                requestDto.montoBase()
                        .add(mora)
        );

        cuota.setEstado(
                EstadoCuota.PENDIENTE
        );

        CuotaMensual cuotaGuardada =
                cuotaMensualRepository.save(cuota);

        return cuotaMensualMapper
                .toResponseDto(cuotaGuardada);
    }

    @Override
    public CuotaMensualResponseDto actualizar(
            Integer id,
            CuotaMensualRequestDto requestDto
    ) {

        CuotaMensual cuota =
                buscarCuotaCompleta(id);

        validarCuotaModificable(cuota);

        validarFechas(requestDto);

        Departamento departamento =
                buscarDepartamento(
                        requestDto.idDepartamento()
                );

        cuotaMensualMapper.actualizarEntidad(
                requestDto,
                cuota
        );

        cuota.setDepartamento(departamento);

        BigDecimal mora =
                obtenerMora(requestDto.montoMora());

        cuota.setMontoMora(mora);

        cuota.setMontoTotal(
                requestDto.montoBase()
                        .add(mora)
        );

        CuotaMensual cuotaActualizada =
                cuotaMensualRepository.save(cuota);

        return cuotaMensualMapper
                .toResponseDto(cuotaActualizada);
    }

    @Override
    public void eliminar(Integer id) {

        CuotaMensual cuota =
                buscarCuotaCompleta(id);

        validarCuotaModificable(cuota);

        cuotaMensualRepository.delete(cuota);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CuotaMensualResponseDto>
    obtenerPorDepartamento(
            Integer idDepartamento,
            Pageable pageable
    ) {

        return cuotaMensualRepository
                .findByDepartamentoIdDepartamento(
                        idDepartamento,
                        pageable
                )
                .map(cuotaMensualMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CuotaMensualResponseDto>
    obtenerPorEstado(
            EstadoCuota estado,
            Pageable pageable
    ) {

        return cuotaMensualRepository
                .findByEstado(
                        estado,
                        pageable
                )
                .map(cuotaMensualMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CuotaMensualResponseDto>
    obtenerEntreFechas(
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Pageable pageable
    ) {

        if (fechaInicio.isAfter(fechaFin)) {
            throw new SolicitudInvalidaException(
                    "La fecha inicial no puede ser posterior "
                            + "a la fecha final"
            );
        }

        return cuotaMensualRepository
                .findByFechaVencimientoBetween(
                        fechaInicio,
                        fechaFin,
                        pageable
                )
                .map(cuotaMensualMapper::toResponseDto);
    }

    private CuotaMensual buscarCuotaCompleta(
            Integer id
    ) {

        return cuotaMensualRepository
                .findOneByIdCuotaMensual(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No se encontró la cuota con id: "
                                        + id
                        )
                );
    }

    private Departamento buscarDepartamento(
            Integer id
    ) {

        return departamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No se encontró el departamento con id: "
                                        + id
                        )
                );
    }

    private void validarCuotaDuplicada(
            Integer idDepartamento,
            LocalDate fechaEmision
    ) {

        YearMonth periodo =
                YearMonth.from(fechaEmision);

        LocalDate fechaInicio =
                periodo.atDay(1);

        LocalDate fechaFin =
                periodo.atEndOfMonth();

        if (cuotaMensualRepository
                .existsByDepartamentoIdDepartamentoAndFechaEmisionBetween(
                        idDepartamento,
                        fechaInicio,
                        fechaFin
                )) {

            throw new RecursoDuplicadoException(
                    "Ya existe una cuota para este departamento "
                            + "en el mismo mes"
            );
        }
    }

    private void validarFechas(
            CuotaMensualRequestDto requestDto
    ) {

        if (requestDto.fechaEmision()
                .isAfter(
                        requestDto.fechaVencimiento()
                )) {

            throw new SolicitudInvalidaException(
                    "La fecha de emisión no puede ser posterior "
                            + "a la fecha de vencimiento"
            );
        }
    }

    private void validarCuotaModificable(
            CuotaMensual cuota
    ) {

        if (cuota.getEstado()
                == EstadoCuota.PAGADO) {

            throw new SolicitudInvalidaException(
                    "No se puede modificar una cuota pagada"
            );
        }
    }

    private BigDecimal obtenerMora(
            BigDecimal montoMora
    ) {

        return montoMora == null
                ? BigDecimal.ZERO
                : montoMora;
    }
}

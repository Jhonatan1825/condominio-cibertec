package com.condominio.cibertec.business.domain.service.impl;

import com.condominio.cibertec.business.api.dto.PagoMantenimientoRequestDto;
import com.condominio.cibertec.business.api.dto.PagoMantenimientoResponseDto;
import com.condominio.cibertec.business.api.exception.RecursoDuplicadoException;
import com.condominio.cibertec.business.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.business.api.exception.SolicitudInvalidaException;
import com.condominio.cibertec.business.data.entity.CuotaMensual;
import com.condominio.cibertec.business.data.entity.PagoMantenimiento;
import com.condominio.cibertec.business.data.entity.Usuario;
import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;
import com.condominio.cibertec.business.data.entity.enums.EstadoPago;
import com.condominio.cibertec.business.data.repository.CuotaMensualRepository;
import com.condominio.cibertec.business.data.repository.PagoMantenimientoRepository;
import com.condominio.cibertec.business.data.repository.UsuarioRepository;
import com.condominio.cibertec.business.domain.mapper.PagoMantenimientoMapper;
import com.condominio.cibertec.business.domain.service.PagoMantenimientoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PagoMantenimientoServiceImpl implements PagoMantenimientoService {

    private final PagoMantenimientoRepository pagoRepository;
    private final CuotaMensualRepository cuotaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PagoMantenimientoMapper pagoMapper;

    public PagoMantenimientoServiceImpl(
            PagoMantenimientoRepository pagoRepository,
            CuotaMensualRepository cuotaRepository,
            UsuarioRepository usuarioRepository,
            PagoMantenimientoMapper pagoMapper
    ) {
        this.pagoRepository = pagoRepository;
        this.cuotaRepository = cuotaRepository;
        this.usuarioRepository = usuarioRepository;
        this.pagoMapper = pagoMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoMantenimientoResponseDto> obtenerTodos() {

        return pagoRepository.findAll()
                .stream()
                .map(pagoMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PagoMantenimientoResponseDto obtenerPorId(
            Integer id
    ) {

        PagoMantenimiento pago =
                buscarPagoCompleto(id);

        return pagoMapper.toResponseDto(pago);
    }

    @Override
    public PagoMantenimientoResponseDto registrar(
            PagoMantenimientoRequestDto requestDto
    ) {

        CuotaMensual cuota =
                buscarCuota(
                        requestDto.idCuotaMensual()
                );

        Usuario usuario =
                buscarUsuario(
                        requestDto.idUsuario()
                );

        validarPagoDuplicado(cuota);

        validarCuotaDisponible(cuota);

        validarMonto(
                cuota,
                requestDto.monto()
        );

        PagoMantenimiento pago =
                new PagoMantenimiento();

        pago.setCuotaMensual(cuota);
        pago.setUsuario(usuario);
        pago.setMetodoPago(
                requestDto.metodoPago()
        );
        pago.setMonto(requestDto.monto());
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstado(EstadoPago.REGISTRADO);

        cuota.setEstado(EstadoCuota.PAGADO);

        PagoMantenimiento pagoGuardado =
                pagoRepository.save(pago);

        return pagoMapper.toResponseDto(
                pagoGuardado
        );
    }

    @Override
    public void anular(Integer id) {

        PagoMantenimiento pago =
                buscarPagoCompleto(id);

        if (pago.getEstado() == EstadoPago.ANULADO) {
            throw new SolicitudInvalidaException(
                    "El pago ya se encuentra anulado"
            );
        }

        pago.setEstado(EstadoPago.ANULADO);

        CuotaMensual cuota =
                pago.getCuotaMensual();

        cuota.setEstado(
                EstadoCuota.PENDIENTE
        );

        pagoRepository.save(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PagoMantenimientoResponseDto> obtenerPorUsuario(
            Integer idUsuario,
            Pageable pageable
    ) {

        return pagoRepository
                .findByUsuarioIdUsuario(
                        idUsuario,
                        pageable
                )
                .map(pagoMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PagoMantenimientoResponseDto> obtenerPorEstado(
            EstadoPago estado,
            Pageable pageable
    ) {

        return pagoRepository
                .findByEstado(
                        estado,
                        pageable
                )
                .map(pagoMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PagoMantenimientoResponseDto> obtenerEntreFechas(
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Pageable pageable
    ) {

        if (fechaInicio.isAfter(fechaFin)) {

            throw new SolicitudInvalidaException(
                    "La fecha inicial no puede ser posterior "
                            + "a la fecha final"
            );
        }

        return pagoRepository
                .findByFechaPagoBetween(
                        fechaInicio,
                        fechaFin,
                        pageable
                )
                .map(pagoMapper::toResponseDto);
    }

    private PagoMantenimiento buscarPagoCompleto(
            Integer id
    ) {

        return pagoRepository
                .findOneByIdPagoMantenimiento(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No se encontró el pago con id: "
                                        + id
                        )
                );
    }

    private CuotaMensual buscarCuota(
            Integer id
    ) {

        return cuotaRepository
                .findOneByIdCuotaMensual(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No se encontró la cuota con id: "
                                        + id
                        )
                );
    }

    private Usuario buscarUsuario(
            Integer id
    ) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "No se encontró el usuario con id: "
                                        + id
                        )
                );
    }

    private void validarPagoDuplicado(
            CuotaMensual cuota
    ) {

        if (pagoRepository
                .existsByCuotaMensualIdCuotaMensual(
                        cuota.getIdCuotaMensual()
                )) {

            throw new RecursoDuplicadoException(
                    "La cuota ya tiene un pago registrado"
            );
        }
    }

    private void validarCuotaDisponible(
            CuotaMensual cuota
    ) {

        if (cuota.getEstado() == EstadoCuota.PAGADO) {

            throw new SolicitudInvalidaException(
                    "La cuota ya se encuentra pagada"
            );
        }
    }

    private void validarMonto(
            CuotaMensual cuota,
            java.math.BigDecimal monto
    ) {

        if (monto.compareTo(
                cuota.getMontoTotal()
        ) != 0) {

            throw new SolicitudInvalidaException(
                    "El monto pagado debe ser igual "
                            + "al monto total de la cuota"
            );
        }
    }
}

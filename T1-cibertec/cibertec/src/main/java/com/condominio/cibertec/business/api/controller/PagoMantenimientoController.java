package com.condominio.cibertec.business.api.controller;

import com.condominio.cibertec.business.api.dto.PagoMantenimientoRequestDto;
import com.condominio.cibertec.business.api.dto.PagoMantenimientoResponseDto;
import com.condominio.cibertec.business.data.entity.enums.EstadoPago;
import com.condominio.cibertec.business.domain.service.PagoMantenimientoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos-mantenimiento")
public class PagoMantenimientoController {

    private final PagoMantenimientoService pagoMantenimientoService;

    public PagoMantenimientoController( PagoMantenimientoService pagoMantenimientoService
    ) {
        this.pagoMantenimientoService = pagoMantenimientoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoMantenimientoResponseDto>>
    obtenerTodos() {

        return ResponseEntity.ok(
                pagoMantenimientoService.obtenerTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoMantenimientoResponseDto>
    obtenerPorId(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                pagoMantenimientoService.obtenerPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<PagoMantenimientoResponseDto>
    registrar(
            @Valid
            @RequestBody
            PagoMantenimientoRequestDto requestDto
    ) {

        PagoMantenimientoResponseDto pagoRegistrado =
                pagoMantenimientoService.registrar(
                        requestDto
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pagoRegistrado);
    }

    @PatchMapping("/{id}/anular")
    public ResponseEntity<Void> anular(
            @PathVariable Integer id
    ) {

        pagoMantenimientoService.anular(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Page<PagoMantenimientoResponseDto>>
    obtenerPorUsuario(
            @PathVariable Integer idUsuario,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "fechaPago"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                pagoMantenimientoService.obtenerPorUsuario(
                        idUsuario,
                        pageable
                )
        );
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Page<PagoMantenimientoResponseDto>>
    obtenerPorEstado(
            @PathVariable EstadoPago estado,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "fechaPago"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                pagoMantenimientoService.obtenerPorEstado(
                        estado,
                        pageable
                )
        );
    }

    @GetMapping("/fechas")
    public ResponseEntity<Page<PagoMantenimientoResponseDto>>
    obtenerEntreFechas(

            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME
            )
            LocalDateTime fechaInicio,

            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME
            )
            LocalDateTime fechaFin,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "fechaPago"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                pagoMantenimientoService.obtenerEntreFechas(
                        fechaInicio,
                        fechaFin,
                        pageable
                )
        );
    }
}

package com.condominio.cibertec.business.api.controller;

import com.condominio.cibertec.business.api.dto.CuotaMensualRequestDto;
import com.condominio.cibertec.business.api.dto.CuotaMensualResponseDto;
import com.condominio.cibertec.business.data.entity.enums.EstadoCuota;
import com.condominio.cibertec.business.domain.service.CuotaMensualService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cuotas")
public class CuotaMensualController {

    private final CuotaMensualService cuotaMensualService;

    public CuotaMensualController(
            CuotaMensualService cuotaMensualService
    ) {
        this.cuotaMensualService =
                cuotaMensualService;
    }

    @GetMapping
    public ResponseEntity<List<CuotaMensualResponseDto>>
    obtenerTodas() {

        return ResponseEntity.ok(
                cuotaMensualService.obtenerTodas()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuotaMensualResponseDto>
    obtenerPorId(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                cuotaMensualService.obtenerPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<CuotaMensualResponseDto>
    crear(
            @Valid
            @RequestBody
            CuotaMensualRequestDto requestDto
    ) {

        CuotaMensualResponseDto cuotaCreada =
                cuotaMensualService.crear(
                        requestDto
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cuotaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuotaMensualResponseDto>
    actualizar(
            @PathVariable Integer id,
            @Valid
            @RequestBody
            CuotaMensualRequestDto requestDto
    ) {

        return ResponseEntity.ok(
                cuotaMensualService.actualizar(
                        id,
                        requestDto
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id
    ) {

        cuotaMensualService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/departamento/{idDepartamento}")
    public ResponseEntity<Page<CuotaMensualResponseDto>>
    obtenerPorDepartamento(
            @PathVariable Integer idDepartamento,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "fechaVencimiento"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                cuotaMensualService
                        .obtenerPorDepartamento(
                                idDepartamento,
                                pageable
                        )
        );
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Page<CuotaMensualResponseDto>>
    obtenerPorEstado(
            @PathVariable EstadoCuota estado,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "fechaVencimiento"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                cuotaMensualService.obtenerPorEstado(
                        estado,
                        pageable
                )
        );
    }

    @GetMapping("/fechas")
    public ResponseEntity<Page<CuotaMensualResponseDto>>
    obtenerEntreFechas(

            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE
            )
            LocalDate fechaInicio,

            @RequestParam
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE
            )
            LocalDate fechaFin,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "fechaVencimiento"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                cuotaMensualService.obtenerEntreFechas(
                        fechaInicio,
                        fechaFin,
                        pageable
                )
        );
    }
}

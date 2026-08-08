package com.condominio.cibertec.business.api.controller;

import com.condominio.cibertec.business.api.dto.RegistroAccesoRequestDto;
import com.condominio.cibertec.business.api.dto.RegistroAccesoResponseDto;
import com.condominio.cibertec.business.domain.service.RegistroAccesoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros-acceso")
public class RegistroAccesoController {

    private final RegistroAccesoService registroAccesoService;

    public RegistroAccesoController(RegistroAccesoService registroAccesoService) {
        this.registroAccesoService = registroAccesoService;
    }

    @GetMapping
    public ResponseEntity<List<RegistroAccesoResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(registroAccesoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroAccesoResponseDto> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(registroAccesoService.obtenerPorId(id));
    }

    @PostMapping("/ingreso")
    public ResponseEntity<RegistroAccesoResponseDto> registrarIngreso(@Valid @RequestBody RegistroAccesoRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registroAccesoService.registrarIngreso(requestDto));
    }

    @PutMapping("/{id}/salida")
    public ResponseEntity<RegistroAccesoResponseDto> registrarSalida(@PathVariable Integer id) {
        return ResponseEntity.ok(registroAccesoService.registrarSalida(id));
    }
}
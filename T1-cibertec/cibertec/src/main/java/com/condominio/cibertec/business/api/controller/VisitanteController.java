package com.condominio.cibertec.business.api.controller;

import com.condominio.cibertec.business.api.dto.VisitanteRequestDto;
import com.condominio.cibertec.business.api.dto.VisitanteResponseDto;
import com.condominio.cibertec.business.domain.service.VisitanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitantes")
public class VisitanteController {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @GetMapping
    public ResponseEntity<List<VisitanteResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(visitanteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitanteResponseDto> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(visitanteService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<VisitanteResponseDto> crear(@Valid @RequestBody VisitanteRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitanteService.crear(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitanteResponseDto> actualizar(@PathVariable Integer id, @Valid @RequestBody VisitanteRequestDto requestDto) {
        return ResponseEntity.ok(visitanteService.actualizar(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        visitanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
package com.condominio.cibertec.api.controller;

import com.condominio.cibertec.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.api.dto.TrabajadorResponseDto;
import com.condominio.cibertec.domain.service.TrabajadorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    public TrabajadorController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping
    public ResponseEntity<List<TrabajadorResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(trabajadorService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorResponseDto> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(trabajadorService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<TrabajadorResponseDto> crear(@Valid @RequestBody TrabajadorRequestDto requestDto) {
        TrabajadorResponseDto creado = trabajadorService.crear(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabajadorResponseDto> actualizar(@PathVariable Integer id, @Valid @RequestBody TrabajadorRequestDto requestDto) {
        return ResponseEntity.ok(trabajadorService.actualizar(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        trabajadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<TrabajadorResponseDto> buscarPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(trabajadorService.buscarPorDni(dni));
    }

    @GetMapping("/consulta")
    public ResponseEntity<Page<TrabajadorResponseDto>> consultar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) Boolean estado,
            @PageableDefault(page = 0, size = 5, sort = "nombre") Pageable pageable
    ) {
        return ResponseEntity.ok(trabajadorService.consultar(nombre, cargo, estado, pageable));
    }
}
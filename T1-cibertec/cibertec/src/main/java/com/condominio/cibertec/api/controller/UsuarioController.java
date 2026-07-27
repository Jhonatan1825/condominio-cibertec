package com.condominio.cibertec.api.controller;

import com.condominio.cibertec.api.dto.UsuarioRequestDTO;
import com.condominio.cibertec.api.dto.UsuarioResponseDTO;
import com.condominio.cibertec.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(
            UsuarioService usuarioService
    ) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {

        return ResponseEntity.ok(
                usuarioService.obtenerTodos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                usuarioService.obtenerPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(
            @Valid @RequestBody UsuarioRequestDTO request
    ) {

        UsuarioResponseDTO usuarioCreado =
                usuarioService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRequestDTO request
    ) {

        return ResponseEntity.ok(
                usuarioService.actualizar(id, request)
        );
    }

    @GetMapping("/consulta")
    public ResponseEntity<Page<UsuarioResponseDTO>> consultar(

            @RequestParam(required = false)
            String nombre,

            @RequestParam(required = false)
            Boolean estado,

            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "nombre"
            )
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                usuarioService.consultar(
                        nombre,
                        estado,
                        pageable
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id
    ) {
        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
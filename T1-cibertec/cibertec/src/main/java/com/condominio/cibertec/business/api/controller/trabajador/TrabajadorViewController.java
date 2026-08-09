package com.condominio.cibertec.business.api.controller.trabajador;

import com.condominio.cibertec.business.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.business.domain.service.TrabajadorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vista/trabajadores")
public class TrabajadorViewController {

    private final TrabajadorService trabajadorService;

    public TrabajadorViewController(TrabajadorService trabajadorService) {
        this.trabajadorService = trabajadorService;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) Boolean estado,
            Model model
    ) {

        // Si no hay filtros, mostramos todos
        if ((nombre == null || nombre.isBlank())
                && (cargo == null || cargo.isBlank())
                && estado == null) {

            model.addAttribute(
                    "trabajadores",
                    trabajadorService.obtenerTodos()
            );

        } else {

            var pageable = PageRequest.of(
                    0,
                    100,
                    Sort.by("nombre").ascending()
            );

            var resultado = trabajadorService.consultar(
                    nombre,
                    cargo,
                    estado,
                    pageable
            );

            model.addAttribute(
                    "trabajadores",
                    resultado.getContent()
            );
        }

        // Mantener los valores en el formulario
        model.addAttribute("nombre", nombre);
        model.addAttribute("cargo", cargo);
        model.addAttribute("estado", estado);

        return "trabajadores/list";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear() {
        return "trabajadores/form";
    }

    @PostMapping("/nuevo")
    public String crear(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String dni,
            @RequestParam String telefono,
            @RequestParam(required = false) String correo,
            @RequestParam String cargo,
            @RequestParam String turno
    ) {

        TrabajadorRequestDto dto =
                new TrabajadorRequestDto(
                        nombre,
                        apellido,
                        dni,
                        telefono,
                        correo,
                        cargo,
                        turno
                );

        trabajadorService.crear(dto);

        return "redirect:/vista/trabajadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(
            @PathVariable Integer id,
            Model model
    ) {

        model.addAttribute(
                "trabajador",
                trabajadorService.obtenerPorId(id)
        );

        return "trabajadores/edit";
    }

    @PostMapping("/editar/{id}")
    public String actualizar(
            @PathVariable Integer id,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String dni,
            @RequestParam String telefono,
            @RequestParam(required = false) String correo,
            @RequestParam String cargo,
            @RequestParam String turno
    ) {

        TrabajadorRequestDto dto =
                new TrabajadorRequestDto(
                        nombre,
                        apellido,
                        dni,
                        telefono,
                        correo,
                        cargo,
                        turno
                );

        trabajadorService.actualizar(id, dto);

        return "redirect:/vista/trabajadores";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {

        trabajadorService.eliminar(id);

        return "redirect:/vista/trabajadores";
    }
}
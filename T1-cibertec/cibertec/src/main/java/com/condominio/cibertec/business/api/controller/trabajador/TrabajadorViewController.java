package com.condominio.cibertec.business.web.controller;

import com.condominio.cibertec.business.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.business.domain.service.TrabajadorService;
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
    public String listar(Model model) {
        model.addAttribute("trabajadores", trabajadorService.obtenerTodos());
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
        TrabajadorRequestDto dto = new TrabajadorRequestDto(nombre, apellido, dni, telefono, correo, cargo, turno);
        trabajadorService.crear(dto);
        return "redirect:/vista/trabajadores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("trabajador", trabajadorService.obtenerPorId(id));
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
        TrabajadorRequestDto dto = new TrabajadorRequestDto(nombre, apellido, dni, telefono, correo, cargo, turno);
        trabajadorService.actualizar(id, dto);
        return "redirect:/vista/trabajadores";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        trabajadorService.eliminar(id);
        return "redirect:/vista/trabajadores";
    }
}
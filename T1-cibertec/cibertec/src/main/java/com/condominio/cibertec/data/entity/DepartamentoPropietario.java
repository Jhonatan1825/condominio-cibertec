package com.condominio.cibertec.data.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "departamento_propietario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoPropietario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento_propietario")
    private Integer idDepartamentoPropietario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    private Usuario propietario; // Usuario con rol PROPIETARIO

    @Column(name = "fecha_adquisicion")
    private LocalDate fechaAdquisicion;

    private Boolean estado;
}

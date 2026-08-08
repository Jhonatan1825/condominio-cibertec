package com.condominio.cibertec.business.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "visitante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visitante")
    private Integer idVisitante;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dni;

    private String motivo;
}
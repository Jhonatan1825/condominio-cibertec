package com.condominio.cibertec.business.domain.mapper;

import com.condominio.cibertec.business.api.dto.PagoMantenimientoResponseDto;
import com.condominio.cibertec.business.data.entity.PagoMantenimiento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PagoMantenimientoMapper {

    @Mapping(
            source = "cuotaMensual.idCuotaMensual",
            target = "idCuotaMensual"
    )
    @Mapping(
            source = "cuotaMensual.departamento.idDepartamento",
            target = "idDepartamento"
    )
    @Mapping(
            source = "cuotaMensual.departamento.numero",
            target = "departamento"
    )
    @Mapping(
            source = "usuario.idUsuario",
            target = "idUsuario"
    )
    @Mapping(
            source = "usuario",
            target = "usuario",
            qualifiedByName = "nombreCompleto"
    )
    PagoMantenimientoResponseDto toResponseDto(
            PagoMantenimiento pago
    );

    @Named("nombreCompleto")
    default String nombreCompleto(
            com.condominio.cibertec.business.data.entity.Usuario usuario
    ) {
        if (usuario == null) {
            return null;
        }

        return usuario.getNombre()
                + " "
                + usuario.getApellido();
    }
}

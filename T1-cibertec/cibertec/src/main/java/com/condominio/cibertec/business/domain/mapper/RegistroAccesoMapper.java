package com.condominio.cibertec.business.domain.mapper;

import com.condominio.cibertec.business.api.dto.RegistroAccesoResponseDto;
import com.condominio.cibertec.business.data.entity.RegistroAcceso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroAccesoMapper {

    @Mapping(target = "idVisitante", source = "visitante.idVisitante")
    @Mapping(target = "nombreVisitante", expression = "java(registro.getVisitante().getNombre() + \" \" + registro.getVisitante().getApellido())")
    @Mapping(target = "idDepartamento", source = "departamento.idDepartamento")
    @Mapping(target = "numeroDepartamento", source = "departamento.numero")
    @Mapping(target = "idTrabajador", source = "trabajador.idTrabajador")
    @Mapping(target = "nombreTrabajador", expression = "java(registro.getTrabajador().getNombre() + \" \" + registro.getTrabajador().getApellido())")
    RegistroAccesoResponseDto toResponseDto(RegistroAcceso registro);
}
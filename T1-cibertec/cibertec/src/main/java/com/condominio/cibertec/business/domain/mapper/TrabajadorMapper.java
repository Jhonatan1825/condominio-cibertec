package com.condominio.cibertec.business.domain.mapper;

import com.condominio.cibertec.business.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.business.api.dto.TrabajadorResponseDto;
import com.condominio.cibertec.business.data.entity.Trabajador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TrabajadorMapper {

    @Mapping(target = "idTrabajador", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Trabajador toEntity(TrabajadorRequestDto requestDto);

    TrabajadorResponseDto toResponseDto(Trabajador trabajador);

    @Mapping(target = "idTrabajador", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void actualizarEntidad(TrabajadorRequestDto requestDto, @MappingTarget Trabajador trabajador);
}
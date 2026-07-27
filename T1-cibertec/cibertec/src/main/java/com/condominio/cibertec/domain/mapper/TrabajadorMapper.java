package com.condominio.cibertec.domain.mapper;

import com.condominio.cibertec.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.api.dto.TrabajadorResponseDto;
import com.condominio.cibertec.data.entity.Trabajador;
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
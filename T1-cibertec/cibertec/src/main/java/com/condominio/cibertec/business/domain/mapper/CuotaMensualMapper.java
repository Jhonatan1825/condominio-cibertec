package com.condominio.cibertec.business.domain.mapper;

import com.condominio.cibertec.business.api.dto.CuotaMensualRequestDto;
import com.condominio.cibertec.business.api.dto.CuotaMensualResponseDto;
import com.condominio.cibertec.business.data.entity.CuotaMensual;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CuotaMensualMapper {

    @Mapping(
            source = "departamento.idDepartamento",
            target = "idDepartamento"
    )
    @Mapping(
            source = "departamento.numero",
            target = "departamento"
    )
    CuotaMensualResponseDto toResponseDto(
            CuotaMensual cuotaMensual
    );

    @Mapping(target = "idCuotaMensual", ignore = true)
    @Mapping(target = "departamento", ignore = true)
    @Mapping(target = "montoTotal", ignore = true)
    @Mapping(target = "estado", ignore = true)
    CuotaMensual toEntity(
            CuotaMensualRequestDto requestDto
    );

    @Mapping(target = "idCuotaMensual", ignore = true)
    @Mapping(target = "departamento", ignore = true)
    @Mapping(target = "montoTotal", ignore = true)
    @Mapping(target = "estado", ignore = true)
    void actualizarEntidad(
            CuotaMensualRequestDto requestDto,
            @MappingTarget CuotaMensual cuotaMensual
    );
}

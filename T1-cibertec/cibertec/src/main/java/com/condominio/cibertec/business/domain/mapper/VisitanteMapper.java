package com.condominio.cibertec.business.domain.mapper;

import com.condominio.cibertec.business.api.dto.VisitanteRequestDto;
import com.condominio.cibertec.business.api.dto.VisitanteResponseDto;
import com.condominio.cibertec.business.data.entity.Visitante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VisitanteMapper {

    @Mapping(target = "idVisitante", ignore = true)
    Visitante toEntity(VisitanteRequestDto requestDto);

    VisitanteResponseDto toResponseDto(Visitante visitante);

    @Mapping(target = "idVisitante", ignore = true)
    void actualizarEntidad(VisitanteRequestDto requestDto, @MappingTarget Visitante visitante);
}
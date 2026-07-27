package com.condominio.cibertec.domain.mapper;

import com.condominio.cibertec.api.dto.UsuarioRequestDTO;
import com.condominio.cibertec.api.dto.UsuarioResponseDTO;
import com.condominio.cibertec.data.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "rol", source = "rol.nombreRol")
    UsuarioResponseDTO toResponseDto(Usuario usuario);

    @Mapping(target="idUsuario", ignore=true)
    @Mapping(target="rol", ignore=true)
    @Mapping(target="createdAt", ignore=true)
    @Mapping(target="updatedAt", ignore=true)
    Usuario toEntity(UsuarioRequestDTO dto);

    @Mapping(target="idUsuario", ignore=true)
    @Mapping(target="rol", ignore=true)
    @Mapping(target="createdAt", ignore=true)
    @Mapping(target="updatedAt", ignore=true)
    void actualizarEntidad(
            UsuarioRequestDTO dto,
            @MappingTarget Usuario usuario
    );


}
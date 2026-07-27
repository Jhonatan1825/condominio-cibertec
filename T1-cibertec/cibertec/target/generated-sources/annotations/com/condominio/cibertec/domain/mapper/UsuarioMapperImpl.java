package com.condominio.cibertec.domain.mapper;

import com.condominio.cibertec.api.dto.UsuarioRequestDTO;
import com.condominio.cibertec.api.dto.UsuarioResponseDTO;
import com.condominio.cibertec.data.entity.Rol;
import com.condominio.cibertec.data.entity.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-26T14:49:44-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioResponseDTO toResponseDto(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        String rol = null;
        Integer idUsuario = null;
        String nombre = null;
        String apellido = null;
        String dni = null;
        String telefono = null;
        String correo = null;
        Boolean estado = null;

        rol = usuarioRolNombreRol( usuario );
        idUsuario = usuario.getIdUsuario();
        nombre = usuario.getNombre();
        apellido = usuario.getApellido();
        dni = usuario.getDni();
        telefono = usuario.getTelefono();
        correo = usuario.getCorreo();
        estado = usuario.getEstado();

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO( idUsuario, nombre, apellido, dni, telefono, correo, rol, estado );

        return usuarioResponseDTO;
    }

    @Override
    public Usuario toEntity(UsuarioRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNombre( dto.nombre() );
        usuario.setApellido( dto.apellido() );
        usuario.setDni( dto.dni() );
        usuario.setTelefono( dto.telefono() );
        usuario.setCorreo( dto.correo() );
        usuario.setPasswordHash( dto.passwordHash() );
        usuario.setEstado( dto.estado() );

        return usuario;
    }

    @Override
    public void actualizarEntidad(UsuarioRequestDTO dto, Usuario usuario) {
        if ( dto == null ) {
            return;
        }

        usuario.setNombre( dto.nombre() );
        usuario.setApellido( dto.apellido() );
        usuario.setDni( dto.dni() );
        usuario.setTelefono( dto.telefono() );
        usuario.setCorreo( dto.correo() );
        usuario.setPasswordHash( dto.passwordHash() );
        usuario.setEstado( dto.estado() );
    }

    private String usuarioRolNombreRol(Usuario usuario) {
        Rol rol = usuario.getRol();
        if ( rol == null ) {
            return null;
        }
        return rol.getNombreRol();
    }
}

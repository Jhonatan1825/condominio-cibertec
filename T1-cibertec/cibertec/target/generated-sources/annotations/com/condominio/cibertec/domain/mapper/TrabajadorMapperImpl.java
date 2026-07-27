package com.condominio.cibertec.domain.mapper;

import com.condominio.cibertec.api.dto.TrabajadorRequestDto;
import com.condominio.cibertec.api.dto.TrabajadorResponseDto;
import com.condominio.cibertec.data.entity.Trabajador;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-26T14:49:44-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (Eclipse Adoptium)"
)
@Component
public class TrabajadorMapperImpl implements TrabajadorMapper {

    @Override
    public Trabajador toEntity(TrabajadorRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Trabajador trabajador = new Trabajador();

        trabajador.setNombre( requestDto.nombre() );
        trabajador.setApellido( requestDto.apellido() );
        trabajador.setDni( requestDto.dni() );
        trabajador.setTelefono( requestDto.telefono() );
        trabajador.setCorreo( requestDto.correo() );
        trabajador.setCargo( requestDto.cargo() );
        trabajador.setTurno( requestDto.turno() );

        return trabajador;
    }

    @Override
    public TrabajadorResponseDto toResponseDto(Trabajador trabajador) {
        if ( trabajador == null ) {
            return null;
        }

        Integer idTrabajador = null;
        String nombre = null;
        String apellido = null;
        String dni = null;
        String telefono = null;
        String correo = null;
        String cargo = null;
        String turno = null;
        Boolean estado = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        idTrabajador = trabajador.getIdTrabajador();
        nombre = trabajador.getNombre();
        apellido = trabajador.getApellido();
        dni = trabajador.getDni();
        telefono = trabajador.getTelefono();
        correo = trabajador.getCorreo();
        cargo = trabajador.getCargo();
        turno = trabajador.getTurno();
        estado = trabajador.getEstado();
        createdAt = trabajador.getCreatedAt();
        updatedAt = trabajador.getUpdatedAt();

        TrabajadorResponseDto trabajadorResponseDto = new TrabajadorResponseDto( idTrabajador, nombre, apellido, dni, telefono, correo, cargo, turno, estado, createdAt, updatedAt );

        return trabajadorResponseDto;
    }

    @Override
    public void actualizarEntidad(TrabajadorRequestDto requestDto, Trabajador trabajador) {
        if ( requestDto == null ) {
            return;
        }

        trabajador.setNombre( requestDto.nombre() );
        trabajador.setApellido( requestDto.apellido() );
        trabajador.setDni( requestDto.dni() );
        trabajador.setTelefono( requestDto.telefono() );
        trabajador.setCorreo( requestDto.correo() );
        trabajador.setCargo( requestDto.cargo() );
        trabajador.setTurno( requestDto.turno() );
    }
}

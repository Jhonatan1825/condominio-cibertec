package com.condominio.cibertec.business.domain.service.impl;

import com.condominio.cibertec.business.api.dto.UsuarioRequestDTO;
import com.condominio.cibertec.business.api.dto.UsuarioResponseDTO;
import com.condominio.cibertec.business.api.exception.RecursoDuplicadoException;
import com.condominio.cibertec.business.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.business.data.entity.Rol;
import com.condominio.cibertec.business.data.entity.Usuario;
import com.condominio.cibertec.business.domain.mapper.UsuarioMapper;
import com.condominio.cibertec.business.data.repository.RolRepository;
import com.condominio.cibertec.business.data.repository.UsuarioRepository;
import com.condominio.cibertec.business.domain.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            UsuarioMapper usuarioMapper) {

        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    @Transactional
    public List<UsuarioResponseDTO> obtenerTodos() {

        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO obtenerPorId(Integer id) {

        Usuario usuario = buscarUsuarioPorId(id);

        return usuarioMapper.toResponseDto(usuario);
    }

    @Override
    public UsuarioResponseDTO crear(UsuarioRequestDTO request) {

        validarDuplicadosAlCrear(request);

        Usuario usuario = usuarioMapper.toEntity(request);

        Rol rol = buscarRol(request.idRol());

        usuario.setRol(rol);
                                    // implementamos Flush
                                    // --------------------
        Usuario usuarioGuardado = usuarioRepository.saveAndFlush(usuario);

        return usuarioMapper.toResponseDto(usuarioGuardado);
    }

    @Override
    public UsuarioResponseDTO actualizar(
            Integer id,
            UsuarioRequestDTO request) {

        Usuario usuario = buscarUsuarioPorId(id);

        validarDuplicadosAlActualizar(id, request);

        usuarioMapper.actualizarEntidad(request, usuario);

        Rol rol = buscarRol(request.idRol());

        usuario.setRol(rol);
                                    // implementamos Flush
                                    // --------------------
        Usuario usuarioActualizado = usuarioRepository.saveAndFlush(usuario);

        return usuarioMapper.toResponseDto(usuarioActualizado);
    }

    @Override
    public void eliminar(Integer id) {

        Usuario usuario = buscarUsuarioPorId(id);

        usuarioRepository.delete(usuario);
    }

    @Override
    @Transactional
    public Page<UsuarioResponseDTO> consultar(
            String nombre,
            Boolean estado,
            Pageable pageable
    ) {

        String nombreNormalizado =
                nombre == null || nombre.isBlank()
                        ? null
                        : nombre.trim();

        return usuarioRepository
                .consultarUsuarios(
                        nombreNormalizado,
                        estado,
                        pageable
                )
                .map(usuarioMapper::toResponseDto);
    }

    // Métodos privados

    private Usuario buscarUsuarioPorId(Integer id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Usuario no encontrado con ID: " + id
                        ));
    }

    private Rol buscarRol(Integer idRol) {

        return rolRepository.findById(idRol)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException(
                                "Rol no encontrado con ID: " + idRol
                        ));
    }

    private void validarDuplicadosAlCrear(
            UsuarioRequestDTO request) {

        if (usuarioRepository.existsByDni(request.dni())) {

            throw new RecursoDuplicadoException(
                    "El DNI ya se encuentra registrado."
            );
        }

        if (usuarioRepository.existsByCorreo(request.correo())) {

            throw new RecursoDuplicadoException(
                    "El correo ya se encuentra registrado."
            );
        }
    }

    private void validarDuplicadosAlActualizar(
            Integer id,
            UsuarioRequestDTO request) {

        if (usuarioRepository.existsByDniAndIdUsuarioNot(
                request.dni(),
                id)) {

            throw new RecursoDuplicadoException(
                    "El DNI ya se encuentra registrado."
            );
        }

        if (usuarioRepository.existsByCorreoAndIdUsuarioNot(
                request.correo(),
                id)) {

            throw new RecursoDuplicadoException(
                    "El correo ya se encuentra registrado."
            );
        }
    }
}

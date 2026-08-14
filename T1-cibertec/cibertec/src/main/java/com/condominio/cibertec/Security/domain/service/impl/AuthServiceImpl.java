package com.condominio.cibertec.Security.domain.service.impl;

import com.condominio.cibertec.business.api.exception.RecursoDuplicadoException;
import com.condominio.cibertec.business.api.exception.RecursoNoEncontradoException;
import com.condominio.cibertec.Security.api.dto.LoginRequestDto;
import com.condominio.cibertec.Security.api.dto.LoginResponseDto;
import com.condominio.cibertec.Security.api.dto.RegistroUsuarioRequestDto;
import com.condominio.cibertec.Security.api.dto.UsuarioResponseDto;
import com.condominio.cibertec.Security.domain.service.AuthService;
import com.condominio.cibertec.Security.domain.service.JwtService;
import com.condominio.cibertec.business.data.entity.Rol;
import com.condominio.cibertec.business.data.entity.Usuario;
import com.condominio.cibertec.business.data.repository.RolRepository;
import com.condominio.cibertec.business.data.repository.UsuarioRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UsuarioResponseDto registrar(RegistroUsuarioRequestDto requestDto) {

        String correo = normalizarCorreo(requestDto.correo());

        if (usuarioRepository.existsByCorreoIgnoreCase(correo)) {
            throw new RecursoDuplicadoException(
                    "El correo ya está registrado"
            );
        }

        Rol rol = rolRepository
                .findByNombreRol(requestDto.rol().trim().toUpperCase())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("El rol no existe")
                );

        Usuario usuario = new Usuario();

        usuario.setCorreo(correo);

        usuario.setPasswordHash(
                passwordEncoder.encode(requestDto.password())
        );

        usuario.setNombre(requestDto.nombre().trim());
        usuario.setApellido(requestDto.apellido().trim());
        usuario.setDni(requestDto.dni().trim());
        usuario.setTelefono(requestDto.telefono());

        usuario.setRol(rol);

        usuario.setEstado(true);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());

        Usuario usuarioGuardado =
                usuarioRepository.save(usuario);

        return convertirAResponse(usuarioGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto requestDto) {

        String correo =
                normalizarCorreo(requestDto.correo());

        UsernamePasswordAuthenticationToken solicitud =
                new UsernamePasswordAuthenticationToken(
                        correo,
                        requestDto.password()
                );

        UserDetails userDetails =
                (UserDetails) authenticationManager
                        .authenticate(solicitud)
                        .getPrincipal();

        String token =
                jwtService.generarToken(userDetails);

        Set<String> roles =
                userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet());

        return new LoginResponseDto(
                token,
                "Bearer",
                jwtService.obtenerTiempoExpiracion(),
                userDetails.getUsername(),
                roles
        );
    }

    private String normalizarCorreo(String correo) {
        return correo.trim().toLowerCase();
    }

    private UsuarioResponseDto convertirAResponse(
            Usuario usuario
    ) {

        return new UsuarioResponseDto(
                usuario.getIdUsuario(),
                usuario.getCorreo(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDni(),
                usuario.getTelefono(),
                usuario.getEstado(),
                usuario.getCreatedAt(),
                usuario.getRol().getNombreRol()
        );
    }
}
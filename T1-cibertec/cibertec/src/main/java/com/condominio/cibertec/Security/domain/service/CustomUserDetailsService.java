package com.condominio.cibertec.Security.domain.service;

import com.condominio.cibertec.business.data.entity.Usuario;
import com.condominio.cibertec.business.data.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        String correoNormalizado = correo.trim().toLowerCase();

        Usuario usuario = usuarioRepository
                .findByCorreoIgnoreCase(correoNormalizado)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuario o contraseña incorrectos"
                        )
                );

        String authority =
                "ROLE_" + usuario.getRol().getNombreRol();

        boolean activo =
                Boolean.TRUE.equals(usuario.getEstado());

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPasswordHash())
                .authorities(authority)
                .disabled(!activo)
                .build();
    }
}
package com.condominio.cibertec.Security.config;

import com.condominio.cibertec.Security.domain.service.CustomUserDetailsService;
import com.condominio.cibertec.Security.domain.service.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder,
            JwtAuthenticationEntryPoint authenticationEntryPoint,
            JwtAccessDeniedHandler accessDeniedHandler
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(exceptions ->
                        exceptions
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )

                .authorizeHttpRequests(auth ->
                        auth
                                // Auth público (login / registro)
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()

                                // Usuarios: solo administrador gestiona (crear, editar, eliminar, listar)
                                .requestMatchers("/api/v1/usuarios/**")
                                .hasRole("ADMINISTRADOR")

                                // Trabajadores: lectura para cualquier usuario logueado (ver quién está de turno)
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/trabajadores/**"
                                )
                                .authenticated()

                                // Trabajadores: crear/editar/eliminar solo administrador
                                .requestMatchers("/api/trabajadores/**")
                                .hasRole("ADMINISTRADOR")

                                // Cuotas: lectura para propietarios/inquilinos, escritura solo admin
                                .requestMatchers(
                                        HttpMethod.GET,
                                        "/api/v1/cuotas/**"
                                )
                                .hasAnyRole("ADMINISTRADOR", "PROPIETARIO", "INQUILINO")

                                .requestMatchers("/api/v1/cuotas/**")
                                .hasRole("ADMINISTRADOR")

                                // Pagos de mantenimiento: cualquier usuario logueado
                                .requestMatchers("/api/v1/pagos-mantenimiento/**")
                                .hasAnyRole("ADMINISTRADOR", "PROPIETARIO", "INQUILINO")

                                .requestMatchers("/error")
                                .permitAll()

                                .anyRequest()
                                .authenticated()
                )

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}
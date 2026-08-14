package com.condominio.cibertec.Security.config;

import com.condominio.cibertec.Security.domain.service.CustomUserDetailsService;
import com.condominio.cibertec.Security.domain.service.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
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

                .authorizeHttpRequests(auth -> auth

                        // LOGIN Y REGISTRO
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()

                        // RECURSOS ESTÁTICOS
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico"
                        )
                        .permitAll()

                        // ADMINISTRADOR
                        .requestMatchers("/api/usuarios/**")
                        .hasRole("ADMINISTRADOR")

                        .requestMatchers("/api/trabajadores/**")
                        .hasRole("ADMINISTRADOR")

                        .requestMatchers("/api/departamentos/**")
                        .hasRole("ADMINISTRADOR")

                        // CUOTAS
                        .requestMatchers("/api/cuotas/**")
                        .hasAnyRole(
                                "ADMINISTRADOR",
                                "PROPIETARIO"
                        )

                        // PAGOS
                        .requestMatchers("/api/pagos/**")
                        .hasAnyRole(
                                "ADMINISTRADOR",
                                "PROPIETARIO"
                        )

                        // RESERVAS
                        .requestMatchers("/api/reservas/**")
                        .hasAnyRole(
                                "ADMINISTRADOR",
                                "PROPIETARIO",
                                "INQUILINO"
                        )

                        // VISITANTES
                        .requestMatchers("/api/visitantes/**")
                        .hasAnyRole(
                                "ADMINISTRADOR",
                                "VIGILANTE"
                        )

                        // REGISTRO DE ACCESO
                        .requestMatchers("/api/registros-acceso/**")
                        .hasAnyRole(
                                "ADMINISTRADOR",
                                "VIGILANTE"
                        )

                        .anyRequest()
                        .authenticated()
                )

                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                )

                .authenticationProvider(authenticationProvider())

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }
}
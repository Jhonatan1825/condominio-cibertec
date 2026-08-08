package com.condominio.cibertec.Security.domain.service;

import com.condominio.cibertec.Security.api.dto.LoginRequestDto;
import com.condominio.cibertec.Security.api.dto.LoginResponseDto;
import com.condominio.cibertec.Security.api.dto.RegistroUsuarioRequestDto;
import com.condominio.cibertec.Security.api.dto.UsuarioResponseDto;

public interface AuthService {

    UsuarioResponseDto registrar(RegistroUsuarioRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);
}
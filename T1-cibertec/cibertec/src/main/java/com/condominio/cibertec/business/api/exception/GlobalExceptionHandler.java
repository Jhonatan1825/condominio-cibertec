package com.condominio.cibertec.business.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> manejarNoEncontrado(RecursoNoEncontradoException ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.NOT_FOUND, ex.getMessage(), request, null);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ApiErrorResponse> manejarDuplicado(RecursoDuplicadoException ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.CONFLICT, ex.getMessage(), request, null);
    }

    @ExceptionHandler(SolicitudInvalidaException.class)
    public ResponseEntity<ApiErrorResponse> manejarSolicitudInvalida(SolicitudInvalidaException ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, ex.getMessage(), request, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> manejarValidacion(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errores.put(err.getField(), err.getDefaultMessage())
        );
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Error de validación", request, errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> manejarGeneral(Exception ex, HttpServletRequest request) {
        return construirRespuesta(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request, null);
    }

    private ResponseEntity<ApiErrorResponse> construirRespuesta(
            HttpStatus status,
            String mensaje,
            HttpServletRequest request,
            Map<String, String> validationErrors
    ) {
        ApiErrorResponse error = new ApiErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                mensaje,
                request.getRequestURI(),
                validationErrors
        );
        return ResponseEntity.status(status).body(error);
    }
}
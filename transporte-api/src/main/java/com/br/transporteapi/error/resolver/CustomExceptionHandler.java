package com.br.transporteapi.error.resolver;

import com.br.transporteapi.error.exception.ErroAoCadastrarException;
import com.br.transporteapi.error.exception.UnidadeDistanciaInvalidaException;
import com.br.transporteapi.error.message.CustomMessageBuilder;
import io.jsonwebtoken.SignatureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setUri(httpServletRequest.getRequestURI())
                .setHttpStatus(HttpStatus.BAD_REQUEST.value())
                .setMessage(
                        ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                                fieldError.getField() + ": " + fieldError.getDefaultMessage())
                                .collect(Collectors.joining(System.lineSeparator())))
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(final EntityNotFoundException ex, final HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setMessage(ex.getLocalizedMessage())
                .setHttpStatus(HttpStatus.NOT_FOUND.value())
                .setUri(httpServletRequest.getRequestURI())
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(final DataIntegrityViolationException ex, final HttpServletRequest httpServletRequest) {

        return new ResponseEntity<>(new CustomMessageBuilder()
                .setMessage(ex.getLocalizedMessage())
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUri(httpServletRequest.getRequestURI())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErroAoCadastrarException.class)
    public ResponseEntity<?> handleErroAoCadastrarException(final ErroAoCadastrarException ex, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setUri(httpServletRequest.getRequestURI())
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMessage(ex.getLocalizedMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAcessDeniedException(final AccessDeniedException ex, final HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setMessage("Você não tem permissão para acessar essa área")
                .setHttpStatus(HttpStatus.UNAUTHORIZED.value())
                .setUri(httpServletRequest.getRequestURI())
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleSignatureException(final SignatureException ex, final HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setHttpStatus(HttpStatus.UNAUTHORIZED.value())
                .setUri(httpServletRequest.getRequestURI())
                .setMessage("O validade do seu token não pôde ser confirmada")
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnidadeDistanciaInvalidaException.class)
    public ResponseEntity<?> handleUnidadeDistanciaInvalidaException(final UnidadeDistanciaInvalidaException ex, final HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setHttpStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
                .setMessage(ex.getMessage())
                .setUri(httpServletRequest.getRequestURI())
                .build(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex, final HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .setMessage("Não conseguimos a forma do conteúdo da sua solicitação")
                .setUri(httpServletRequest.getRequestURI())
                .build(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(final Exception ex, final HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUri(httpServletRequest.getRequestURI())
                .setMessage("O servidor encontrou algum erro ao processar sua solicitação")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(final BadCredentialsException ex, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setHttpStatus(HttpStatus.UNAUTHORIZED.value())
                .setUri(httpServletRequest.getRequestURI())
                .setMessage("Não foi possível realizar login com o email e senha enviados")
                .build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(final UsernameNotFoundException ex, HttpServletRequest httpServletRequest) {
        return new ResponseEntity<>(new CustomMessageBuilder()
                .setHttpStatus(HttpStatus.UNAUTHORIZED.value())
                .setUri(httpServletRequest.getRequestURI())
                .setMessage(ex.getMessage())
                .build(), HttpStatus.UNAUTHORIZED);
    }

}

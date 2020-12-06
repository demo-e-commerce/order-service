package com.example.demo.controller.exception;

import com.example.demo.controller.dto.ApiErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiErrorDto> buildErrorResponse(HttpStatus status, Throwable ex) {
        return ResponseEntity.status(status).body(new ApiErrorDto(status, ex.getMessage()));
    }

    @ExceptionHandler(value = {Throwable.class})
    public ResponseEntity<ApiErrorDto> handUnExpectedException(Throwable ex, WebRequest request) {
        LOGGER.error(request.getContextPath(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiErrorDto> handleNotFoundException(NotFoundException ex, WebRequest request) {
        LOGGER.error(request.getContextPath(), ex);
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<ApiErrorDto> handleNotFoundException(MissingServletRequestParameterException ex, WebRequest request) {
        LOGGER.error(request.getContextPath(), ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }
}

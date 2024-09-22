package com.musinsa.coordination.exception.handler;

import com.musinsa.coordination.exception.*;
import com.musinsa.coordination.model.response.ErrorResponse;
import com.musinsa.coordination.type.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class ApiExceptionHandlerAdvice {

    @ExceptionHandler(AbstractException.class)
    public ResponseEntity<ErrorResponse> abstractExceptionHandler(AbstractException ex) {

        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getErrorResponse());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.from(
                ResponseCode.FAILURE,
                ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {

        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.from(ResponseCode.FAILURE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(BindingResultException.class)
    public ResponseEntity<ErrorResponse> bindResultExceptionHandler(BindingResultException ex) {

        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.from(ResponseCode.FAILURE, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpRequestMethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException ex) {

        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.from(ResponseCode.FAILURE, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> unKnownExceptionHandler(Exception ex) {

        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = ErrorResponse.from(ResponseCode.FAILURE, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}

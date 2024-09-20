package com.musinsa.coordination.exception.handler;

import com.musinsa.coordination.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice(basePackages = "com.musinsa.coordination.controller.api")
public class ApiExceptionHandlerAdvice {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> CategoryNotFoundExceptionHandler(CategoryNotFoundException ex) {

        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}

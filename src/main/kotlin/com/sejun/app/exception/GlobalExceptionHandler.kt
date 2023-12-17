package com.sejun.app.exception

import com.sejun.app.common.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun customExceptionHandler(e: CustomException): ResponseEntity<Response> {
        return ResponseEntity.status(e.statusCode).body(Response(e.statusCode, e.message!!))
    }
}
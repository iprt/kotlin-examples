package org.example.hello.config

import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * controller 层的异常处理
 *
 * @author winterfell
 * @since 2021/1/6
 */
@RestControllerAdvice
class ControllerExceptionHandler {

    private val log = getLogger(this::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<String> {
        log.error(StringUtils.EMPTY, exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(exception.message ?: StringUtils.EMPTY)
    }
}
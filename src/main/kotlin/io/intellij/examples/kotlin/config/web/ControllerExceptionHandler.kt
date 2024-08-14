package io.intellij.examples.kotlin.config.web

import io.intellij.examples.kotlin.funcs.getLogger
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.NoHandlerFoundException

/**
 * Controller exception handler
 *
 * @constructor Create empty Controller exception handler
 */
@ControllerAdvice
class ControllerExceptionHandler {

    private val log = getLogger(this::class.java)

    /**
     * Handle exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<String> {
        log.error(StringUtils.EMPTY, exception)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(exception.message ?: StringUtils.EMPTY)
    }

    /**
     * Handle no handler found exception
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNoHandlerFoundException(e: NoHandlerFoundException): ResponseEntity<String> {
        log.error(StringUtils.EMPTY, e)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(e.message ?: StringUtils.EMPTY)
    }

}
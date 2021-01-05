package org.example.hello.config

import org.apache.commons.lang3.StringUtils
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author winterfell
 * @since 2021/1/6
 */
@RestControllerAdvice
class ControllerExceptionHandler {
    private val log = getLogger(ControllerExceptionHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): String? {
        log.error(StringUtils.EMPTY, exception)
        return exception.message
    }
}
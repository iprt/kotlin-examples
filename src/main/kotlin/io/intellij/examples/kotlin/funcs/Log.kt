package io.intellij.examples.kotlin.funcs

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Log
 *
 *
 * @author tech@intellij.io
 * @since 2021/1/5
 */

fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)

/*
abstract class Log {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)
}
*/

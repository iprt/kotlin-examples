package org.example.hello.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author zhuzhenjie
 * @since 2021/1/5
 */
//abstract class Log {
//    val log: Logger = LoggerFactory.getLogger(this.javaClass)
//}

fun getLogger(forClass: Class<*>): Logger = LoggerFactory.getLogger(forClass)

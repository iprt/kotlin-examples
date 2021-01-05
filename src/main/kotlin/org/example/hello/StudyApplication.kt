package org.example.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author zhuzhenjie
 * @since 2021/1/5
 */
@SpringBootApplication
class StudyKotlinApplication

fun main(args: Array<String>) {
    runApplication<StudyKotlinApplication>(*args)
}
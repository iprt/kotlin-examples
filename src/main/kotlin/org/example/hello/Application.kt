package org.example.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author zhuzhenjie
 * @since 2021/1/5
 */
@SpringBootApplication
class KotlinExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinExampleApplication>(*args)
}
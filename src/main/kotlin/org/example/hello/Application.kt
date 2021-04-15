package org.example.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Kotlin example application
 *
 * @constructor Create empty Kotlin example application
 */
@SpringBootApplication
class KotlinExampleApplication

/**
 * Main
 *
 * @param args
 */
fun main(args: Array<String>) {
    runApplication<KotlinExampleApplication>(*args)
}
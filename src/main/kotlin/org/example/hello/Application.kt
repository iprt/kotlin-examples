package org.example.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.integration.annotation.IntegrationComponentScan

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
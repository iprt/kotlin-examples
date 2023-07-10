package org.iproute.examples.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * Kotlin example application
 *
 * @constructor Create empty Kotlin example application
 */
@SpringBootApplication
@ConfigurationPropertiesScan
class KotlinApplication

/**
 * Main
 *
 * @param args
 */
fun main(args: Array<String>) {
    runApplication<KotlinApplication>(*args)
}
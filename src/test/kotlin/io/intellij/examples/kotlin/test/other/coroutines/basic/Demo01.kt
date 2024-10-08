package io.intellij.examples.kotlin.test.other.coroutines.basic.Demo01.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo01
 *
 * @author tech@intellij.io
 * @since 2023/7/3
 */
fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

package org.example.test.other.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo05
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main() = runBlocking {

    this.launch {
        delay(1000L)
        println("World!")

    }

    println("Hello,")

}
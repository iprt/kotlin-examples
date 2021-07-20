package org.example.test.other.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo02
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main() {

    GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    println("Hello,")

    runBlocking {
        delay(2000L)
    }
}
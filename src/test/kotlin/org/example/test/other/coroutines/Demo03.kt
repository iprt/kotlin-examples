package org.example.test.other.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo03
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main(): Unit = runBlocking<Unit> {
    GlobalScope.launch {
        println("World!")
        delay(1000L)
    }

    println("Hello,")
    delay(2000L)
}
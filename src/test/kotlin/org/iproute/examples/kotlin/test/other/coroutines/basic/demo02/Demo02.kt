package org.iproute.examples.kotlin.test.other.coroutines.basic.demo02

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo02
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */

fun main() {
    runBlocking {
        launch {
            doWorld()
        }
        println("Hello,")
    }

    runBlocking {
        doWorld2()
    }

    runBlocking {
        doWorld3()
    }

    println("Done!")
}

suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

suspend fun doWorld2() = coroutineScope {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}

suspend fun doWorld3() = coroutineScope {

    launch {
        delay(2000L)
        println("World 2")
    }

    launch {
        delay(1000L)
        println("World 1")
    }

    println("Hello,")
}

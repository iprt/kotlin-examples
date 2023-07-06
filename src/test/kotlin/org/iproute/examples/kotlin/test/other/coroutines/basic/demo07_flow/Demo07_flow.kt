package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo07_flow
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */
fun main() = runBlocking {
    launch {
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }

    simpleFlow().collect {
        println(it)
    }
}

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

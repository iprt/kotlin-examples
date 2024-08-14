package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow_cancel.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Demo07_flow_cancel
 *
 * @author tech@intellij.io
 * @since 2023/7/4
 */
fun main() = runBlocking {
    // 在 250 毫秒后超时
    withTimeoutOrNull(250) {
        simpleFlow3().collect {
            println(it)
        }
    }

    println("Done")
}


fun simpleFlow3(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}



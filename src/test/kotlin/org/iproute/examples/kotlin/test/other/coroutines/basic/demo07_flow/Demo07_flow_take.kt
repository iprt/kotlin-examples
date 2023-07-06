package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow_take.kt

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

/**
 * Demo07_flow_take
 *
 * @author zhuzhenjie
 * @since 2023/7/4
 */
fun main() = runBlocking {
    numbers().take(2)
        .collect {
            println(it)
        }
}

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}
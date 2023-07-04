package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_asyncflow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Demo07_flow2
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */

fun main() {

    runBlocking {
        println("Calling simple function...")
        val flow = simpleFlow2()

        println("Calling collect...")
        flow.collect {
            println(it)
        }

        println("Calling collect again...")
        flow.collect {
            println(it)
        }

    }
}


fun simpleFlow2(): Flow<Int> = flow {
    println("Flow started")

    for (i in 1..3) {
        delay(100)
        emit(i)
    }

}

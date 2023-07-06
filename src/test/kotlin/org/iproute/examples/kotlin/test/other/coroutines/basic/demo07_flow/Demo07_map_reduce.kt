package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_map_reduce.kt

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * Demo07_map_reduce
 *
 * @author zhuzhenjie
 * @since 2023/7/4
 */
fun main() = runBlocking {

    val sum = (1..5).asFlow().map { i ->
        i * i
    }.reduce { a, b -> a + b }

    println(sum)

}

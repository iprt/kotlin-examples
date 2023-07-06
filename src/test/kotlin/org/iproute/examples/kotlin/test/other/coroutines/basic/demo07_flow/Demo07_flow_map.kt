package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow_map.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * Demo07_flow_map
 *
 * @author zhuzhenjie
 * @since 2023/7/4
 */
fun main() = runBlocking {
    (1..3).asFlow()
        .map {
            performRequest(it)
        }
        .collect {
            println(it)
        }
}

suspend fun performRequest(request: Int): String {
    delay(100) // 模仿长时间运行的异步工作
    return "response $request"
}
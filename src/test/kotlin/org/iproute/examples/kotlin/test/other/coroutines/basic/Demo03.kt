package org.iproute.examples.kotlin.test.other.coroutines.basic.Demo03.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo03
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */

fun main() = runBlocking {
    // 启动大量协程
    repeat(50_000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}
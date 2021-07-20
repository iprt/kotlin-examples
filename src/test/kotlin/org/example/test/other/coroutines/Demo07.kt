package org.example.test.other.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo07
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main() = runBlocking {
    launch {
        doWorld()
    }
    println("Hello,")
}

// 这是你的第一个挂起函数
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}
package org.example.test.other.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo06
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main() = runBlocking {

    this.launch {
        delay(200L)
        println("Task from runBlocking")
    }

    // 创建一个协程作用域
    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}
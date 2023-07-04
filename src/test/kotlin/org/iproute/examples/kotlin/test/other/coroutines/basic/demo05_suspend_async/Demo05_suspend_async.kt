package org.iproute.examples.kotlin.test.other.coroutines.basic.demo05_suspend_async

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * Demo05_suspend_async
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */
fun main() {

    runBlocking {

        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("Two answer is ${one + two}")
        }

        println("Complete in $time ms")
    }

    //  async
    runBlocking {

        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }

            // 执行一些计算
            one.start()
            two.start()

            println("The answer is ${one.await() + two.await()}")
        }

        println("Complete in $time ms")
    }
}


suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    return 13
}


suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 29
}

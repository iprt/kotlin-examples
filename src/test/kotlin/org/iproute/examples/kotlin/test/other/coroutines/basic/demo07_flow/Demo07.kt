package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Demo07
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */
fun main() {

    println("simple")
    simple().forEach {
        println(it)
    }

    println("simple2")
    simple2().forEach {
        println(it)
    }

    println("simple3")
    runBlocking {
        simple3().forEach {
            println(it)
        }
    }

}


fun simple(): List<Int> = listOf(1, 2, 3)


fun simple2(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(100)
        // 产生下一个值
        yield(i)
    }
}


suspend fun simple3(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3)
}

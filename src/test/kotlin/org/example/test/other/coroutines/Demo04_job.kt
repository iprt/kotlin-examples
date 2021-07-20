package org.example.test.other.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo04_job
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000L)
        println("World!")
    }

    println("Hello,")
    job.join()
}
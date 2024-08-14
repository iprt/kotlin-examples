package io.intellij.examples.kotlin.test.other.coroutines.basic.Demo04_Job.kt

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Demo04_Job
 *
 * @author tech@intellij.io
 * @since 2023/7/3
 */

fun main() = runBlocking {

    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }


    delay(1300L)

    println("main: I'm tried of waiting!")
    job.cancel()
    job.join()

    println("main: Now I can quit!")
}

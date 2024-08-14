package org.iproute.examples.kotlin.test.other.coroutines.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Channel01
 *
 * @author tech@intellij.io
 * @since 2023/7/3
 */
fun main() {

    runBlocking {
        val channel = Channel<Int>()

        launch {
            for (x in 1..5) {
                channel.send(x * x)
            }
        }

        // 这里我们打印了 5 次被接收的整数：
        repeat(5) {
            println(channel.receive())
        }

        println("Done!")

    }

}
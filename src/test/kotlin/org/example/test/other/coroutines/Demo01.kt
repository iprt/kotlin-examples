package org.example.test.other.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Demo01
 *
 * @author Jzz
 * @since 2021/7/15
 */
fun main() {

    // 在后台启动一个新的协程并继续
    GlobalScope.launch {

        // 非阻塞的等待1秒钟
        delay(1000L)

        println("World!")
    }

    // 协程已在等待时主线程还在继续
    println("Hello,")

    // 阻塞主线程 2 秒钟来保证 JVM 存活
    Thread.sleep(2000L)

}

/*
本质上，协程是轻量级的线程。 它们在某些 CoroutineScope 上下文中与 launch 协程构建器 一起启动

这是因为 delay 是一个特殊的 挂起函数 ，它不会造成线程阻塞，但是会 挂起 协程，并且只能在协程中使用。
 */
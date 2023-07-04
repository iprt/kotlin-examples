package org.iproute.examples.kotlin.test.other.coroutines.basic.demo06_ctx

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

/**
 * `Demo06_ CoroutineContext
 *
 * @author zhuzhenjie
 * @since 2023/7/3
 */
fun main() = runBlocking<Unit> {

    // 运行在父协程的上下文中，即 runBlocking 主协程
    launch {
        println("main runBlocking       : I'm working in thread ${Thread.currentThread().name}")
    }


    // 不受限的——将工作在主线程中
    launch(Dispatchers.Unconfined) {
        println("Unconfined             : I'm working in thread ${Thread.currentThread().name}")
    }

    // 将会获取默认调度器
    launch(Dispatchers.Default) {
        println("Default                : I'm working in thread ${Thread.currentThread().name}")
    }

    // 将使它获得一个新的线程
    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext : I'm working in thread ${Thread.currentThread().name}")
    }

}

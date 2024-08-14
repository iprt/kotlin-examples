package io.intellij.examples.kotlin.service

import io.intellij.examples.kotlin.funcs.getLogger
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.apache.commons.lang3.RandomUtils
import org.slf4j.Logger
import org.springframework.stereotype.Service

/**
 * CoroutinesService 协程示例类
 *
 * @author tech@intellij.io
 * @since 2023/7/17
 */
@Service
class CoroutinesService {

    private val log: Logger = getLogger(this::class.java)

    /**
     * 使用协程
     *
     * @return
     */
    suspend fun sumTwo(): Int = coroutineScope {
        log.info("Current Thread is : ${Thread.currentThread().name}")
        // 分别异步调用，换成 getInt2() 再测一遍
        val i1: Deferred<Int> = async { getInt() }
        val i2: Deferred<Int> = async { getInt() }
        // 聚合
        i1.await() + i2.await()
    }


    /**
     * None-Blocking web client
     *
     * very fast
     * @return
     */
    suspend fun getInt(): Int {
        log.info("Current Thread is : ${Thread.currentThread().name}")
        return 1
    }


    /**
     * Blocking web client
     *
     * very slow
     * @return
     */
    suspend fun getInt2(): Int {
        delay(RandomUtils.nextLong(1, 1000))
        return 2
    }

}
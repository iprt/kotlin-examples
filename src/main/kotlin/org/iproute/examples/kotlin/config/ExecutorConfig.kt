package org.iproute.examples.kotlin.config

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.atomic.AtomicInteger

/**
 * ExecutorConfig
 *
 * @author zhuzhenjie
 * @since 2023/7/18
 */
@Configuration
class ExecutorConfig {

    companion object {
        const val CORE_POOL_SIZE = 5
        const val MAX_POOL_SIZE = 100
        const val QUEUE_CAPACITY = 20
        const val THREAD_NAME_PREFIX = "dispatcher-async-"
    }


    @Bean
    fun asyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()

        executor.corePoolSize = CORE_POOL_SIZE
        executor.maxPoolSize = MAX_POOL_SIZE
        executor.setQueueCapacity(QUEUE_CAPACITY)
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX)

        // 拒绝策略 当任务满额时将新任务(如果有的话)，打回到原线程去执行。
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())

        executor.initialize()
        return executor
    }


    @Bean
    fun dispatcher(asyncExecutor: Executor): CoroutineDispatcher {
        return asyncExecutor.asCoroutineDispatcher()
    }

}


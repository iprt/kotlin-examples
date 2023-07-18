package org.iproute.examples.kotlin.config

import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicInteger

/**
 * 参考 DefaultThreadFactory
 *
 * @property group
 * @property namePrefix
 * @constructor Create empty Dispatcher thread factory
 */
class DispatcherThreadFactory : ThreadFactory {

    companion object {
        val poolNumber = AtomicInteger(1)
    }

    private var group: ThreadGroup
    private var namePrefix: String
    private val threadNumber = AtomicInteger(1)

    init {
        val s = System.getSecurityManager();
        this.group = if (s != null) {
            s.threadGroup
        } else {
            Thread.currentThread().threadGroup
        }

        namePrefix = "dispatcher-pool-${poolNumber.getAndIncrement()}-thread-"
    }

    override fun newThread(r: Runnable): Thread {
        val t = Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0)

        if (t.isDaemon) {
            t.isDaemon = false
        }

        if (t.priority != Thread.NORM_PRIORITY) {
            t.priority = Thread.NORM_PRIORITY
        }

        return t
    }
}

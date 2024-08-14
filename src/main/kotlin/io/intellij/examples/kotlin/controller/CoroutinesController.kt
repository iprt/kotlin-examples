package io.intellij.examples.kotlin.controller

import io.intellij.examples.kotlin.funcs.getLogger
import io.intellij.examples.kotlin.service.CoroutinesService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * CoroutinesController
 *
 * @author tech@intellij.io
 * @since 2023/7/24
 */
@RequestMapping("/coroutines")
@RestController
class CoroutinesController(
    private val dispatcher: CoroutineDispatcher
) {

    private val log = getLogger(this::class.java)

    // https://stackoverflow.com/questions/58558802/spring-boot-rest-service-with-kotlin-coroutines
    // private val dispatcher = Executors.newFixedThreadPool(5, DispatcherThreadFactory()).asCoroutineDispatcher()

    @Autowired
    lateinit var coroutinesService: CoroutinesService

    /**
     * 这种写法是有问题的
     *
     * @return
     */
    @GetMapping("/sum")
    fun sum(): String = runBlocking(dispatcher) {
        log.info("当前线程为: ${Thread.currentThread().name}")

        "Sum: ${coroutinesService.sumTwo()}"
    }

}
package org.iproute.examples.kotlin.controller

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.iproute.examples.kotlin.service.CoroutinesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * CoroutinesExampleController
 *
 * @author zhuzhenjie
 * @since 2023/7/17
 */
@RestController
@RequestMapping("/coroutines")
class CoroutinesExampleController(
    private val dispatcher: CoroutineDispatcher
) {

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
        "Sum: ${coroutinesService.sumTwo()}"
    }

}
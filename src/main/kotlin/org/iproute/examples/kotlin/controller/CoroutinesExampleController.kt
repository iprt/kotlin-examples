package org.iproute.examples.kotlin.controller

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import org.iproute.examples.kotlin.service.CoroutinesExampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Executors

/**
 * CoroutinesExampleController
 *
 * @author zhuzhenjie
 * @since 2023/7/17
 */
@RestController
@RequestMapping("/coroutines")
class CoroutinesExampleController {

    private val dispatcher = Executors.newFixedThreadPool(5).asCoroutineDispatcher()

    @Autowired
    lateinit var coroutinesExampleService: CoroutinesExampleService


    /**
     * 这种写法是有问题的
     *
     * @return
     */
    @GetMapping("/sum")
    fun sum(): String = runBlocking(dispatcher) {
        "Sum: ${coroutinesExampleService.sumTwo()}"
    }

}
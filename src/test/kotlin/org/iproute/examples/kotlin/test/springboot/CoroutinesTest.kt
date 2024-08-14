package org.iproute.examples.kotlin.test.springboot

import org.iproute.examples.kotlin.service.CoroutinesService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * CoroutinesTest
 *
 * @author tech@intellij.io
 * @since 2023/7/26
 */
@SpringBootTest
internal class CoroutinesTest {

    @Autowired
    lateinit var coroutinesService: CoroutinesService

    @Test
    fun test() {
        println("--> $coroutinesService")
    }

}
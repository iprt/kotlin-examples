package io.intellij.examples.kotlin.test.springboot

import io.intellij.examples.kotlin.service.CoroutinesService
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
class CoroutinesTest {

    @Autowired
    private lateinit var coroutinesService: CoroutinesService

    @Test
    fun `test get bean class`() {
        println("--> $coroutinesService")
    }

}
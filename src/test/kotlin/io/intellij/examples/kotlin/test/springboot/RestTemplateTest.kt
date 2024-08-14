package io.intellij.examples.kotlin.test.springboot

import io.intellij.examples.kotlin.entities.po.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

/**
 * RestTemplateTest
 *
 * @author tech@intellij.io
 */
@SpringBootTest
class RestTemplateTest {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

    companion object {
        const val KEY = "redis_template_test_key"
        val USER_OBJ = User(9999, "RestTemplateTest", 123.345)
    }

    @Test
    fun `test set key with object`() {
        redisTemplate.opsForValue().set(KEY, USER_OBJ)
    }

    @Test
    fun `test get Object by key`() {
        val user = redisTemplate.opsForValue().get(KEY) as User
        println("get user = $user")
    }

}
package io.intellij.examples.kotlin.config.redis

import io.intellij.examples.kotlin.entities.po.User
import io.intellij.examples.kotlin.entities.po.UserDetail
import io.intellij.examples.kotlin.funcs.getLogger
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.*

/**
 * RedisRunner
 *
 * @author tech@intellij.io
 * @since 2023/7/4
 */
@Component
class RedisRunner : CommandLineRunner {

    companion object {
        const val KEY = "user"
        val VALUE = User(
            123, "test", 99.9,
            UserDetail("RedisRunner", Date(), Date())
        )
    }

    private val log: Logger = getLogger(this::class.java)

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, Any>


    override fun run(vararg args: String?) {
        log.info("测试CommandLineRunner")

        log.info("测试redis写入")
        redisTemplate.opsForValue().set(KEY, VALUE)

        log.info("测试redis读出")
        val user = redisTemplate.opsForValue().get(KEY) as User
        log.info("Get Object From Redis By RedisTemplate : $user")
    }

}
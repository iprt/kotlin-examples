package org.iproute.examples.kotlin.controller

import org.iproute.examples.kotlin.config.getLogger
import org.iproute.examples.kotlin.entities.po.User
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * Example controller
 *
 * @constructor Create empty Example controller
 */
@RestController
@RequestMapping("/kotlin-examples")
@Validated
class ExampleController : CommandLineRunner {
    private val log: Logger = getLogger(this::class.java)
    /*
    companion object : Log() {}
    private val log: Logger = LoggerFactory.getLogger(DemoController::class.java)
    */

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

    /**
     * Say hello
     *
     * @return
     */
    @GetMapping("hello")
    fun sayHello(): String = "hello kotlin"

    /**
     * Say hello
     *
     * @param name
     * @return
     */
    @GetMapping("/sayHello")
    fun sayHello(@RequestParam("name") name: String): String = "hello $name"

    /**
     * Say hello with user
     *
     * @param user
     * @return
     */
    @PostMapping("/sayHelloByUser")
    fun sayHelloWithUser(@RequestBody user: User): String {
        log.info("user is {}", user)
        return "hello ${user.name}"
    }

    /**
     * Copy user
     *
     * @param user
     * @return
     */
    @PostMapping("/copyUser")
    fun copyUser(@RequestBody user: User): User {
        log.info("need copy user is : {}", user)
        return copy(user)("Ray")
    }

    /**
     * Http servlet request test
     *
     * @param httpServletRequest
     * @return
     */
    @GetMapping("/testHttpServletRequest")
    fun httpServletRequestTest(httpServletRequest: HttpServletRequest): String {
        val uri = httpServletRequest.requestURI
        return "Test HttpServletRequest and uri is $uri"
    }

    // 返回一个函数 函数式编程初入
    private fun copy(user: User): (String) -> User {
        val userOther = User(
            id = user.id ?: (123 + 3306),
            grade = user.grade ?: (100.0 + 99)
        )
        // 返回产生一个user的函数
        return fun(name: String): User = user.apply {
            userOther.name = user.name ?: name
        }
    }

    override fun run(vararg args: String?) {
        log.info("测试CommandLineRunner")

        log.info("测试redis写入")
        redisTemplate.opsForValue().set("user", User(123, "test", 99.9))

        log.info("测试redis读出")
        val user = redisTemplate.opsForValue().get("user") as User
        log.info("get from redis : $user")
    }
}
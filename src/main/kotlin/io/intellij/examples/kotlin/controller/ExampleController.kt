package io.intellij.examples.kotlin.controller

import io.intellij.examples.kotlin.entities.po.User
import io.intellij.examples.kotlin.funcs.getLogger
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Example controller
 *
 * @constructor Create empty Example controller
 */
@RestController
@RequestMapping("/kotlin-examples")
@Validated
class ExampleController {
    private val log: Logger = getLogger(this::class.java)

    /*
    companion object : Log() {}
    private val log: Logger = LoggerFactory.getLogger(DemoController::class.java)
    */

    /**
     * Say hello
     *
     * @return
     */
    @GetMapping("/hello")
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

}
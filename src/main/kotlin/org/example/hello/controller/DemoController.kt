package org.example.hello.controller

import org.example.hello.entities.po.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.web.bind.annotation.*

/**
 * @author zhuzhenjie
 * @since 2021/1/5
 */
@RestController
@RequestMapping("/kotlin-demo")
class DemoController : CommandLineRunner {
    //    companion object : Log() {}
    private val log: Logger = LoggerFactory.getLogger(DemoController::class.java)

    @GetMapping("hello")
    fun sayHello(): String {
        return "hello kotlin"
    }

    @GetMapping("/sayHello")
    fun sayHello(@RequestParam("name") name: String): String {
        return "hello $name"
    }

    @PostMapping("/sayHelloByUser")
    fun sayHelloWithUser(@RequestBody user: User): String {
        log.info("user is {}", user)
        return "hello ${user.name}"
    }

    @PostMapping("/copyUser")
    fun copyUser(@RequestBody user: User): User {
        log.info("need copy user is : {}", user)
        return copy(user)("winterfell")
    }

    // 返回一个函数 函数式编程初入
    fun copy(user: User): (String) -> User {
        val userOther = User(
                id = user.id ?: 123 + 3306,
                grade = user.grade ?: 100.0 + 99
        )
        // 返回产生一个user的函数
        return fun(name: String): User {
            if (user.name == null) {
                userOther.name = name
            } else {
                userOther.name = user.name
            }
            return userOther
        }
    }

    override fun run(vararg args: String?) {
        log.info("测试CommandLineRunner | {}")
    }
}
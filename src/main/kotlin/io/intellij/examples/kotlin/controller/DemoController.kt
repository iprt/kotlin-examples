package io.intellij.examples.kotlin.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * DemoController
 *
 * @author tech@intellij.io
 */
@RestController
@RequestMapping
class DemoController {

    @GetMapping("/")
    fun sayHello(): String {
        return "Hello,Kotlin!"
    }

}
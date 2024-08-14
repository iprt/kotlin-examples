package org.iproute.examples.kotlin.test.other.study0

/**
 * @author tech@intellij.io
 * @since 2020/12/29
 */

// when表达式 感觉像java里面的switch表达式

/**
 * 参数是 obj 返回是 String类型
 */
fun describe(obj: Any): String =
    when (obj) {
        1 -> "one"
        "hello" -> "Greeting"
        is Long -> "Long"
        !is String -> "Not a string"
        else -> "Unknown"
    }

fun main() {
    println(describe(1))

    println(describe("hello"))

    println(describe(1000L))

    println(describe(2))

    println(describe("other"))
}
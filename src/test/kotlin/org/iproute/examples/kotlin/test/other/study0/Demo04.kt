package org.iproute.examples.kotlin.test.other.study0

/**
 * @author zhuzhenjie
 * @since 2020/12/28
 */

// 条件表达式
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

// 在kotlin中 if 也可以用作表达式
fun maxOf1(a: Int, b: Int): Int = if (a > b) a else b

fun main() {
    // 这里还是使用了字符串模板
    println("max of 0 and 42 is ${maxOf(0, 42)}")

    println("max of 0 and 42 is ${maxOf1(0, 42)}")
}
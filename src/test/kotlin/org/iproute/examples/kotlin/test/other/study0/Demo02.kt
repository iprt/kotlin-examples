package org.iproute.examples.kotlin.test.other.study0

/**
 * @author tech@intellij.io
 * @since 2020/12/28
 */
// 函数
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 将表达式作为函数体，自动推断函数的返回值
fun sum1(a: Int, b: Int) = a + b

// 函数返回毫无意义的值 关键字 Unit
fun sum2(a: Int, b: Int): Unit {
    println("sum2 ===> $a + $b = ${a + b}")
}

// Unit 返回类型可以省略：
fun sum3(a: Int, b: Int) {
    println("sum3 ===> $a + $b = ${a + b}")
}

fun main() {
    val a = 3
    val b = 4
    println("$a + $b = " + sum(a, b))

    println("sum of 19 and 23 is ${sum1(19, 23)}")

    sum2(19, 56)

    sum3(99, 56)
}
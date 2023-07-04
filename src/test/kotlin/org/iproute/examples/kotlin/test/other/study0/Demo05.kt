package org.iproute.examples.kotlin.test.other.study0

/**
 * @author winterfell
 * @since 2020/12/29
 */

fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    if (x == null) {
        println("Wrong Number format in arg1 : $arg1")
        return
    }
    if (y == null) {
        println("Wrong Number format in arg1 : $arg2")
        return
    }
    // 在空检测之后，x 与 y 会自动转化为非空值
    println(x * y)
}


fun main() {

    printProduct("6", "7")

    printProduct("a", "7")

    printProduct("99", "b")

}
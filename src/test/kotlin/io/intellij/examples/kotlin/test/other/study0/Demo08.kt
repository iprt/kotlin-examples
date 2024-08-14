package io.intellij.examples.kotlin.test.other.study0

/**
 * 使用区间
 *
 * @author tech@intellij.io
 * @since 2021/1/3
 */

fun main() {
    val x = 10
    val y = 9

    if (x in 1..y + 1) {
        println("fits is range")
    }

    // 检测某个数字是否在区间外

    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }

    if (list.size !in list.indices) {
        println("list size is out if valid list indices range ,too")
    }

    // 区间迭代
    for (x in 1..5) {
        println(x)
    }

    // 数列迭代
    println("数列迭代1")
    for (x in 1..10 step 2) {
        println(x)
    }

    println("数列迭代2")
    for (x in 9 downTo 0 step 3) {
        println(x)
    }
}
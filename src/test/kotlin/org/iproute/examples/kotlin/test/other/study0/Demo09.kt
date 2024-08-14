package org.iproute.examples.kotlin.test.other.study0

/**
 * 集合
 * @author tech@intellij.io
 * @since 2021/1/3
 */

fun main() {

    // 集合迭代
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }

    // 使用 in 运算符来判断集合内是否包含某实例
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }


    // 使用 lambda 表达式来过滤（filter）与映射（map）集合

    println("kotlin 的 lambda 表达式")
    items.filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }


}
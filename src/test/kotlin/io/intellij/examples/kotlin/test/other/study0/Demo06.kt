package io.intellij.examples.kotlin.test.other.study0

/**
 * @author tech@intellij.io
 * @since 2020/12/29
 */
// is 用法
fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

fun getStringLength2(obj: Any): Int? {
    if (obj !is String) return null
    return obj.length
}

fun getStringLength3(obj: Any): Int? {
    if (obj is String && obj.length > 0) {
        return obj.length
    }
    return null
}


fun main() {
    val items = listOf("hello", "world", "this", "that")
    for (item in items) {
        println(item)
    }

    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }

    // while 循环
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

}

package org.iproute.examples.kotlin.test.other.chain.Chain2.kt

/**
 * Chain2
 *
 * @author zhuzhenjie
 * @since 2023/7/13
 */
fun main() {
    val input = "hello"

    val insert = saveToDB(input)
    log("$insert")

    val a = append(input, "a")

    val insert2 = saveToDB(a)
    log("$insert2")

    val b = append(a, "b")
    log(b)
}

fun saveToDB(str: String): Int {
    println("保存到数据库 save.data = $str -> 并返回保存个数")
    return 1
}


fun append(str: String, append: String): String {
    return "$str-$append"
}

fun log(str: String) {
    println("打印日志：$str")
}
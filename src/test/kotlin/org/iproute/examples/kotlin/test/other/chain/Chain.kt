package org.iproute.examples.kotlin.test.other.chain.Chain.kt

/**
 * Chain
 *
 * @author zhuzhenjie
 * @since 2023/7/13
 */
fun main() {
    val input = "hello"
    println(
        c(
            b(
                a(input)
            )
        )
    )
}


fun a(str: String): String {
    return "$str-a"
}


fun b(str: String): String {
    return "$str-b"
}


fun c(str: String): String {
    return "$str-c"
}

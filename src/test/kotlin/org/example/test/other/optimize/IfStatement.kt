package org.example.test.other.optimize

/**
 * @author zhuzhenjie
 * @since 2021/1/27
 */

fun main() {
    optIf1(true)

    var a: String? = null


    println(a ?: "hello")
}


fun optIf1(status: Boolean) {
    // 原始用法
    if (status) {
        doSth()
    }

    // 修改后的代码
    status.takeIf { status }?.apply { doSth() }
}


fun optIf2() {
}


fun doSth() {
    println("优化if语句")
}
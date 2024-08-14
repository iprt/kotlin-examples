package io.intellij.examples.kotlin.test.other.optimize

/**
 * @author tech@intellij.io
 * @since 2021/1/27
 */

fun main() {
    optIf1(true)

    println("========================================")

    val a: String? = null
    println(a ?: "hello")

    println("========================================")

    optIf2()

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
    val demo = Demo("winterfell").apply {
        this.id = 123
        this.grade = 99.9
    }


    demo.takeIf { it.grade == null }?.apply {
        println("demo grade is null")
    }

    val demo2 = Demo("wintefell", 111, null)

    demo2.takeIf { it.grade == null }?.apply {
        println("demo2 grade is null")
    }
}

fun doSth() {
    println("优化if语句")
}


private class Demo(val name: String?) {
    var id: Int? = null
    var grade: Double? = null

    constructor(name: String, id: Int?, grade: Double?) : this(name) {
        this.id = id
        this.grade = grade
    }

}
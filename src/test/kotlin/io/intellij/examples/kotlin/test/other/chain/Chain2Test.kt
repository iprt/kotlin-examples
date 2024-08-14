package io.intellij.examples.kotlin.test.other.chain.Chain2Test.kt

import org.junit.jupiter.api.Test


class Chain2Test {

    @Test
    fun test() {
        val input = "hello"

        val insert = saveToDB(input)
        log("$insert")

        val a = append(input, "a")

        val insert2 = saveToDB(a)
        log("$insert2")

        val b = append(a, "b")
        log(b)
    }

    private fun saveToDB(str: String): Int {
        println("保存到数据库 save.data = $str -> 并返回保存个数")
        return 1
    }


    private fun append(str: String, append: String): String {
        return "$str-$append"
    }

    private fun log(str: String) {
        println("打印日志：$str")
    }
}
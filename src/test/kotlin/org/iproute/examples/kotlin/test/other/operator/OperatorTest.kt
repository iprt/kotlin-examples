package org.iproute.examples.kotlin.test.other.operator

/**
 * OperatorTest
 *
 * @author tech@intellij.io
 */
data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)


data class People(val age: Int, val name: String)

/**
 * 重载 > <
 *
 * @param other
 * @return
 */
operator fun People.compareTo(other: People): Int {
    return this.age - other.age
}


/**
 * 重载 减号
 *
 * @param other
 * @return
 */
operator fun People.minus(other: People): Int {
    return this.age - other.age
}


fun main() {
    val tom = People(18, "Tom")

    val jerry = People(8, "Jerry")

    println(
        "tom > jerry = " + (tom > jerry)
    )


    println(
        "tom - jerry = " + (tom - jerry)
    )
}


package org.iproute.examples.kotlin.test.other.study0

/**
 * Demo11
 *
 * @author zhuzhenjie
 */

data class Bean(var name: String, var age: Int)

data class Point(var x: Int, var y: Int)

operator fun Point.plus(o: Point): Point {
    val x = this.x + o.x
    val y = this.y - o.y
    return Point(x, y)
}

fun main() {
    // apply <==> design-pattern: decorator
    val bean = Bean("docker", 18)
        .apply {
            println("original name is ${this.name}")
            // 业务变化1
            this.name = "kubernetes"
        }
        .apply {
            println("original age is ${this.age}")
            // 业务变化2
            this.age = 30
        }

    println(bean)

    println()

    val p1 = Point(1, 1)
    val p2 = Point(2, 2)

    println(p1 + p2)
}

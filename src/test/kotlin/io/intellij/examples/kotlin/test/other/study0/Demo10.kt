package io.intellij.examples.kotlin.test.other.study0

/**
 * 创建基本类以及实例
 *
 * @author tech@intellij.io
 * @since 2021/1/3
 */

fun main() {
    val rectangle = Rectangle(5.0, 2.0)
    val triangle = Triangle(3.0, 4.0, 5.0)
    println("Area of rectangle is ${rectangle.calculateArea()}, its perimeter is ${rectangle.perimeter}")
    println("Area of triangle is ${triangle.calculateArea()}, its perimeter is ${triangle.perimeter}")
}

abstract class Shape(private val sides: List<Double>) {
    val perimeter: Double get() = sides.sum()
    abstract fun calculateArea(): Double
}

interface RectangleProperties {
    val isSquare: Boolean
}

class Rectangle(var height: Double, var length: Double) : Shape(listOf(height, length, height, length)),
    RectangleProperties {
    override val isSquare: Boolean get() = height == length
    override fun calculateArea(): Double = height * length
}

class Triangle(
    var sideA: Double,
    var sideB: Double,
    var sideC: Double
) : Shape(listOf(sideA, sideB, sideC)) {
    override fun calculateArea(): Double {
        val s = perimeter / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC))
    }
}
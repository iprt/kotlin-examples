package org.iproute.examples.kotlin.test.other.study1

/**
 * 继承
 * @author winterfell
 * @since 2021/1/5
 */

// 默认继承 Any
class Example

/*
 Any 有三个方法， equals() hashCode() toString()
*/

// 开放类的继承
open class Base

open class Base2(p: Int)

// 类的继承
class Derived(p: Int) : Base2(p)


// 覆盖方法
open class Shape {
    open fun draw() {}
    fun fill() {}
}

class Circle() : Shape() {
    override fun draw() {
//        super.draw()
        println("circle draw")
    }
}

open class Shape2 {
    open val vertexCount: Int = 0
}


class Rectangle : Shape2() {
    override val vertexCount: Int
        get() = 123
}

fun main() {
    val circle = Circle()
    circle.draw()


    val rectangle = Rectangle()
    println(rectangle.vertexCount)
}


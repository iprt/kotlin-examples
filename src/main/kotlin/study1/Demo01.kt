package study1

/**
 * 类与继承
 * @author zhuzhenjie
 * @since 2021/1/5
 */

// ======================================== 类 ========================================
/*
 kotlin中的类使用关键字 class 声明类

类声明 由 类名、类头（指定其类型参数，主构造函数等）以及 花括号（{}）包围的类体构成

类声明 = 类名 + 类头 + 类体

类头和类体可以省略

 */
class Invoice {}

class Empty

// ======================================== 构造函数 ========================================

/*
一个类可以有一个主构造函数以及一个或者多个次构造函数。
重点：一个主构造函数

主构造函数不能包含任何代码

*/

// 如果主构造函数没有任何的注解或者可见性修饰符，可以省略constructor关键字
class Person constructor(firstName: String) {}

class Person2(firstName: String) {}


// ======== 初始化块 ========

class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    /*
     主构造函数的参数可以在初始化块中使用，也可以在类体内声明的属性初始化器中使用
     */

    init {
        println("first initialize block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initialize block that prints ${name.length}")
    }

}

// ======== 次构造器 ========


class Person3 {
    var children: MutableList<Person3> = mutableListOf()

    // 次构造器
    constructor(parent: Person3) {
        parent.children.add(parent)
    }
}

// 构造器之间的调用 使用 this
class Person4(val name: String) {
    var children: MutableList<Person4> = mutableListOf()

    constructor(name: String, parent: Person4) : this(name) {
        parent.children.add(this)
    }
}


// 初始化块
class Constructors {
    init {
        // 初始化块先执行
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructors")
    }
}

// 构造函数私有
class DontCreateMe private constructor() {

}

fun main() {
    InitOrderDemo("hello")

    Constructors(1)
}


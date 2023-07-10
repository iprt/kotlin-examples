package org.iproute.examples.kotlin.test.other.optimize

/**
 * kotlin的一些内置函数
 *
 * @author zhuzhenjie
 * @since 2021/2/23
 */

fun main() {

    println("---------------- let ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_let()

    println("---------------- also ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_also()

    println("---------------- with ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_with()

    println("---------------- run ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_run()

    println("---------------- apply ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_apply()

    println("---------------- takeIf ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_takeIf()

    println("---------------- takeUnless ----------------")
    org.iproute.examples.kotlin.test.other.optimize.test_takeUnless()

}


/**
 * let函数
 *
 * 定义： 一个作用域函数
 * 作用：
 *       1. 定义一个变量在一个特定的作用域范围内
 *       2. 避免写一些判断 null 的操作
 * 应用场景：
 *       1. 明确一个变量所处特定的作用域范围内可使用
 *       2. 针对一个可 null 的对象做统一判空处理
 *
 */
private fun test_let() {
    // 作用1：使用it替代object对象去访问其公有的属性 & 方法
/*
    object.let{
        it.todo()
    }
*/

    // 作用2：判断object为null的操作
/*
    object?.let{//表示object不为null的条件下，才会去执行let函数体
        it.todo()
    }
*/
    // 注：返回值 = 最后一行 / return的表达式

    val people = org.iproute.examples.kotlin.test.other.optimize.People("name", 18)

    people?.let {
        it.function1()
        it.function2()
        it.function3()
    }
}


/**
 * also 函数
 *
 * 作用 & 应用场景：类似let函数，但区别在于返回值
 *      - let函数：返回值 = 最后一行 / return的表达式
 *      - also函数：返回值 = 传入的对象的本身
 */
private fun test_also() {
    val people = org.iproute.examples.kotlin.test.other.optimize.People("name", 18)
    val alsoResult = people?.also {
        it.function1()
        it.function2()
        it.function3()
        // 其实这句话是无效的
        999
    }

    val letResult = people.let {
        it.function1()
        it.function2()
        it.function3()
        999
    }

    println(
        """
        |also result = $alsoResult
        |let result = $letResult
    """.trimIndent().trimMargin()
    )

}

/**
 * with函数
 * 作用： 调用同一个对象的多个方法 / 属性时，可以省去对象名重复，直接调用方法名 / 属性即可
 * 应用场景： 需要调用同一个对象的多个方法 / 属性
 *
 */
private fun test_with() {

    val people = org.iproute.examples.kotlin.test.other.optimize.People("carson", 25)
    with(people) {
        println("my name is $name, I am $age years old")
    }

    // 返回值 = 函数块的最后一行 / return表达式
}

/**
 * run函数
 *
 * 作用 & 应用场景
 *   结合了let、with两个函数的作用，即：
 *      1. 调用同一个对象的多个方法 / 属性时，可以省去对象名重复，直接调用方法名 / 属性即可
 *      2. 定义一个变量在特定作用域内
 *      3. 统一做判空处理
 */
private fun test_run() {
/*
    object.run{
        // ...
    }
*/

    // 返回值 = 函数块的最后一行 / return表达式

    val people = org.iproute.examples.kotlin.test.other.optimize.People("carson", 25)

    // 此处要调用people的name 和 age属性，且要判空
    people?.run {
        println("my name is $name, I am $age years old")
    }

}


/**
 * apply函数
 *
 * 作用 & 应用场景
 *   与run函数类似，但区别在于返回值：
 *       - run函数返回最后一行的值 / 表达式
 *       - apply函数返回传入的对象的本身
 */
private fun test_apply() {
    val people = org.iproute.examples.kotlin.test.other.optimize.People("carson", 25)

    val runResult = people.run {
        this.function1()
        this.function2()
        this.function3()
        999
    }

    val applyResult = people.run {
        this.function1()
    }

    println(
        """
        |run   result = $runResult
        |apply result = $applyResult 
    """.trimIndent().trimMargin()
    )
}

private fun test_takeIf() {
    val people = org.iproute.examples.kotlin.test.other.optimize.People("Jack", 24)
    people.takeIf { it.age > 20 }?.run {
        println("Jack's age > 20")
    }

    people.takeIf { it.age > 30 }?.run {
        println("do nothing ...")
    }
}


private fun test_takeUnless() {
    val people = org.iproute.examples.kotlin.test.other.optimize.People("Niko", 24)

    people.takeUnless { it.age > 20 }?.run {
        println("do nothing ...")
    }

    people.takeUnless { it.age > 30 }?.run {
        println("Niko's age <= 30")
    }
}

private class People(val name: String, val age: Int) {
    fun function1() {}
    fun function2() {}
    fun function3() {}
}
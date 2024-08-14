package io.intellij.examples.kotlin.entities.po

import java.io.Serializable

/**
 * 用户类
 *
 * @author tech@intellij.io
 * @since 2021/1/5
 */
data class User(
    var id: Long? = null,
    var name: String? = null,
    var grade: Double? = null
) : Serializable

/*
常规写法 在对象初始化的时候有默认值
int intValue;          var intValue: Int = 0
long longValue;        var longValue: Long = 0
float floatValue;      var floatValue: Float = 0.toFloat()

包装类写法 在对象初始化的时候默认为null
Byte byteValue;        val byteValue: Byte? = null
Short shortValue;      val shortValue: Short? = null
Integer intValue;      val intValue: Int? = null
...
 */
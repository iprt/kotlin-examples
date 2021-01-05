package org.example.hello.config

import org.jasypt.util.text.BasicTextEncryptor

/**
 * @author winterfell
 * @since 2021/1/6
 */
fun main() {
    val textEncryptor = BasicTextEncryptor()

    textEncryptor.setPassword("kotlin-study")

    val username = textEncryptor.encrypt("hello")
    val password = textEncryptor.encrypt("world")
    val jdbcUrl = textEncryptor.encrypt("jdbc:mysql://39.107.57.75:3307/zhuzhenjie?characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&useSSL=false&serverTimezone=Asia/Shanghai&nullCatalogMeansCurrent=true")

    val template = """
       |username: $username
       |password: $password
       |jdbcUrl:  $jdbcUrl
    """.trimIndent()

    println(template)
}
package io.intellij.examples.kotlin.test.springboot

import org.jasypt.util.text.BasicTextEncryptor
import org.junit.jupiter.api.Test

/**
 * EncryptTest
 *
 * @author tech@intellij.io
 */
class EncryptTest {

    private val password = "kotlin"

    @Test
    fun `test encrypt string`() {
        val encryptor = BasicTextEncryptor()
        encryptor.setPassword(password)
        println("ENC(${encryptor.encrypt("root")})")
        println("ENC(${encryptor.encrypt("root")})")
        println("ENC(${encryptor.encrypt("root")})")
    }

}
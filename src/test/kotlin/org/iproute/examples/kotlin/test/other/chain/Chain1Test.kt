package org.iproute.examples.kotlin.test.other.chain.Chain1Test

import org.junit.jupiter.api.Test
import reactor.core.publisher.toMono
import java.util.UUID


class Chain1Test {

    @Test
    fun test() {
        val input = UUID.randomUUID().toString()
        println(
            c(
                b(
                    a(input)
                )
            )
        )
    }

    private fun a(str: String): String {
        return "$str-a"
    }


    private fun b(str: String): String {
        return "$str-b"
    }


    private fun c(str: String): String {
        return "$str-c"
    }

}




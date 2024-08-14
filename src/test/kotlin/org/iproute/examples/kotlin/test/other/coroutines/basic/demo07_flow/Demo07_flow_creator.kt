package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow_creator.kt

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * Demo07_flow_creator
 *
 * @author tech@intellij.io
 * @since 2023/7/4
 */
fun main() = runBlocking {
    (1..3).asFlow()
        .collect {
            println(it)
        }
}

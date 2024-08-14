package org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow_transform.kt

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import org.iproute.examples.kotlin.test.other.coroutines.basic.demo07_flow.Demo07_flow_map.kt.performRequest

/**
 * Demo07_flow_map
 *
 * @author tech@intellij.io
 * @since 2023/7/4
 */
fun main() = runBlocking {
    (1..3).asFlow()
        .transform { request ->
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect {
            println(it)
        }
}

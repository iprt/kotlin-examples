package org.iproute.examples.kotlin.mqtt

import org.iproute.examples.kotlin.config.getLogger
import org.slf4j.Logger
import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import org.springframework.core.type.AnnotationMetadata
import java.net.Socket

/**
 * MqttConnectCondition
 *
 * @author zhuzhenjie
 */
class MqttConnectCondition {

    companion object {
        val log: Logger = getLogger(this::class.java)

        /**
         * Parse the host and port from the broker URL.
         *
         * @param broker The broker URL in the format "tcp*/
        fun parseHostPortFromBroker(broker: String): HostPort =
            try {
                val hp = broker.substringAfter("//")
                val host = hp.substringBefore(":")
                val port = hp.substringAfter(":").toInt()
                HostPort(host, port)
            } catch (e: Exception) {
                HostPort("127.0.0.1", 1883)
            }

        fun testTCPConnection(host: String, port: Int): Boolean {
            try {
                Socket(host, port).use { socket ->
                    if (socket.isConnected) {
                        log.info("Connected to $host on port $port")
                        return true
                    }
                }
            } catch (e: Exception) {
                log.info("Failed to connect to $host on port $port: ${e.message}")
                return false
            }
            return false
        }
    }

    data class HostPort(val host: String, val port: Int)

    class Publish : Condition {
        override fun matches(context: ConditionContext, annotatedTypeMetadata: AnnotatedTypeMetadata): Boolean {
            log.info("publish condition in ${(annotatedTypeMetadata as? AnnotationMetadata)?.className}")

            val publishBroker =
                context.environment.getProperty("spring.mqtt.publish.broker") ?: return false

            if (publishBroker.isEmpty()
                || !publishBroker.startsWith("tcp://")
            ) {
                log.error("publish broker is $publishBroker")
                return false
            }

            val hostPort = parseHostPortFromBroker(publishBroker)

            return testTCPConnection(
                hostPort.host, hostPort.port
            )
        }
    }

    class Subscribe : Condition {
        override fun matches(context: ConditionContext, annotatedTypeMetadata: AnnotatedTypeMetadata): Boolean {
            log.info("subscribe condition in ${(annotatedTypeMetadata as? AnnotationMetadata)?.className}")

            val subscribeBroker =
                context.environment.getProperty("spring.mqtt.subscribe.broker") ?: return false

            if (subscribeBroker.isEmpty()
                || !subscribeBroker.startsWith("tcp://")
            ) {
                log.error("subscribe broker is $subscribeBroker")
                return false
            }

            val hostPort = parseHostPortFromBroker(subscribeBroker)

            return testTCPConnection(
                hostPort.host, hostPort.port
            )
        }

    }

}
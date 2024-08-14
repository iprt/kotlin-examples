package io.intellij.examples.kotlin.mqtt

import org.springframework.context.annotation.Conditional
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

/**
 * Mqtt publisher
 *
 * @constructor Create empty Mqtt publisher
 */
@MessagingGateway(defaultRequestChannel = CHANNEL_NAME_OUT)
@Conditional(MqttConnectCondition.Publish::class)
@Component
interface MqttGateway {

    /**
     * Publish to broker
     *
     * @param payload
     */
    fun sendToMqtt(payload: String)

    /**
     * Publish to broker
     *
     * @param topic
     * @param payload
     */
    fun sendToMqtt(
        @Header(MqttHeaders.TOPIC) topic: String,
        payload: String
    )

    /**
     * Publish to broker
     *
     * @param topic
     * @param qos
     * @param payload
     */
    fun sendToMqtt(
        @Header(MqttHeaders.TOPIC) topic: String,
        @Header(MqttHeaders.QOS) qos: Int,
        payload: String
    )

}
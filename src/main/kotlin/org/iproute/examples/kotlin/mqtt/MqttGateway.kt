package org.iproute.examples.kotlin.mqtt

import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.messaging.handler.annotation.Header

/**
 * Mqtt publisher
 *
 * @constructor Create empty Mqtt publisher
 */
@MessagingGateway(defaultRequestChannel = CHANNEL_NAME_OUT)
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
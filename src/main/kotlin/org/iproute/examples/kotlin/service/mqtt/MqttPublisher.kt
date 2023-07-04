package org.iproute.examples.kotlin.service.mqtt

import org.iproute.examples.kotlin.config.mqtt.MqttPublisherConfig
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.integration.mqtt.support.MqttHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

/**
 * Mqtt publisher
 *
 * @constructor Create empty Mqtt publisher
 */
@Component
@MessagingGateway(defaultRequestChannel = MqttPublisherConfig.CHANNEL_NAME_OUT)
interface MqttPublisher {

    /**
     * Publish to broker
     *
     * @param payload
     */
    fun publishToBroker(payload: String)

    /**
     * Publish to broker
     *
     * @param topic
     * @param payload
     */
    fun publishToBroker(
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
    fun publishToBroker(
        @Header(MqttHeaders.TOPIC) topic: String,
        @Header(MqttHeaders.QOS) qos: Int,
        payload: String
    )

}
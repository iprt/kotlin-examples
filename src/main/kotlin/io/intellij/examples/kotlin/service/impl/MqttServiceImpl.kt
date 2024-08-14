package io.intellij.examples.kotlin.service.impl

import io.intellij.examples.kotlin.mqtt.MqttConnectCondition
import io.intellij.examples.kotlin.mqtt.MqttGateway
import io.intellij.examples.kotlin.service.MqttService
import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Service

/**
 * MqttTestServiceImpl
 *
 * @author tech@intellij.io
 * @since 2021/12/28
 */
@Conditional(MqttConnectCondition.Publish::class)
@Service
class MqttServiceImpl(
    private val mqttGateway: MqttGateway
) : MqttService {

    override fun publishToDefaultTopic(msg: String): Boolean {
        mqttGateway.sendToMqtt(msg)
        return true
    }

    override fun publish(topic: String, msg: String): Boolean {
        mqttGateway.sendToMqtt(topic, msg)
        return true;
    }

    override fun publish(topic: String, qos: Int, msg: String): Boolean {
        mqttGateway.sendToMqtt(topic, qos, msg)
        return true;
    }
}
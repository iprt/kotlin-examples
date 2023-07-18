package org.iproute.examples.kotlin.service.impl

import org.iproute.examples.kotlin.mqtt.MqttGateway
import org.iproute.examples.kotlin.service.MqttTestService
import org.springframework.stereotype.Service

/**
 * MqttTestServiceImpl
 *
 * @author zhuzhenjie
 * @since 2021/12/28
 */
@Service
class MqttTestServiceImpl(
    private val mqttGateway: MqttGateway
) : MqttTestService {

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
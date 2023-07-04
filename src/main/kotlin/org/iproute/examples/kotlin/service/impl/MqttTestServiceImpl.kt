package org.iproute.examples.kotlin.service.impl

import org.iproute.examples.kotlin.service.MqttTestService
import org.iproute.examples.kotlin.service.mqtt.MqttPublisher
import org.springframework.stereotype.Service

/**
 * MqttTestServiceImpl
 *
 * @author winterfell
 * @since 2021/12/28
 */
@Service
class MqttTestServiceImpl(
    private val mqttPublisher: MqttPublisher
) : MqttTestService {

//    @Autowired
//    private lateinit var mqttPublisher: MqttPublisher

    override fun publishToDefaultTopic(msg: String): Boolean {
        mqttPublisher.publishToBroker(msg)
        return true
    }

    override fun publish(topic: String, msg: String): Boolean {
        mqttPublisher.publishToBroker(topic, msg)
        return true;
    }

    override fun publish(topic: String, qos: Int, msg: String): Boolean {
        mqttPublisher.publishToBroker(topic, qos, msg)
        return true;
    }
}
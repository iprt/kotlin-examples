package io.intellij.examples.kotlin.controller

import io.intellij.examples.kotlin.entities.dto.mqtt.A
import io.intellij.examples.kotlin.entities.dto.mqtt.B
import io.intellij.examples.kotlin.entities.dto.mqtt.C
import io.intellij.examples.kotlin.entities.dto.mqtt.MqttPubReq
import io.intellij.examples.kotlin.mqtt.MqttConnectCondition
import io.intellij.examples.kotlin.service.MqttService
import org.springframework.context.annotation.Conditional
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Mqtt test controller
 *
 * @property mqttService
 * @constructor Create empty Mqtt test controller
 */
@RestController
@RequestMapping("/mqtt")
@Conditional(MqttConnectCondition.Publish::class)
class MqttController(
    private val mqttService: MqttService
) {

    /**
     * Publish to default topic
     *
     * @param req
     * @return
     */
    @PostMapping("/publishToDefaultTopic")
    fun publishToDefaultTopic(@RequestBody @Validated(A::class) req: MqttPubReq): Boolean {
        val msg = req.msg!!
        return mqttService.publishToDefaultTopic(msg)
    }


    /**
     * Publish without qos
     *
     * @param req
     * @return
     */
    @PostMapping("/publishWithoutQos")
    fun publishWithoutQos(@RequestBody @Validated(B::class) req: MqttPubReq): Boolean {
        val topic = req.topic!!
        val msg = req.msg!!
        return mqttService.publish(topic, msg)
    }


    /**
     * Publish with qos
     *
     * @param req
     * @return
     */
    @PostMapping("/publishWithQos")
    fun publishWithQos(@RequestBody @Validated(C::class) req: MqttPubReq): Boolean {
        val topic = req.topic!!
        val qos = req.qos!!
        val msg = req.msg!!
        return mqttService.publish(topic, qos, msg)
    }

}
package io.intellij.examples.kotlin.controller

import io.intellij.examples.kotlin.entities.dto.mqtt.A
import io.intellij.examples.kotlin.entities.dto.mqtt.B
import io.intellij.examples.kotlin.entities.dto.mqtt.C
import io.intellij.examples.kotlin.entities.dto.mqtt.MqttMsgDto
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
     * @param dto
     * @return
     */
    @PostMapping("/publishToDefaultTopic")
    fun publishToDefaultTopic(@RequestBody @Validated(A::class) dto: MqttMsgDto): Boolean {
        val msg = dto.msg!!
        return mqttService.publishToDefaultTopic(msg)
    }


    /**
     * Publish without qos
     *
     * @param dto
     * @return
     */
    @PostMapping("/publishWithoutQos")
    fun publishWithoutQos(@RequestBody @Validated(B::class) dto: MqttMsgDto): Boolean {
        val topic = dto.topic!!
        val msg = dto.msg!!
        return mqttService.publish(topic, msg)
    }


    /**
     * Publish with qos
     *
     * @param dto
     * @return
     */
    @PostMapping("/publishWithQos")
    fun publishWithQos(@RequestBody @Validated(C::class) dto: MqttMsgDto): Boolean {
        val topic = dto.topic!!
        val qos = dto.qos!!
        val msg = dto.msg!!
        return mqttService.publish(topic, qos, msg)
    }

}
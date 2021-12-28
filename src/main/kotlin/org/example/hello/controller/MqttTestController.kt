package org.example.hello.controller

import org.example.hello.entities.dto.A
import org.example.hello.entities.dto.B
import org.example.hello.entities.dto.C
import org.example.hello.entities.dto.MqttMsgDto
import org.example.hello.service.MqttTestService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Mqtt test controller
 *
 * @property mqttTestService
 * @constructor Create empty Mqtt test controller
 */
@RestController
@RequestMapping("/mqtt")
class MqttTestController(
    private val mqttTestService: MqttTestService
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
        return mqttTestService.publishToDefaultTopic(msg)
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
        return mqttTestService.publish(topic, msg)
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
        return mqttTestService.publish(topic, qos, msg)
    }

}
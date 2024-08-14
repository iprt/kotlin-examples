package io.intellij.examples.kotlin.entities.dto.mqtt

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


/**
 * MqttMsgDto
 *
 * @author tech@intellij.io
 * @since 2021/12/28
 */
data class MqttMsgDto(
    @field:NotBlank(message = "msg不能为空", groups = [A::class, B::class, C::class])
    var msg: String? = null,

    @field:NotBlank(message = "topic不能为空", groups = [B::class, C::class])
    var topic: String? = null,

    @field:NotNull(message = "qos不能为空", groups = [C::class])
    var qos: Int? = null
)

interface A
interface B
interface C

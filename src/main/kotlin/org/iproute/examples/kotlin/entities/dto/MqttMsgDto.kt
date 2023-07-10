package org.iproute.examples.kotlin.entities.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * MqttMsgDto
 *
 * @author zhuzhenjie
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



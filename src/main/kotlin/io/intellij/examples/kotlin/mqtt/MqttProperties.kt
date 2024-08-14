package io.intellij.examples.kotlin.mqtt

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * 常量
 */
const val CHANNEL_NAME_OUT = "mqttOutboundChannel"
const val WILL_TOPIC = "/topic/will"
val WILL_DATA = "offline".toByteArray(Charsets.UTF_8);

@ConfigurationProperties(prefix = "spring.mqtt.subscribe")
data class MqttSubscribeProperties(
    var broker: String = "",
    var clientId: String = "",
    var username: String = "",
    var password: String = "",
    var topics: List<String> = emptyList()
)

@ConfigurationProperties(prefix = "spring.mqtt.publish")
data class MqttPublishProperties(
    var broker: String = "",
    var clientId: String = "",
    var username: String = "",
    var password: String = "",
    var defaultTopic: String = "",
    var topics: List<String> = emptyList()
)
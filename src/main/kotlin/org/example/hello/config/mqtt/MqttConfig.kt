package org.example.hello.config.mqtt

import org.apache.commons.lang3.StringUtils
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * MqttConfig
 *
 * @author winterfell
 * @since 2021/12/27
 */
@ConfigurationProperties(prefix = "spring.mqtt")
@Component
data class MqttConfig(
    var broker: String = StringUtils.EMPTY,
    var clientId: String = StringUtils.EMPTY,
    var username: String = StringUtils.EMPTY,
    var password: String = StringUtils.EMPTY,
    var defaultTopic: String = StringUtils.EMPTY,
    var topics: List<String> = emptyList()
)
package org.example.hello.config.mqtt

import org.apache.commons.lang3.StringUtils
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.IntegrationComponentScan
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

/**
 * MqttPublishConfig
 *
 * @author winterfell
 * @since 2021/12/27
 */
@Configuration
@IntegrationComponentScan
class MqttPublisherConfig(
    private val mqttConfig: MqttConfig
) {

    companion object {
        const val CHANNEL_NAME_OUT = "mqttOutboundChannel";
        const val WILL_TOPIC = "/topic/will"
        val WILL_DATA = "offline".toByteArray(Charsets.UTF_8);
    }

    /**
     * Publisher mqtt connection option
     *
     * @return
     */
    @Bean
    fun publisherMqttConnectionOption(): MqttConnectOptions {
        return MqttConnectOptions().apply {
            if (StringUtils.isNotBlank(mqttConfig.username)) {
                this.userName = mqttConfig.username
            }
            if (StringUtils.isNotBlank(mqttConfig.password)) {
                this.password = mqttConfig.password.toCharArray()
            }

            this.serverURIs = StringUtils.split(mqttConfig.broker, ",")
            this.isCleanSession = true
            this.connectionTimeout = 10
            this.keepAliveInterval = 20
            this.setWill(WILL_TOPIC, WILL_DATA, 2, false)
        }
    }

    /**
     * Publisher mqtt client factory
     *
     * @return
     */
    @Bean
    fun publisherMqttClientFactory(): MqttPahoClientFactory {
        return DefaultMqttPahoClientFactory().apply {
            this.connectionOptions = publisherMqttConnectionOption()
        }
    }

    /**
     * mqtt信息通道 （生产者）
     *
     * @return
     */
    @Bean(CHANNEL_NAME_OUT)
    fun mqttOutboundChannel(): MessageChannel {
        return DirectChannel();
    }

    /**
     * mqtt消息处理器 （生产者）
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    fun mqttOutbound(): MessageHandler {
        val handler = MqttPahoMessageHandler(mqttConfig.clientId, publisherMqttClientFactory())
        handler.setAsync(false)
        handler.setDefaultTopic(mqttConfig.defaultTopic)
        return handler
    }

}
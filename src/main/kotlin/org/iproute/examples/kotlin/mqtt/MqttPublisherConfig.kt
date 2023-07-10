package org.iproute.examples.kotlin.mqtt

import org.apache.commons.lang3.StringUtils
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
 * @author zhuzhenjie
 * @since 2021/12/27
 */
@Configuration
class MqttPublisherConfig(
    private val mqttPublishProperties: MqttPublishProperties
) {

    /**
     * Publisher mqtt connection option
     *
     * @return
     */
    @Bean
    fun publisherMqttConnectionOption(): MqttConnectOptions {
        return MqttConnectOptions().apply {
            if (StringUtils.isNotBlank(mqttPublishProperties.username)) {
                this.userName = mqttPublishProperties.username
            }
            if (StringUtils.isNotBlank(mqttPublishProperties.password)) {
                this.password = mqttPublishProperties.password.toCharArray()
            }

            this.serverURIs = StringUtils.split(mqttPublishProperties.broker, ",")
            this.isCleanSession = true
            this.connectionTimeout = 10
            this.keepAliveInterval = 20
            this.setWill(WILL_TOPIC, WILL_DATA, 0, false)
        }
    }

    /**
     * Publisher mqtt client factory
     *
     * @return
     */
    @Bean
    fun publisherMqttClientFactory(publisherMqttConnectionOption: MqttConnectOptions): MqttPahoClientFactory {
        return DefaultMqttPahoClientFactory().apply {
            this.connectionOptions = publisherMqttConnectionOption
        }
    }

    /**
     * mqtt信息通道 （生产者）
     *
     * @return
     */
    @Bean
    fun mqttOutboundChannel(): MessageChannel {
        return DirectChannel()
    }

    /**
     * mqtt消息处理器 （生产者）
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    fun outbound(@Qualifier("publisherMqttClientFactory") publisherMqttClientFactory: MqttPahoClientFactory): MessageHandler {
        val messageHandler = MqttPahoMessageHandler(mqttPublishProperties.clientId, publisherMqttClientFactory)
        messageHandler.setDefaultTopic(mqttPublishProperties.defaultTopic)
        messageHandler.setAsync(true)
        return messageHandler
    }

}
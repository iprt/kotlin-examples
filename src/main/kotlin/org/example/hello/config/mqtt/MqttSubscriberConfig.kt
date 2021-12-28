package org.example.hello.config.mqtt

import org.apache.commons.lang3.StringUtils
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.example.hello.config.getLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.IntegrationComponentScan
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageProducer
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory
import org.springframework.integration.mqtt.core.MqttPahoClientFactory
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler

/**
 * Mqtt subscriber config
 *
 * @property mqttConfig
 * @property cacheService
 * @constructor Create empty Mqtt subscriber config
 */
@Configuration
@IntegrationComponentScan
class MqttSubscriberConfig(
    private val mqttConfig: MqttConfig,
) {
    private val log = getLogger(this::class.java)

    /**
     * Subscriber mqtt connect options
     *
     * @return
     */
    @Bean
    fun subscriberMqttConnectOptions(): MqttConnectOptions {
        val username = mqttConfig.username
        val password = mqttConfig.password
        val options = MqttConnectOptions()
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            options.userName = username
            options.password = password.toCharArray()
        }
        options.serverURIs = arrayOf(mqttConfig.broker)
        options.keepAliveInterval = 60
        return options
    }

    /**
     * Mqtt client factory
     *
     * @return
     */
    @Bean
    fun mqttClientFactory(): MqttPahoClientFactory {
        val factory = DefaultMqttPahoClientFactory()
        factory.connectionOptions = subscriberMqttConnectOptions()
        return factory
    }

    /**
     * Mqtt input channel
     *
     * @return
     */
    @Bean("mqttInputChannel")
    fun mqttInputChannel(): MessageChannel {
        return DirectChannel()
    }

    /**
     * Inbound
     *
     * @return
     */
    @Bean
    fun inbound(): MessageProducer {
        log.info("consume topic : ${mqttConfig.defaultTopic}")
        val topicArr = mqttConfig.topics.toTypedArray()
        topicArr.forEach { log.info("consume topic : $it") }
        val adapter =
            MqttPahoMessageDrivenChannelAdapter(
                mqttConfig.clientId,
                mqttClientFactory(),
                mqttConfig.defaultTopic,
                *topicArr
            )
        adapter.setConverter(DefaultPahoMessageConverter())
        adapter.setQos(1)
        adapter.outputChannel = mqttInputChannel()
        return adapter
    }

    /**
     * Handler
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun handler(): MessageHandler {
        return MessageHandler {
            val topic = it.headers["mqtt_receivedTopic"].toString()
            val payload = it.payload.toString()

            log.info("--------------------START-------------------")
            log.info("接收到订阅消息:")
            log.info("topic: $topic")
            log.info("message: $payload")
            log.info("---------------------END--------------------\n")
        }
    }

}
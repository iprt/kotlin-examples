package org.iproute.examples.kotlin.mqtt

import org.apache.commons.lang3.StringUtils
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.iproute.examples.kotlin.config.getLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
 * @property mqttSubscribeProperties
 * @constructor Create empty Mqtt subscriber config
 */
@Configuration
class MqttSubscriberConfig(
    private val mqttSubscribeProperties: MqttSubscribeProperties
) {
    private val log = getLogger(this::class.java)

    /**
     * Subscriber mqtt connect options
     *
     * @return
     */
    @Bean
    fun subscriberMqttConnectOptions(): MqttConnectOptions {
        val username = mqttSubscribeProperties.username
        val password = mqttSubscribeProperties.password
        val options = MqttConnectOptions()
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            options.userName = username
            options.password = password.toCharArray()
        }
        options.serverURIs = arrayOf(mqttSubscribeProperties.broker)
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
        val topicArr = mqttSubscribeProperties.topics.toTypedArray()
        topicArr.forEach { log.info("消费 topic : $it") }
        val adapter = MqttPahoMessageDrivenChannelAdapter(
            mqttSubscribeProperties.clientId,
            mqttClientFactory(),
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
    @Bean("inboundHandler")
    @ServiceActivator(inputChannel = "mqttInputChannel")
    fun handler(): MessageHandler {
        return MessageHandler {
            val topic = it.headers["mqtt_receivedTopic"].toString()
            val payload = it.payload.toString()

            log.info("--------------------START-------------------")
            log.info("接收到订阅消息:")
            log.info("topic: $topic")
            log.info("message: \n$payload")
            log.info("---------------------END--------------------\n")
        }
    }

}
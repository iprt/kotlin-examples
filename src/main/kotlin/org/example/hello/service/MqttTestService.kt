package org.example.hello.service

/**
 * Mqtt test service
 *
 * @constructor Create empty Mqtt test service
 */
interface MqttTestService {

    /**
     * Publish to default topic
     *
     * @param msg
     * @return
     */
    fun publishToDefaultTopic(msg: String): Boolean

    /**
     * Publish
     *
     * @param topic
     * @param msg
     * @return
     */
    fun publish(topic: String, msg: String): Boolean

    /**
     * Publish
     *
     * @param topic
     * @param qos
     * @param msg
     * @return
     */
    fun publish(topic: String, qos: Int, msg: String): Boolean

}
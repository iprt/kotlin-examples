package org.example.hello.config

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.SerializationException
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.nio.charset.StandardCharsets

/**
 * RedisConfig
 *
 * @author winterfell
 * @since 2021/1/27
 */
@EnableCaching
@Configuration
@ConditionalOnClass(RedisOperations::class)
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig : CachingConfigurerSupport() {

    @Bean(name = ["redisTemplate"])
    @ConditionalOnMissingBean(name = ["redisTemplate"])
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<Any, Any> =
        RedisTemplate<Any, Any>().apply {
            val redisSerializer = FastJsonRedisSerializer(Any::class.java)

            valueSerializer = redisSerializer
            hashValueSerializer = redisSerializer

            keySerializer = StringRedisSerializer()
            hashKeySerializer = StringRedisSerializer()
            setConnectionFactory(redisConnectionFactory)
        }

    //缓存管理器
    @Bean
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager =
        RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory).build()

}

class FastJsonRedisSerializer<T>(private val clazz: Class<T>) : RedisSerializer<T> {

    @Throws(SerializationException::class)
    override fun serialize(t: T?) = if (null == t) {
        ByteArray(0)
    } else {
        JSON.toJSONString(t, SerializerFeature.WriteClassName).toByteArray(StandardCharsets.UTF_8)
    }

    @Throws(SerializationException::class)
    override fun deserialize(bytes: ByteArray?): T? = if (bytes == null || bytes.isEmpty()) {
        null
    } else {
        val str = String(bytes, StandardCharsets.UTF_8)
        JSON.parseObject(str, clazz) as T
    }

}

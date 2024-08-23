package io.intellij.examples.kotlin.config.redis

import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONReader
import com.alibaba.fastjson2.JSONWriter
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.SerializationException
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.nio.charset.StandardCharsets

/**
 * RedisConfig
 *
 * @author tech@intellij.io
 */
// @EnableCaching
// @Configuration
// @ConditionalOnClass(RedisOperations::class)
// @EnableConfigurationProperties(RedisProperties::class)
@Deprecated(
    "This config is Deprecated,use RedisConfig instead",
    ReplaceWith("RedisConfig()", "io.intellij.examples.kotlin.config.redis.RedisConfig")
)
class RedisConfigFastJson : CachingConfigurer {

    /**
     * Redis template
     *
     * @param redisConnectionFactory
     * @return
     */
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

    /**
     * Cache manager
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager {
        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .build()
    }

}

/**
 * Fast json redis serializer
 *
 * @param T
 * @property clazz
 * @constructor Create empty Fast json redis serializer
 */
class FastJsonRedisSerializer<T>(
    private val clazz: Class<T>
) : RedisSerializer<T> {

    companion object {
        val FEATURES = arrayOf(
            JSONWriter.Feature.WriteClassName,
            JSONWriter.Feature.FieldBased,
            JSONWriter.Feature.ReferenceDetection,
            JSONWriter.Feature.NotWriteDefaultValue,
            JSONWriter.Feature.WriteNameAsSymbol,
            JSONWriter.Feature.WriteEnumsUsingName
        )

        val AUTO_TYPE_FILTER = JSONReader.autoTypeFilter(
            "com.",
            "net.",
            "org.",
            "io.",
            "java.", "javax.", "jakarta."
        )!!

    }

    @Throws(SerializationException::class)
    override fun serialize(t: T?) = if (null == t) {
        ByteArray(0)
    } else {
        // JSON.toJSONString(t, JSONWriter.Feature.WriteClassName).toByteArray(StandardCharsets.UTF_8)
        // 使用 * 运算符将数组展开
        JSON.toJSONString(t, *FEATURES).toByteArray(StandardCharsets.UTF_8)
    }

    @Throws(SerializationException::class)
    override fun deserialize(bytes: ByteArray?): T? = if (null == bytes || bytes.isEmpty()) {
        null
    } else {
        val str = String(bytes, StandardCharsets.UTF_8)
        JSON.parseObject(
            str, clazz, AUTO_TYPE_FILTER,
            JSONReader.Feature.UseDefaultConstructorAsPossible,
            JSONReader.Feature.UseNativeObject,
            JSONReader.Feature.FieldBased
        ) as T
    }

}
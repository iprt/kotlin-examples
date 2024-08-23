package io.intellij.examples.kotlin.config.redis

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

/**
 * RedisConfig
 *
 * @author tech@intellij.io
 */
@EnableCaching
@Configuration
class RedisConfig : CachingConfigurer {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> =
        RedisTemplate<String, Any>().apply {
            connectionFactory = redisConnectionFactory

            keySerializer = StringRedisSerializer()
            hashValueSerializer = StringRedisSerializer()

            /*
            val objectMapper = ObjectMapper().apply {
                // this(new ObjectMapper(), reader, writer, typeHintPropertyName);
                registerModule(SimpleModule().apply {
                    addSerializer(NullValueSerializer())
                })

                // this.mapper.setDefaultTyping(createDefaultTypeResolverBuilder(getObjectMapper(), typeHintPropertyName));
                setDefaultTyping(
                    TypeResolverBuilder.forEverything(this)
                        .init(JsonTypeInfo.Id.CLASS, null)
                        .inclusion(As.PROPERTY)
                        .typeProperty("null")
                )
            }
            */

            valueSerializer = GenericJackson2JsonRedisSerializer()

        }

    @Bean
    fun cacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager =
        RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(
            RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(600))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        Jackson2JsonRedisSerializer(ObjectMapper().apply {
                            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                            activateDefaultTyping(
                                LaissezFaireSubTypeValidator.instance,
                                DefaultTyping.NON_FINAL
                            )
                        }, Any::class.java)
                    )
                )
                .disableCachingNullValues()
        ).build()

}

/*

class NullValueSerializer(
    private var classIdentifier: String? = "@class" // 调用者可以传递 null
) : StdSerializer<NullValue>(NullValue::class.java) {

    companion object {
        private const val serialVersionUID = 1999052150548658808L
    }

    init {
        // 如果传递的 classIdentifier 为空，则默认设置为 "@class"
        if (!StringUtils.hasText(classIdentifier)) {
            this.classIdentifier = "@class"
        }
    }

    @Throws(IOException::class)
    override fun serialize(value: NullValue, jsonGenerator: JsonGenerator, provider: SerializerProvider) {
        jsonGenerator.writeStartObject()
        jsonGenerator.writeStringField(classIdentifier ?: "@class", NullValue::class.java.name)
        jsonGenerator.writeEndObject()
    }

    @Throws(IOException::class)
    override fun serializeWithType(
        value: NullValue, jsonGenerator: JsonGenerator, serializers: SerializerProvider, typeSerializer: TypeSerializer
    ) {
        serialize(value, jsonGenerator, serializers)
    }
}


class TypeResolverBuilder(
    typing: DefaultTyping, polymorphicTypeValidator: PolymorphicTypeValidator
) : ObjectMapper.DefaultTypeResolverBuilder(typing, polymorphicTypeValidator) {

    companion object {
        fun forEverything(mapper: ObjectMapper): TypeResolverBuilder {
            return TypeResolverBuilder(DefaultTyping.EVERYTHING, mapper.polymorphicTypeValidator)
        }
    }

    override fun withDefaultImpl(defaultImpl: Class<*>?): ObjectMapper.DefaultTypeResolverBuilder {
        return this
    }

    override fun useForType(javaType: JavaType): Boolean {

        if (javaType.isJavaLangObject) {
            return true
        }

        val resolvedType = resolveArrayOrWrapper(javaType)

        if (resolvedType.isEnumType || ClassUtils.isPrimitiveOrWrapper(resolvedType.rawClass)) {
            return false
        }

        if (resolvedType.isFinal && !KotlinDetector.isKotlinType(resolvedType.rawClass)
            && resolvedType.rawClass.packageName.startsWith("java")
        ) {
            return false
        }

        return !TreeNode::class.java.isAssignableFrom(resolvedType.rawClass)
    }

    private fun resolveArrayOrWrapper(type: JavaType): JavaType {
        var resultType = type

        while (resultType.isArrayType) {
            resultType = resultType.contentType
            if (resultType.isReferenceType) {
                resultType = resolveArrayOrWrapper(resultType)
            }
        }

        while (resultType.isReferenceType) {
            resultType = resultType.referencedType
            if (resultType.isArrayType) {
                resultType = resolveArrayOrWrapper(resultType)
            }
        }

        return resultType
    }
}

*/

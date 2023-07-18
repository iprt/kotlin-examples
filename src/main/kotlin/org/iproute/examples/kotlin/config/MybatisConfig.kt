package org.iproute.examples.kotlin.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Mybatis config
 *
 * @constructor Create empty Mybatis config
 */
@Configuration
class MybatisConfig {

    /**
     * Mybatis plus interceptor
     *
     * @return
     */
    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor = MybatisPlusInterceptor().apply {
        this.addInnerInterceptor(PaginationInnerInterceptor(DbType.MYSQL))
    }
}
package org.example.hello.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author zhuzhenjie
 * @since 2021/1/6
 */
@Configuration
class MybatisConfig {

    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        return MybatisPlusInterceptor().apply {
            this.addInnerInterceptor(PaginationInnerInterceptor(DbType.MYSQL))
        }
    }
}
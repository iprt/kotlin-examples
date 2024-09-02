package io.intellij.examples.kotlin.entities.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.*

/**
 * Benchmarks
 *
 * @author tech@intellij.io
 * @since 2021/7/19
 */
@TableName(value = "id_type_autoincrement")
data class AutoIncrementTable(
    @field:TableId(value = "id", type = IdType.INPUT)
    var id: Long? = null,

    @field:TableField(value = "data")
    val data: String,

    @field:TableField(value = "created_at")
    var createdAt: Date? = Date()
)

@TableName(value = "id_type_snowflake")
data class SnowFlakeTable(
    @field:TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: Long? = null,

    @field:TableField(value = "data")
    val data: String,

    @field:TableField(value = "created_at")
    var createdAt: Date? = Date()
)
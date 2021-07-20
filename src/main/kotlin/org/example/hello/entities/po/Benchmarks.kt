package org.example.hello.entities.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * Benchmarks
 *
 * @author Jzz
 * @since 2021/7/19
 */
@TableName(value = "benchmarks_auto")
data class BenchmarksAuto(
    @field:TableId(value = "id", type = IdType.INPUT)
    var id: Long? = null,

    @field:TableField(value = "data")
    val data: String
)

@TableName(value = "benchmarks_flow")
data class BenchmarksFlow(
    @field:TableId(value = "id", type = IdType.ASSIGN_ID)
    var id: Long? = null,

    @field:TableField(value = "data")
    var data: String
)
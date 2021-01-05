package org.example.hello.entities.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import javax.validation.constraints.NotBlank

/**
 * @author winterfell
 * @since 2021/1/6
 */
@TableName("student")
data class Student(
        @TableId(value = "id", type = IdType.ASSIGN_ID)
        @get:JsonSerialize(using = ToStringSerializer::class)
        var id: Long? = null,

        @TableField("name")
        @field:NotBlank(message = "name 不能为空", groups = [Get::class, Add::class])
        var name: String? = null,
) {
    /**
     * 验证分组
     */
    interface Add

    interface Get
}

//@TableName("student")
//class Student {
//
//    @TableId("id")
//    var id: Long? = null
//
//    @TableField("name")
//    var name: String? = null
//
//}
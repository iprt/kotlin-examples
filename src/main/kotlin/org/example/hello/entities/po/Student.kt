package org.example.hello.entities.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import javax.validation.constraints.NotBlank

/**
 * StudentWithParent
 *
 * @author winterfell
 * @since 2021/1/6
 */
@TableName(value = "student", autoResultMap = true)
data class StudentWithParent(
    @field:TableId(value = "id", type = IdType.ASSIGN_ID)
    @get:JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @field:TableField(value = "name")
    @field:NotBlank(message = "name 不能为空", groups = [Get::class, Add::class])
    var name: String? = null,

    @field:TableField(value = "info", typeHandler = FastjsonTypeHandler::class)
    var info: Parent? = null
) {
    /**
     * 验证分组
     */
    interface Add

    interface Get
}

data class Parent(
    var mother: String? = null,
    var father: String? = null
)

/*
@TableName("student")
class Student {

    @TableId("id")
    var id: Long? = null

    @TableField("name")
    var name: String? = null

}
*/


/**
 * StudentWithAddress
 *
 * @author winterfell
 * @since 2021/1/25
 */
@TableName(value = "student", autoResultMap = true)
data class StudentWithAddress(
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @get:JsonSerialize(using = ToStringSerializer::class)
    var id: Long? = null,

    @field:TableField(value = "name")
    @field:NotBlank(message = "name 不能为空", groups = [Get::class, Add::class])
    var name: String? = null,

    @field:TableField(value = "info", typeHandler = FastjsonTypeHandler::class)
    var info: Address? = null
) {
    /**
     * 验证分组
     */
    interface Add

    interface Get
}

data class Address(
    var lgtd: Double? = 0.0,
    var lttd: Double? = 0.0
)

package org.iproute.examples.kotlin.entities.po

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import javax.validation.constraints.NotBlank

/**
 * Student with parent
 *
 * @property id
 * @property name
 * @property info
 * @constructor Create empty Student with parent
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
     * Add
     *
     * @constructor Create empty Add
     */
    interface Add

    /**
     * Get
     *
     * @constructor Create empty Get
     */
    interface Get
}

/**
 * Parent
 *
 * @property mother
 * @property father
 * @constructor Create empty Parent
 */
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
 * Student with address
 *
 * @property id
 * @property name
 * @property info
 * @constructor Create empty Student with address
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
     * Add
     *
     * @constructor Create empty Add
     */
    interface Add

    /**
     * Get
     *
     * @constructor Create empty Get
     */
    interface Get
}

/**
 * Address
 *
 * @property lgtd
 * @property lttd
 * @constructor Create empty Address
 */
data class Address(
    var lgtd: Double? = 0.0,
    var lttd: Double? = 0.0
)

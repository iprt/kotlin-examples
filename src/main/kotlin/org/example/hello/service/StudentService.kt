package org.example.hello.service

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.example.hello.entities.po.StudentWithAddress
import org.example.hello.entities.po.StudentWithParent

/**
 * StudentService
 *
 * @author winterfell
 * @since 2021/3/26
 */
interface StudentService {

    /**
     * 列出所有学生
     */
    fun listAllStudentWithParent(): MutableList<StudentWithParent>?


    /**
     * 根据条件列举学生
     */
    fun listStuWithParentByCondition(): MutableList<StudentWithParent>?


    /**
     * 分页查询学生
     */
    fun pageListStuWithParent(page: Page<StudentWithParent>): IPage<StudentWithParent>?


    /**
     * 根据ID查询学生
     *
     * @param id id
     * @return 学生信息
     */
    fun getStuWithParentById(id: Long): StudentWithParent?

    /*  使用不同的 typeHandler 查询 */

    /**
     * 列出所有学生
     */
    fun listAllStudentWithAddress(): MutableList<StudentWithAddress>?

    /**
     * 分页展示所有学生
     */
    fun pageListStuWithAddress(page: Page<*>): IPage<StudentWithAddress>?

    /**
     * 根据ID查询学生
     */
    fun getStuWithAddressById(id: Long): StudentWithAddress
}
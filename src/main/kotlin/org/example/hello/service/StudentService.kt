package org.example.hello.service

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.example.hello.entities.po.StudentWithAddress
import org.example.hello.entities.po.StudentWithParent

/**
 * Student service
 *
 * @constructor Create empty Student service
 */
interface StudentService {

    /**
     * List all student with parent
     *
     * @return
     */
    fun listAllStudentWithParent(): MutableList<StudentWithParent>?


    /**
     * List stu with parent by condition
     *
     * @return
     */
    fun listStuWithParentByCondition(): MutableList<StudentWithParent>?


    /**
     * Page list stu with parent
     *
     * @param page
     * @return
     */
    fun pageListStuWithParent(page: Page<StudentWithParent>): IPage<StudentWithParent>?


    /**
     * Get stu with parent by id
     *
     * @param id
     * @return
     */
    fun getStuWithParentById(id: Long): StudentWithParent?

    /*  使用不同的 typeHandler 查询 */

    /**
     * List all student with address
     *
     * @return
     */
    fun listAllStudentWithAddress(): MutableList<StudentWithAddress>?

    /**
     * Page list stu with address
     *
     * @param page
     * @return
     */
    fun pageListStuWithAddress(page: Page<*>): IPage<StudentWithAddress>?

    /**
     * Get stu with address by id
     *
     * @param id
     * @return
     */
    fun getStuWithAddressById(id: Long): StudentWithAddress
}
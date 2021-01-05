package org.example.hello.service

import org.example.hello.entities.po.Student

/**
 * @author zhuzhenjie
 * @since 2021/1/5
 */
interface DemoService {
    /**
     * 获取所有学生
     */
    fun listStudents(): MutableList<Student>?

    /**
     * 获取条件下的所有学生
     */
    fun listStudents2(): MutableList<Student>?

    /**
     * 根据 id 获取学生
     */
    fun getStudentById(id: Long): Student?

    /**
     * 根据学生获取学生
     */
    fun getStudentByName(name: String): Student?

    /**
     * 新增学生
     */
    fun addStudent(student: Student): Boolean
}

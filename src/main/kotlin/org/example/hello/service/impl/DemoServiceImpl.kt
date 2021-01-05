package org.example.hello.service.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.example.hello.entities.po.Student
import org.example.hello.mapper.StudentMapper
import org.example.hello.service.DemoService
import org.springframework.stereotype.Service

/**
 * curd 的demo
 *
 * @author winterfell
 * @since 2021/1/6
 */
@Service
class DemoServiceImpl(
        val studentMapper: StudentMapper,
) : DemoService {

    override fun listStudents(): MutableList<Student>? {
        return studentMapper.selectList(null)
    }

    override fun listStudents2(): MutableList<Student>? {
        return studentMapper.selectList(
                KtQueryWrapper(Student::class.java)
                        .gt(Student::id, 1)
        )
    }

    override fun getStudentById(id: Long): Student? {
        return studentMapper.selectById(id)
    }

    override fun getStudentByName(name: String): Student? {
        return studentMapper.selectList(
                KtQueryWrapper(Student::class.java)
                        .eq(Student::name, name)
        ).firstOrNull()
    }

    override fun addStudent(student: Student): Boolean {
        val add = studentMapper.insert(student)
        return add > 0
    }
}
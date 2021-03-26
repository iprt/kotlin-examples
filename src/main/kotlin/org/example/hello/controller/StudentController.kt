package org.example.hello.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.example.hello.config.getLogger
import org.example.hello.entities.po.StudentWithAddress
import org.example.hello.entities.po.StudentWithParent
import org.example.hello.service.StudentService
import org.slf4j.Logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sun.tools.jar.CommandLine
import java.util.*
import org.springframework.boot.CommandLineRunner as CommandLineRunner

/**
 * StudentController
 *
 * @author winterfell
 * @since 2021/3/26
 */
@RestController
@RequestMapping("/curd-examples")
class StudentController(
    val studentService: StudentService
) : CommandLineRunner {
    private val log: Logger = getLogger(this::class.java)

    @PostMapping("/student-with-parent/list-all")
    fun listAllStuWithParent(): MutableList<StudentWithParent>? = studentService.listAllStudentWithParent()

    @PostMapping("/student-with-parent/list-all-by-condition")
    fun listAllStuWithParentByCondition(): MutableList<StudentWithParent>? =
        studentService.listStuWithParentByCondition()

    @PostMapping("/student-with-parent/page-list")
    fun pageListStuWithParent(@RequestBody page: Page<StudentWithParent>): IPage<StudentWithParent>? {
        return studentService.pageListStuWithParent(page)
    }

    @PostMapping("/student-with-parent/get-by-id")
    fun getStuWithParentById(@RequestBody stu: StudentWithParent): StudentWithParent? {
        return stu.id?.let { studentService.getStuWithParentById(it) }
    }


    @PostMapping("/student-with-address/list-all")
    fun listAllStuWithAddress(): MutableList<StudentWithAddress>? {
        return studentService.listAllStudentWithAddress()
    }

    @PostMapping("/student-with-address/page-list")
    fun pageListStuWithAddress(@RequestBody page: Page<*>): IPage<StudentWithAddress>? {
        return studentService.pageListStuWithAddress(page)
    }

    @PostMapping("/student-with-address/get-by-id")
    fun getStuWithAddressById(@RequestBody stu: StudentWithAddress): StudentWithAddress? {
        return stu.id?.let { studentService.getStuWithAddressById(it) }
    }

    override fun run(vararg args: String?) {
        log.info("Student Controller Command Line Runner Start")


        log.info("Student Controller Command Line Runner End")
    }
}
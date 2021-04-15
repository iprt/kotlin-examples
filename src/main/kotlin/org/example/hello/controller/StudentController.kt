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
 * Student controller
 *
 * @property studentService
 * @constructor Create empty Student controller
 */
@RestController
@RequestMapping("/curd-examples")
class StudentController(
    val studentService: StudentService
) : CommandLineRunner {
    private val log: Logger = getLogger(this::class.java)

    /**
     * List all stu with parent
     *
     * @return
     */
    @PostMapping("/student-with-parent/list-all")
    fun listAllStuWithParent(): MutableList<StudentWithParent>? = studentService.listAllStudentWithParent()

    /**
     * List all stu with parent by condition
     *
     * @return
     */
    @PostMapping("/student-with-parent/list-all-by-condition")
    fun listAllStuWithParentByCondition(): MutableList<StudentWithParent>? =
        studentService.listStuWithParentByCondition()

    /**
     * Page list stu with parent
     *
     * @param page
     * @return
     */
    @PostMapping("/student-with-parent/page-list")
    fun pageListStuWithParent(@RequestBody page: Page<StudentWithParent>): IPage<StudentWithParent>? {
        return studentService.pageListStuWithParent(page)
    }

    /**
     * Get stu with parent by id
     *
     * @param stu
     * @return
     */
    @PostMapping("/student-with-parent/get-by-id")
    fun getStuWithParentById(@RequestBody stu: StudentWithParent): StudentWithParent? {
        return stu.id?.let { studentService.getStuWithParentById(it) }
    }


    /**
     * List all stu with address
     *
     * @return
     */
    @PostMapping("/student-with-address/list-all")
    fun listAllStuWithAddress(): MutableList<StudentWithAddress>? {
        return studentService.listAllStudentWithAddress()
    }

    /**
     * Page list stu with address
     *
     * @param page
     * @return
     */
    @PostMapping("/student-with-address/page-list")
    fun pageListStuWithAddress(@RequestBody page: Page<*>): IPage<StudentWithAddress>? {
        return studentService.pageListStuWithAddress(page)
    }

    /**
     * Get stu with address by id
     *
     * @param stu
     * @return
     */
    @PostMapping("/student-with-address/get-by-id")
    fun getStuWithAddressById(@RequestBody stu: StudentWithAddress): StudentWithAddress? {
        return stu.id?.let { studentService.getStuWithAddressById(it) }
    }

    override fun run(vararg args: String?) {
        log.info("Student Controller Command Line Runner Start")


        log.info("Student Controller Command Line Runner End")
    }
}
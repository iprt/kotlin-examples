package org.example.hello.controller

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.example.hello.config.getLogger
import org.example.hello.entities.po.Student
import org.example.hello.entities.po.StudentCopy
import org.example.hello.entities.po.User
import org.example.hello.service.DemoService
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

/**
 * 完整demo
 *
 * @author zhuzhenjie
 * @since 2021/1/5
 */
@RestController
@RequestMapping("/kotlin-demo")
@Validated
class DemoController(
    val demoService: DemoService,
) : CommandLineRunner {
    private val log: Logger = getLogger(this::class.java)

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any, Any>
    /*
    companion object : Log() {}
    private val log: Logger = LoggerFactory.getLogger(DemoController::class.java)
    */

    @GetMapping("hello")
    fun sayHello(): String = "hello kotlin"

    @GetMapping("/sayHello")
    fun sayHello(@RequestParam("name") name: String): String = "hello $name"

    @PostMapping("/sayHelloByUser")
    fun sayHelloWithUser(@RequestBody user: User): String {
        log.info("user is {}", user)
        return "hello ${user.name}"
    }

    @PostMapping("/copyUser")
    fun copyUser(@RequestBody user: User): User {
        log.info("need copy user is : {}", user)
        return copy(user)("winterfell")
    }

    @GetMapping("/testHttpServletRequest")
    fun httpServletRequestTest(httpServletRequest: HttpServletRequest): String {
        val uri = httpServletRequest.requestURI
        return "Test HttpServletRequest and uri is $uri"
    }

    // ================ curd 测试 ================

    @GetMapping("/student/list")
    fun listStudents(): MutableList<Student>? = demoService.listStudents()

    @GetMapping("/student-copy/list")
    fun listStudentCopys(): MutableList<StudentCopy>? = demoService.listStudentCopys()

    @GetMapping("/student/list2")
    fun listStudents2(): MutableList<Student>? = demoService.listStudents2()


    /**
     * 分页展示学生信息
     */
    @PostMapping("/student/pageList")
    fun pageListStudents(@RequestBody page: Page<*>): IPage<Student>? {
        log.info("page info ===> current = ${page.current} ,size = ${page.size}")
        return demoService.pageListStudents(page)
    }

    @GetMapping("/student/getById")
    fun getStudentById(@RequestParam(value = "id", required = true) id: Long): Student? {
        return demoService.getStudentById(id)
    }

    @GetMapping("/student-copy/getById")
    fun getStudentCopyById(@RequestParam(value = "id", required = true) id: Long): StudentCopy? {
        return demoService.getStudentCopyById(id)
    }

    @PostMapping("/student/getByName")
    fun getStudentByName(@RequestBody @Validated(Student.Get::class) student: Student): Student? {
        // let 在一个对象执行代码块
        return student.name?.let { demoService.getStudentByName(it) }
    }

    @PostMapping("/student/add")
    fun addStudent(@RequestBody @Validated(Student.Add::class) student: Student): Boolean {
        return demoService.addStudent(student)
    }

    // 返回一个函数 函数式编程初入
    fun copy(user: User): (String) -> User {
        val userOther = User(
            id = user.id ?: 123 + 3306,
            grade = user.grade ?: 100.0 + 99
        )
        // 返回产生一个user的函数
        return fun(name: String): User {
//            userOther.name = if (user.name == null) name else user.name
            userOther.name = user.name ?: name
            return userOther
        }
    }

    override fun run(vararg args: String?) {
        log.info("测试CommandLineRunner | {}")

        log.info("测试redis写入")
        redisTemplate.opsForValue().set("user", User(123, "test", 99.9))

        log.info("测试redis读出")
        val user = redisTemplate.opsForValue().get("user") as User
        log.info("get from redis : $user")

    }
}
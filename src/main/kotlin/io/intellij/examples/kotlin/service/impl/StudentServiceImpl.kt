package io.intellij.examples.kotlin.service.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import io.intellij.examples.kotlin.entities.po.StudentWithAddress
import io.intellij.examples.kotlin.entities.po.StudentWithParent
import io.intellij.examples.kotlin.funcs.getLogger
import io.intellij.examples.kotlin.mapper.StuWithAddressMapper
import io.intellij.examples.kotlin.mapper.StuWithParentMapper
import io.intellij.examples.kotlin.service.StudentService
import org.springframework.stereotype.Service

/**
 * Student service impl
 *
 * @property stuWithParentMapper
 * @property stuWithAddressMapper
 * @constructor Create empty Student service impl
 */
@Service
class StudentServiceImpl(
    val stuWithParentMapper: StuWithParentMapper,
    val stuWithAddressMapper: StuWithAddressMapper
) : StudentService {

    private val log = getLogger(this::class.java)

    override fun listAllStudentWithParent(): MutableList<StudentWithParent>? {
        log.info("---> list all student with parent")
        return stuWithParentMapper.selectList(null)
    }


    override fun listStuWithParentByCondition(): MutableList<StudentWithParent>? {
        val condition: KtQueryWrapper<StudentWithParent> = KtQueryWrapper(StudentWithParent::class.java)
            .ge(StudentWithParent::id, 1)
        return stuWithParentMapper.selectList(condition)
    }

    override fun pageListStuWithParent(page: Page<StudentWithParent>): IPage<StudentWithParent>? {
        // 原来的写法
        /*
                return studentWithParentMapper.pageListStudents(page)
        */
        log.info("分页查询 student with parent info")
        return stuWithParentMapper.selectPage(page, null)
    }

    override fun getStuWithParentById(id: Long): StudentWithParent? {
        return stuWithParentMapper.selectById(id)
    }

    override fun listAllStudentWithAddress(): MutableList<StudentWithAddress>? {
        log.info("---> list all student with address")
        return stuWithAddressMapper.selectList(null)
    }

    override fun pageListStuWithAddress(page: Page<*>): IPage<StudentWithAddress>? {
        return stuWithAddressMapper.pageList(page)
    }

    override fun getStuWithAddressById(id: Long): StudentWithAddress {
        return stuWithAddressMapper.selectById(id)
    }
}
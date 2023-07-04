package org.iproute.examples.kotlin.service.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.iproute.examples.kotlin.config.getLogger
import org.iproute.examples.kotlin.entities.po.StudentWithAddress
import org.iproute.examples.kotlin.entities.po.StudentWithParent
import org.iproute.examples.kotlin.mapper.StudentWithAddressMapper
import org.iproute.examples.kotlin.mapper.StudentWithParentMapper
import org.iproute.examples.kotlin.service.StudentService
import org.springframework.stereotype.Service

/**
 * Student service impl
 *
 * @property studentWithParentMapper
 * @property studentWithAddressMapper
 * @constructor Create empty Student service impl
 */
@Service
class StudentServiceImpl(
    val studentWithParentMapper: StudentWithParentMapper,
    val studentWithAddressMapper: StudentWithAddressMapper
) : StudentService {

    private val log = getLogger(this::class.java)

    override fun listAllStudentWithParent(): MutableList<StudentWithParent>? {
        log.info("---> list all student with parent")
        return studentWithParentMapper.selectList(null)
    }


    override fun listStuWithParentByCondition(): MutableList<StudentWithParent>? {
        val condition: KtQueryWrapper<StudentWithParent> = KtQueryWrapper(StudentWithParent::class.java)
            .ge(StudentWithParent::id, 1)
        return studentWithParentMapper.selectList(condition)
    }

    override fun pageListStuWithParent(page: Page<StudentWithParent>): IPage<StudentWithParent>? {
        // 原来的写法
        /*
                return studentWithParentMapper.pageListStudents(page)
        */
        log.info("分页查询 student with parent info")
        return studentWithParentMapper.selectPage(page, null)
    }

    override fun getStuWithParentById(id: Long): StudentWithParent? {
        return studentWithParentMapper.selectById(id)
    }

    override fun listAllStudentWithAddress(): MutableList<StudentWithAddress>? {
        log.info("---> list all student with address")
        return studentWithAddressMapper.selectList(null)
    }

    override fun pageListStuWithAddress(page: Page<*>): IPage<StudentWithAddress>? {
        return studentWithAddressMapper.pageList(page)
    }

    override fun getStuWithAddressById(id: Long): StudentWithAddress {
        return studentWithAddressMapper.selectById(id)
    }
}
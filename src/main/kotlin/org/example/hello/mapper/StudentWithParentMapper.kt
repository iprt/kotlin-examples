package org.example.hello.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.example.hello.entities.po.StudentWithParent
import org.springframework.stereotype.Repository

/**
 * @author winterfell
 * @since 2021/1/6
 */
@Repository
@Mapper
interface StudentWithParentMapper : BaseMapper<StudentWithParent> {

    /**
     * 分页展示学生信息
     */
    fun pageList(page: Page<*>): IPage<StudentWithParent>?
}
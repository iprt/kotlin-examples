package org.example.hello.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.example.hello.entities.po.StudentWithParent
import org.springframework.stereotype.Repository

/**
 * Student with parent mapper
 *
 * @constructor Create empty Student with parent mapper
 */
@Repository
@Mapper
interface StudentWithParentMapper : BaseMapper<StudentWithParent> {


    /**
     * Page list
     *
     * @param page
     * @return
     */
    fun pageList(page: Page<*>): IPage<StudentWithParent>?
}
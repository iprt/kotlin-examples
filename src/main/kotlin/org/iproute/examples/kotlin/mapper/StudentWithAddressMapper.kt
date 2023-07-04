package org.iproute.examples.kotlin.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.iproute.examples.kotlin.entities.po.StudentWithAddress
import org.springframework.stereotype.Repository

/**
 * Student with address mapper
 *
 * @constructor Create empty Student with address mapper
 */
@Repository
@Mapper
interface StudentWithAddressMapper : BaseMapper<StudentWithAddress> {


    /**
     * Page list
     *
     * @param page
     * @return
     */
    fun pageList(page: Page<*>): IPage<StudentWithAddress>?
}
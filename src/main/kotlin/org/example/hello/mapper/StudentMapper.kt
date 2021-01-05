package org.example.hello.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.example.hello.entities.po.Student
import org.springframework.stereotype.Repository

/**
 * @author winterfell
 * @since 2021/1/6
 */
@Repository
@Mapper
interface StudentMapper : BaseMapper<Student>
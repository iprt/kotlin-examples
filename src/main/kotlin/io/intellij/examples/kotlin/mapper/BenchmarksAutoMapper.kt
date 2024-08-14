package io.intellij.examples.kotlin.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import io.intellij.examples.kotlin.entities.po.AutoIncrementTable
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * BenchmarksAutoMapper
 *
 * @author Jzz
 * @since 2021/7/19
 */
@Repository
@Mapper
interface BenchmarksAutoMapper : BaseMapper<AutoIncrementTable>
package io.intellij.examples.kotlin.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import io.intellij.examples.kotlin.entities.po.SnowFlakeTable
import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

/**
 * BenchmarksFlowMapper
 *
 * @author tech@intellij.io
 * @since 2021/7/19
 */
@Repository
@Mapper
interface IdTypeFlowMapper : BaseMapper<SnowFlakeTable>
package org.iproute.examples.kotlin.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.iproute.examples.kotlin.entities.po.BenchmarksFlow
import org.springframework.stereotype.Repository

/**
 * BenchmarksFlowMapper
 *
 * @author Jzz
 * @since 2021/7/19
 */
@Repository
@Mapper
interface BenchmarksFlowMapper : BaseMapper<BenchmarksFlow>
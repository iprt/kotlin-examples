package org.example.hello.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.example.hello.entities.po.BenchmarksAuto
import org.springframework.stereotype.Repository

/**
 * BenchmarksAutoMapper
 *
 * @author Jzz
 * @since 2021/7/19
 */
@Repository
@Mapper
interface BenchmarksAutoMapper : BaseMapper<BenchmarksAuto>
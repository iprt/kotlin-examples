package io.intellij.examples.kotlin.controller

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import io.intellij.examples.kotlin.entities.po.AutoIncrementTable
import io.intellij.examples.kotlin.entities.po.SnowFlakeTable
import io.intellij.examples.kotlin.funcs.getLogger
import io.intellij.examples.kotlin.mapper.BenchmarksAutoMapper
import io.intellij.examples.kotlin.mapper.BenchmarksFlowMapper
import org.slf4j.Logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

/**
 * BenchmarksController
 *
 * @author Jzz
 * @since 2021/7/19
 */
@RestController
@RequestMapping("/benchmarks")
class BenchmarksController(
    private val benchmarksAutoMapper: BenchmarksAutoMapper,
    private val benchmarksFlowMapper: BenchmarksFlowMapper
) {

    private val log: Logger = getLogger(this::class.java)

    @GetMapping("/add/auto/{count}")
    fun addAuto(@PathVariable("count") count: Int): String {

        val start = Date().time

        repeat(count) {
            benchmarksAutoMapper.insert(
                AutoIncrementTable(data = UUID.randomUUID().toString() + it)
            )
        }

        val end = Date().time

        log.info("auto_increment cost ms = ${end - start}")
        return "hello world"
    }

    @GetMapping("/add/flow/{count}")
    fun addFlow(@PathVariable("count") count: Int): String {

        val start = Date().time

        repeat(count) {
            benchmarksFlowMapper.insert(
                SnowFlakeTable(data = UUID.randomUUID().toString() + it)
            )
        }

        val end = Date().time
        log.info("snowflake cost ms = ${end - start}")
        return "hello world"
    }


    @GetMapping("/query/auto")
    fun queryAuto(): String {
        val start: KtQueryWrapper<AutoIncrementTable> = KtQueryWrapper(AutoIncrementTable::class.java)
            .apply(false, "limit 0,25000")
        val startList = benchmarksAutoMapper.selectList(start)
        val end: KtQueryWrapper<AutoIncrementTable> = KtQueryWrapper(AutoIncrementTable::class.java)
            .apply(false, "limit 175000,25000")
        val endList = benchmarksAutoMapper.selectList(end)

        startList.addAll(endList)

        val idList = startList.stream().map(AutoIncrementTable::id)
            .collect(Collectors.toList())

        idList.shuffle()

        val startTime = Date().time
        idList.forEach {
            val auto = benchmarksAutoMapper.selectById(it)
            log.info(auto.toString())
        }
        val endTime = Date().time

        log.info("auto_increment query cost ms = ${endTime - startTime}")
        return "hello world"
    }

    @GetMapping("/query/flow")
    fun queryFlow(): String {
        val start: KtQueryWrapper<SnowFlakeTable> = KtQueryWrapper(SnowFlakeTable::class.java)
            .apply(false, "limit 0,25000")
        val startList = benchmarksFlowMapper.selectList(start)
        val end: KtQueryWrapper<SnowFlakeTable> = KtQueryWrapper(SnowFlakeTable::class.java)
            .apply(false, "limit 175000,25000")
        val endList = benchmarksFlowMapper.selectList(end)

        startList.addAll(endList)

        val idList = startList.stream().map(SnowFlakeTable::id)
            .collect(Collectors.toList())

        idList.shuffle()

        val startTime = Date().time
        idList.forEach {
            val auto = benchmarksFlowMapper.selectById(it)
            log.info(auto.toString())
        }
        val endTime = Date().time

        log.info("snowflake query cost ms = ${endTime - startTime}")
        return "hello world"
    }
}
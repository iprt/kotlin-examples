package io.intellij.examples.kotlin.controller

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import io.intellij.examples.kotlin.entities.po.AutoIncrementTable
import io.intellij.examples.kotlin.entities.po.SnowFlakeTable
import io.intellij.examples.kotlin.funcs.getLogger
import io.intellij.examples.kotlin.mapper.IdTypeAutoMapper
import io.intellij.examples.kotlin.mapper.IdTypeFlowMapper
import org.slf4j.Logger
import org.springframework.util.StopWatch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

/**
 * IdTypeController
 *
 * @author tech@intellij.io
 * @since 2021/7/19
 */
@RestController
@RequestMapping("/id-type")
class IdTypeController(
    private val idTypeAutoMapper: IdTypeAutoMapper,
    private val idTypeFlowMapper: IdTypeFlowMapper
) {

    private val log: Logger = getLogger(this::class.java)

    @GetMapping("/add/autoincrement/{count}")
    fun addAuto(@PathVariable("count") count: Int): String {
        val stopWatch = StopWatch()
        stopWatch.start("addAuto")
        repeat(count) {
            idTypeAutoMapper.insert(
                AutoIncrementTable(data = UUID.randomUUID().toString() + it)
            )
        }
        stopWatch.stop()
        log.info("auto_increment cost \n ${stopWatch.prettyPrint()}")
        return "increment id insert"
    }

    @GetMapping("/add/snowflake/{count}")
    fun addFlow(@PathVariable("count") count: Int): String {
        val stopWatch = StopWatch()
        stopWatch.start("addFlow")
        repeat(count) {
            idTypeFlowMapper.insert(
                SnowFlakeTable(data = UUID.randomUUID().toString() + it)
            )
        }
        stopWatch.stop()
        log.info("snowflake cost \n ${stopWatch.prettyPrint()}")
        return "snowflake id insert"
    }


    @GetMapping("/query/autoincrement")
    fun queryAuto(): String {
        val start: KtQueryWrapper<AutoIncrementTable> = KtQueryWrapper(AutoIncrementTable::class.java)
            .apply(false, "limit 0,25000")
        val startList = idTypeAutoMapper.selectList(start)
        val end: KtQueryWrapper<AutoIncrementTable> = KtQueryWrapper(AutoIncrementTable::class.java)
            .apply(false, "limit 175000,25000")
        val endList = idTypeAutoMapper.selectList(end)

        startList.addAll(endList)

        val idList = startList.stream().map(AutoIncrementTable::id)
            .collect(Collectors.toList())

        // 使用shuffle函数对列表进行随机打乱
        idList.shuffle()
        val stopWatch = StopWatch()
        stopWatch.start("autoincrement query")

        idList.forEach {
            val auto = idTypeAutoMapper.selectById(it)
            log.info(auto.toString())
        }

        stopWatch.stop()

        log.info("auto_increment query \n ${stopWatch.prettyPrint()}")
        return "increment id query"
    }

    @GetMapping("/query/snowflake")
    fun queryFlow(): String {
        val start: KtQueryWrapper<SnowFlakeTable> = KtQueryWrapper(SnowFlakeTable::class.java)
            .apply(false, "limit 0,25000")
        val startList = idTypeFlowMapper.selectList(start)
        val end: KtQueryWrapper<SnowFlakeTable> = KtQueryWrapper(SnowFlakeTable::class.java)
            .apply(false, "limit 175000,25000")
        val endList = idTypeFlowMapper.selectList(end)

        startList.addAll(endList)

        val idList = startList.stream().map(SnowFlakeTable::id)
            .collect(Collectors.toList())

        idList.shuffle()

        val stopWatch = StopWatch()
        stopWatch.start("snowflake query")
        idList.forEach {
            val auto = idTypeFlowMapper.selectById(it)
            log.info(auto.toString())
        }
        stopWatch.stop()
        log.info("snowflake query \n ${stopWatch.prettyPrint()}")
        return "snowflake id query"
    }

}
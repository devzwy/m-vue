package com.ruoyi.quartz.task

import com.ruoyi.common.utils.bean.log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import kotlin.collections.HashMap

@Component("XyTask")
class XyTask {
    @Autowired
    var mongoTemplate: MongoTemplate? = null

    fun loadXiyouDataFromMongoDB() {
        log("触发自动任务，同步游戏数据...")
        //查找账号数据 账号不一定有角色
        val mongoAccounts = mongoTemplate!!.findAll(HashMap::class.java, "account_xiyou")
        for (i in mongoAccounts.indices) {
            val account = mongoAccounts[i]["key"].toString()

            //根据账号查询角色信息(可能是空的，未创建角色)
            val query = Query.query(Criteria.where("key").`is`(account))
            val mongoRoles = mongoTemplate!!.find(query, HashMap::class.java, "roleinfo_xiyou")
            if (mongoRoles.size == 0) {
                log("账号：${account}未创建角色信息")
                continue
            }
            val roles = mongoRoles.get(0)["rolesx"]
            if (roles is HashMap<*, *>) {
                val r2 = roles["md_s_1"]
                if (r2 is HashMap<*, *>) {
                    log("账号(${account})下有${r2.size}个角色 -> ")
                    r2.forEach { t, u ->
                        log("角色ID:${t}")
                        log("角色属性:${u}")
                    }
                }

            }

        }
    }
}
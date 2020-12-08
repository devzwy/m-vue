package com.ruoyi.quartz.task

import com.ruoyi.common.utils.bean.log
import com.ruoyi.system.domain.XyAccount
import com.ruoyi.system.domain.XyRole
import com.ruoyi.system.service.IXyAccountService
import com.ruoyi.system.service.IXyRoleService
import com.ruoyi.system.service.impl.XyRoleServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import kotlin.collections.HashMap

@Component("XyTask")
class XyTask {
    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @Autowired
    lateinit var mIXyAccountService: IXyAccountService

    @Autowired
    lateinit var mIXyRoleService: IXyRoleService


    fun loadXiyouDataFromMongoDB() {
        log("触发自动任务，同步游戏数据...")
        //查找账号数据 账号不一定有角色
        val mongoAccounts = mongoTemplate.findAll(HashMap::class.java, "account_xiyou")
        for (i in mongoAccounts.indices) {
            val account = mongoAccounts[i]["key"].toString()

            //根据账号查询角色信息(可能是空的，未创建角色)
            val query = Query.query(Criteria.where("key").`is`(account))
            val mongoRoles = mongoTemplate.find(query, HashMap::class.java, "roleinfo_xiyou")
            if (mongoRoles.size == 0) {
                log("账号：${account}未创建角色信息")
                mIXyAccountService.selectXyAccountById(account).let { accountDB ->
                    if (accountDB == null) {
                        mIXyAccountService.insertXyAccount(XyAccount().also {
                            it.account = account
                            it.xyRoleNum = 0
                        })
                    } else {
                        mIXyAccountService.updateXyAccount(accountDB.also {
                            it.xyRoleNum = 0
                        })
                    }
                }

                continue
            }
            val roles = mongoRoles.get(0)["rolesx"]
            if (roles is HashMap<*, *>) {
                val r2 = roles["md_s_1"]
                if (r2 is HashMap<*, *>) {
                    mIXyAccountService.selectXyAccountById(account).let { accountDB ->
                        if (accountDB == null) {
                            val a = XyAccount().also {
                                it.account = account
                                it.xyRoleNum = r2.size.toLong()
                            }
                            mIXyAccountService.insertXyAccount(a)
                        } else {
                            mIXyAccountService.updateXyAccount(accountDB.also {
                                it.xyRoleNum = r2.size.toLong()
                            })
                        }
                    }
                    log("账号(${account})下有${r2.size}个角色 -> ")
                    r2.forEach { t, u ->
                        mIXyRoleService.selectXyRoleById(t.toString()).let { roleDB ->
                            if (roleDB == null) {
                                mIXyRoleService.insertXyRole(XyRole().also {
                                    it.xyAccount = account
                                    it.xyRoleId = t.toString()
                                    if (u is Map<*, *>) {
                                        it.xyRoleLevel = u["lv"].toString().toLong()
                                        it.xyRoleType = u["rtype"].toString().toLong()
                                        it.xyRoleName = u["name"].toString()
                                        it.xyRoleLevelZs = u["rb"].toString().toLong()
                                    } else {
                                        throw TypeNotPresentException("数据转换失败", Throwable())
                                    }
//                                    val mongoRoleBena = Gson().
                                })
                            } else {
                                mIXyRoleService.updateXyRole(roleDB.also {
                                    it.xyAccount = account
                                    if (u is Map<*, *>) {
                                        it.xyRoleLevel = u["lv"].toString().toLong()
                                        it.xyRoleType = u["rtype"].toString().toLong()
                                        it.xyRoleName = u["name"].toString()
                                        it.xyRoleLevelZs = u["rb"].toString().toLong()
                                    } else {
                                        throw TypeNotPresentException("数据转换失败", Throwable())
                                    }
                                })
                            }
                        }
                        log("角色ID:${t}")
                        log("角色属性:${u}")

                    }
                }

            }

        }
    }
}
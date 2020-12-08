package com.ruoyi.common.utils.bean

import java.util.logging.Logger
import org.springframework.data.mongodb.core.mapping.Field

const val TAG = "赵文文"

fun log(msg: String?) {
    Logger.getLogger(TAG).info(msg)
}

//{rtype=15001, rid=100108, lv=33, name=霸气外露, rb=0}
data class MongoXYRole(val rtype: Long, val rid: Long, val lv: Int, val name: String, val rn: Int)
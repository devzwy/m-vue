package com.ruoyi.common.utils.bean

import java.util.logging.Logger
import org.springframework.data.mongodb.core.mapping.Field

const val TAG = "赵文文"

fun log(msg: String?) {
    Logger.getLogger(TAG).info(msg)
}
package com.ruoyi.common.enums;

/**
 * 业务操作类型
 * 
 * @author ruoyi
 */
public enum BusinessType
{
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,
    
    /**
     * 清空数据
     */
    CLEAN,

    /**
     * 禁言
     */
    BANMSG,

    /**
     * 封号
     */
    BANROLE,

    /**
         * 充值元宝
     */
    ADDYB,


    /**
     * 充值元宝套餐
     */
    ADDYBTC,

    /**
     * 充值福利
     */
    ADDFuli,

    //发布公告
    SEND_NOTICE,

    //发送全服福利
    SEND_ALL_GIFT,

    //发送物品
    SEND_ITEM,





}

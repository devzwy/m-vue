package com.ruoyi.system.domain;

import io.lettuce.core.dynamic.annotation.Param;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 西游账号对象 xy_account
 *
 * @author ruoyi
 * @date 2020-12-04
 */
public class XyAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 账号 */
    @Excel(name = "账号")
    private String account;

    /** 角色数量 */
    @Excel(name = "角色数量")
    private Long xyRoleNum;

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getAccount()
    {
        return account;
    }
    public void setXyRoleNum(Long xyRoleNum)
    {
        this.xyRoleNum = xyRoleNum;
    }

    public Long getXyRoleNum()
    {
        return xyRoleNum;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("account", getAccount())
                .append("xyRoleNum", getXyRoleNum())
                .toString();
    }
}
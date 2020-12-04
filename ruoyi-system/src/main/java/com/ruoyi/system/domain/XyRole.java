package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 西游角色对象 xy_role
 * 
 * @author ruoyi
 * @date 2020-12-04
 */
public class XyRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private String xyRoleId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String xyRoleName;

    /** 角色类型 */
    @Excel(name = "角色类型")
    private Long xyRoleType;

    /** 角色等级 */
    @Excel(name = "角色等级")
    private Long xyRoleLevel;

    /** 角色转生等级 */
    @Excel(name = "角色转生等级")
    private Long xyRoleLevelZs;

    /** 角色账号 */
    @Excel(name = "角色账号")
    private String xyAccount;

    private String p1;

    private String p2;

    private String p3;

    private String p4;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getP4() {
        return p4;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public void setXyRoleId(String xyRoleId)
    {
        this.xyRoleId = xyRoleId;
    }

    public String getXyRoleId() 
    {
        return xyRoleId;
    }
    public void setXyRoleName(String xyRoleName) 
    {
        this.xyRoleName = xyRoleName;
    }

    public String getXyRoleName() 
    {
        return xyRoleName;
    }
    public void setXyRoleType(Long xyRoleType)
    {
        this.xyRoleType = xyRoleType;
    }

    public Long getXyRoleType()
    {
        return xyRoleType;
    }
    public void setXyRoleLevel(Long xyRoleLevel) 
    {
        this.xyRoleLevel = xyRoleLevel;
    }

    public Long getXyRoleLevel() 
    {
        return xyRoleLevel;
    }
    public void setXyRoleLevelZs(Long xyRoleLevelZs) 
    {
        this.xyRoleLevelZs = xyRoleLevelZs;
    }

    public Long getXyRoleLevelZs() 
    {
        return xyRoleLevelZs;
    }
    public void setXyAccount(String xyAccount) 
    {
        this.xyAccount = xyAccount;
    }

    public String getXyAccount() 
    {
        return xyAccount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("xyRoleId", getXyRoleId())
            .append("xyRoleName", getXyRoleName())
            .append("xyRoleType", getXyRoleType())
            .append("xyRoleLevel", getXyRoleLevel())
            .append("xyRoleLevelZs", getXyRoleLevelZs())
            .append("xyAccount", getXyAccount())
            .toString();
    }
}

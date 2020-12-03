package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 西游角色对象 xy_role
 * 
 * @author Jason
 * @date 2020-12-03
 */
public class XyRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色账号 */
    @Excel(name = "角色账号")
    private String account;

    /** 服务区组ID */
    @Excel(name = "服务区组ID")
    private Long serverId;

    /** 角色ID */
    @Excel(name = "角色ID")
    private Long roleId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 角色等级 */
    @Excel(name = "角色等级")
    private Long level;

    /** 角色类型 */
    @Excel(name = "角色类型")
    private Long roleType;

    /** 转生等级 */
    @Excel(name = "转生等级")
    private Long levelZs;

    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setServerId(Long serverId) 
    {
        this.serverId = serverId;
    }

    public Long getServerId() 
    {
        return serverId;
    }
    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setRoleType(Long roleType) 
    {
        this.roleType = roleType;
    }

    public Long getRoleType() 
    {
        return roleType;
    }
    public void setLevelZs(Long levelZs) 
    {
        this.levelZs = levelZs;
    }

    public Long getLevelZs() 
    {
        return levelZs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("account", getAccount())
            .append("serverId", getServerId())
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("level", getLevel())
            .append("roleType", getRoleType())
            .append("levelZs", getLevelZs())
            .toString();
    }
}

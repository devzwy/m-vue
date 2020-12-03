package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.XyRole;

/**
 * 西游角色Service接口
 * 
 * @author Jason
 * @date 2020-12-03
 */
public interface IXyRoleService 
{
    /**
     * 查询西游角色
     * 
     * @param account 西游角色ID
     * @return 西游角色
     */
    public XyRole selectXyRoleById(String account);

    /**
     * 查询西游角色列表
     * 
     * @param xyRole 西游角色
     * @return 西游角色集合
     */
    public List<XyRole> selectXyRoleList(XyRole xyRole);

    /**
     * 新增西游角色
     * 
     * @param xyRole 西游角色
     * @return 结果
     */
    public int insertXyRole(XyRole xyRole);

    /**
     * 修改西游角色
     * 
     * @param xyRole 西游角色
     * @return 结果
     */
    public int updateXyRole(XyRole xyRole);

    /**
     * 批量删除西游角色
     * 
     * @param accounts 需要删除的西游角色ID
     * @return 结果
     */
    public int deleteXyRoleByIds(String[] accounts);

    /**
     * 删除西游角色信息
     * 
     * @param account 西游角色ID
     * @return 结果
     */
    public int deleteXyRoleById(String account);
}

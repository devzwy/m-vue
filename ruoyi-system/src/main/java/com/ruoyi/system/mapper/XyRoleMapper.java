package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.XyRole;

/**
 * 西游角色Mapper接口
 * 
 * @author ruoyi
 * @date 2020-12-04
 */
public interface XyRoleMapper 
{
    /**
     * 查询西游角色
     * 
     * @param xyRoleId 西游角色ID
     * @return 西游角色
     */
    public XyRole selectXyRoleById(String xyRoleId);

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
     * 删除西游角色
     * 
     * @param xyRoleId 西游角色ID
     * @return 结果
     */
    public int deleteXyRoleById(String xyRoleId);

    /**
     * 批量删除西游角色
     * 
     * @param xyRoleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteXyRoleByIds(String[] xyRoleIds);
}

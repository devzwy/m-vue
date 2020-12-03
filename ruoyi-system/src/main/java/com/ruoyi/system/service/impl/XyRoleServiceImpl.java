package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XyRoleMapper;
import com.ruoyi.system.domain.XyRole;
import com.ruoyi.system.service.IXyRoleService;

/**
 * 西游角色Service业务层处理
 * 
 * @author Jason
 * @date 2020-12-03
 */
@Service
public class XyRoleServiceImpl implements IXyRoleService 
{
    @Autowired
    private XyRoleMapper xyRoleMapper;

    /**
     * 查询西游角色
     * 
     * @param account 西游角色ID
     * @return 西游角色
     */
    @Override
    public XyRole selectXyRoleById(String account)
    {
        return xyRoleMapper.selectXyRoleById(account);
    }

    /**
     * 查询西游角色列表
     * 
     * @param xyRole 西游角色
     * @return 西游角色
     */
    @Override
    public List<XyRole> selectXyRoleList(XyRole xyRole)
    {
        return xyRoleMapper.selectXyRoleList(xyRole);
    }

    /**
     * 新增西游角色
     * 
     * @param xyRole 西游角色
     * @return 结果
     */
    @Override
    public int insertXyRole(XyRole xyRole)
    {
        return xyRoleMapper.insertXyRole(xyRole);
    }

    /**
     * 修改西游角色
     * 
     * @param xyRole 西游角色
     * @return 结果
     */
    @Override
    public int updateXyRole(XyRole xyRole)
    {
        return xyRoleMapper.updateXyRole(xyRole);
    }

    /**
     * 批量删除西游角色
     * 
     * @param accounts 需要删除的西游角色ID
     * @return 结果
     */
    @Override
    public int deleteXyRoleByIds(String[] accounts)
    {
        return xyRoleMapper.deleteXyRoleByIds(accounts);
    }

    /**
     * 删除西游角色信息
     * 
     * @param account 西游角色ID
     * @return 结果
     */
    @Override
    public int deleteXyRoleById(String account)
    {
        return xyRoleMapper.deleteXyRoleById(account);
    }
}

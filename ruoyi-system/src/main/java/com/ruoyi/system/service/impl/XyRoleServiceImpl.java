package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.GMUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XyRoleMapper;
import com.ruoyi.system.domain.XyRole;
import com.ruoyi.system.service.IXyRoleService;

/**
 * 西游角色Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-04
 */
@Service
public class XyRoleServiceImpl implements IXyRoleService {
    @Autowired
    private XyRoleMapper xyRoleMapper;

    @Autowired
    GMUtils gmUtils;

    /**
     * 查询西游角色
     *
     * @param xyRoleId 西游角色ID
     * @return 西游角色
     */
    @Override
    public XyRole selectXyRoleById(String xyRoleId) {
        return xyRoleMapper.selectXyRoleById(xyRoleId);
    }

    /**
     * 查询西游角色列表
     *
     * @param xyRole 西游角色
     * @return 西游角色
     */
    @Override
    public List<XyRole> selectXyRoleList(XyRole xyRole) {
        return xyRoleMapper.selectXyRoleList(xyRole);
    }

    /**
     * 新增西游角色
     *
     * @param xyRole 西游角色
     * @return 结果
     */
    @Override
    public int insertXyRole(XyRole xyRole) {
        return xyRoleMapper.insertXyRole(xyRole);
    }

    /**
     * 修改西游角色
     *
     * @param xyRole 西游角色
     * @return 结果
     */
    @Override
    public int updateXyRole(XyRole xyRole) {
        return xyRoleMapper.updateXyRole(xyRole);
    }

    /**
     * 修改西游角色 GM同步修改资料
     *
     * @param xyRole 西游角色
     * @return 结果
     */
    @Override
    public int updateXyRole2(XyRole xyRole) {
        gmUtils.setHeroPro(1, xyRole.getXyRoleLevel(), xyRole.getXyRoleLevelZs(), Long.valueOf(xyRole.getXyRoleId()));
        gmUtils.setHeroShuXing(1, Integer.valueOf(xyRole.getP1()),
                Integer.valueOf(xyRole.getP2()),
                Integer.valueOf(xyRole.getP3()),
                Integer.valueOf(xyRole.getP4()),
                xyRole.getXyRoleId());
        return xyRoleMapper.updateXyRole(xyRole);
    }




    /**
     * 批量删除西游角色
     *
     * @param xyRoleIds 需要删除的西游角色ID
     * @return 结果
     */
    @Override
    public int deleteXyRoleByIds(String[] xyRoleIds) {
        return xyRoleMapper.deleteXyRoleByIds(xyRoleIds);
    }

    /**
     * 删除西游角色信息
     *
     * @param xyRoleId 西游角色ID
     * @return 结果
     */
    @Override
    public int deleteXyRoleById(String xyRoleId) {
        return xyRoleMapper.deleteXyRoleById(xyRoleId);
    }
}

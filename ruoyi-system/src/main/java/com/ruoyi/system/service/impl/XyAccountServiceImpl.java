package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XyAccountMapper;
import com.ruoyi.system.domain.XyAccount;
import com.ruoyi.system.service.IXyAccountService;

/**
 * 西游账号Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-04
 */
@Service
public class XyAccountServiceImpl implements IXyAccountService
{
    @Autowired
    private XyAccountMapper xyAccountMapper;

    /**
     * 查询西游账号
     *
     * @param account 西游账号ID
     * @return 西游账号
     */
    @Override
    public XyAccount selectXyAccountById(String account)
    {
        return xyAccountMapper.selectXyAccountById(account);
    }

    /**
     * 查询西游账号列表
     *
     * @param xyAccount 西游账号
     * @return 西游账号
     */
    @Override
    public List<XyAccount> selectXyAccountList(XyAccount xyAccount)
    {
        return xyAccountMapper.selectXyAccountList(xyAccount);
    }

    /**
     * 新增西游账号
     *
     * @param xyAccount 西游账号
     * @return 结果
     */
    @Override
    public int insertXyAccount(XyAccount xyAccount)
    {
        return xyAccountMapper.insertXyAccount(xyAccount);
    }

    /**
     * 修改西游账号
     *
     * @param xyAccount 西游账号
     * @return 结果
     */
    @Override
    public int updateXyAccount(XyAccount xyAccount)
    {
        return xyAccountMapper.updateXyAccount(xyAccount);
    }

    /**
     * 批量删除西游账号
     *
     * @param accounts 需要删除的西游账号ID
     * @return 结果
     */
    @Override
    public int deleteXyAccountByIds(String[] accounts)
    {
        return xyAccountMapper.deleteXyAccountByIds(accounts);
    }

    /**
     * 删除西游账号信息
     *
     * @param account 西游账号ID
     * @return 结果
     */
    @Override
    public int deleteXyAccountById(String account)
    {
        return xyAccountMapper.deleteXyAccountById(account);
    }
}
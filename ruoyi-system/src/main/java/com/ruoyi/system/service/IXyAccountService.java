package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.XyAccount;

/**
 * 西游账号Service接口
 *
 * @author ruoyi
 * @date 2020-12-04
 */
public interface IXyAccountService
{
    /**
     * 查询西游账号
     *
     * @param account 西游账号ID
     * @return 西游账号
     */
    public XyAccount selectXyAccountById(String account);

    /**
     * 查询西游账号列表
     *
     * @param xyAccount 西游账号
     * @return 西游账号集合
     */
    public List<XyAccount> selectXyAccountList(XyAccount xyAccount);

    /**
     * 新增西游账号
     *
     * @param xyAccount 西游账号
     * @return 结果
     */
    public int insertXyAccount(XyAccount xyAccount);

    /**
     * 修改西游账号
     *
     * @param xyAccount 西游账号
     * @return 结果
     */
    public int updateXyAccount(XyAccount xyAccount);

    /**
     * 批量删除西游账号
     *
     * @param accounts 需要删除的西游账号ID
     * @return 结果
     */
    public int deleteXyAccountByIds(String[] accounts);

    /**
     * 删除西游账号信息
     *
     * @param account 西游账号ID
     * @return 结果
     */
    public int deleteXyAccountById(String account);
}
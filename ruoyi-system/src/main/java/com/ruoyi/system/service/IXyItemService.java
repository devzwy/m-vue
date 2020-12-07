package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.XyItem;

/**
 * 西游物资Service接口
 * 
 * @author Jason
 * @date 2020-12-07
 */
public interface IXyItemService 
{
    /**
     * 查询西游物资
     * 
     * @param id 西游物资ID
     * @return 西游物资
     */
    public XyItem selectXyItemById(Long id);

    /**
     * 查询西游物资列表
     * 
     * @param xyItem 西游物资
     * @return 西游物资集合
     */
    public List<XyItem> selectXyItemList(XyItem xyItem);

    /**
     * 新增西游物资
     * 
     * @param xyItem 西游物资
     * @return 结果
     */
    public int insertXyItem(XyItem xyItem);

    /**
     * 修改西游物资
     * 
     * @param xyItem 西游物资
     * @return 结果
     */
    public int updateXyItem(XyItem xyItem);

    /**
     * 批量删除西游物资
     * 
     * @param ids 需要删除的西游物资ID
     * @return 结果
     */
    public int deleteXyItemByIds(Long[] ids);

    /**
     * 删除西游物资信息
     * 
     * @param id 西游物资ID
     * @return 结果
     */
    public int deleteXyItemById(Long id);
}

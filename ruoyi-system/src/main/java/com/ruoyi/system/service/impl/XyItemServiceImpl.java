package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XyItemMapper;
import com.ruoyi.system.domain.XyItem;
import com.ruoyi.system.service.IXyItemService;

/**
 * 西游物资Service业务层处理
 * 
 * @author Jason
 * @date 2020-12-07
 */
@Service
public class XyItemServiceImpl implements IXyItemService 
{
    @Autowired
    private XyItemMapper xyItemMapper;

    /**
     * 查询西游物资
     * 
     * @param id 西游物资ID
     * @return 西游物资
     */
    @Override
    public XyItem selectXyItemById(Long id)
    {
        return xyItemMapper.selectXyItemById(id);
    }

    /**
     * 查询西游物资列表
     * 
     * @param xyItem 西游物资
     * @return 西游物资
     */
    @Override
    public List<XyItem> selectXyItemList(XyItem xyItem)
    {
        return xyItemMapper.selectXyItemList(xyItem);
    }

    /**
     * 新增西游物资
     * 
     * @param xyItem 西游物资
     * @return 结果
     */
    @Override
    public int insertXyItem(XyItem xyItem)
    {
        return xyItemMapper.insertXyItem(xyItem);
    }

    /**
     * 修改西游物资
     * 
     * @param xyItem 西游物资
     * @return 结果
     */
    @Override
    public int updateXyItem(XyItem xyItem)
    {
        return xyItemMapper.updateXyItem(xyItem);
    }

    /**
     * 批量删除西游物资
     * 
     * @param ids 需要删除的西游物资ID
     * @return 结果
     */
    @Override
    public int deleteXyItemByIds(Long[] ids)
    {
        return xyItemMapper.deleteXyItemByIds(ids);
    }

    /**
     * 删除西游物资信息
     * 
     * @param id 西游物资ID
     * @return 结果
     */
    @Override
    public int deleteXyItemById(Long id)
    {
        return xyItemMapper.deleteXyItemById(id);
    }
}

package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 西游物资对象 xy_item
 * 
 * @author Jason
 * @date 2020-12-07
 */
public class XyItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 物资ID */
    @Excel(name = "物资ID")
    private Long itemId;

    /** 物资类型 */
    @Excel(name = "物资类型")
    private Long itemType;

    /** 物资名称 */
    @Excel(name = "物资名称")
    private String itemName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setItemType(Long itemType) 
    {
        this.itemType = itemType;
    }

    public Long getItemType() 
    {
        return itemType;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemId", getItemId())
            .append("itemType", getItemType())
            .append("itemName", getItemName())
            .toString();
    }
}

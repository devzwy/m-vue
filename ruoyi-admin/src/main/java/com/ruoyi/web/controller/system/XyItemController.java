package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.XyItem;
import com.ruoyi.system.service.IXyItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 西游物资Controller
 * 
 * @author Jason
 * @date 2020-12-07
 */
@RestController
@RequestMapping("/system/xy_item")
public class XyItemController extends BaseController
{
    @Autowired
    private IXyItemService xyItemService;

    /**
     * 查询西游物资列表
     */
    @PreAuthorize("@ss.hasPermi('system:xy_item:list')")
    @GetMapping("/list")
    public TableDataInfo list(XyItem xyItem)
    {
        startPage();
        List<XyItem> list = xyItemService.selectXyItemList(xyItem);
        return getDataTable(list);
    }

    /**
     * 导出西游物资列表
     */
    @PreAuthorize("@ss.hasPermi('system:xy_item:export')")
    @Log(title = "西游物资", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(XyItem xyItem)
    {
        List<XyItem> list = xyItemService.selectXyItemList(xyItem);
        ExcelUtil<XyItem> util = new ExcelUtil<XyItem>(XyItem.class);
        return util.exportExcel(list, "xy_item");
    }

    /**
     * 获取西游物资详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xy_item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(xyItemService.selectXyItemById(id));
    }

    /**
     * 新增西游物资
     */
    @PreAuthorize("@ss.hasPermi('system:xy_item:add')")
    @Log(title = "西游物资", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XyItem xyItem)
    {
        return toAjax(xyItemService.insertXyItem(xyItem));
    }

    /**
     * 修改西游物资
     */
    @PreAuthorize("@ss.hasPermi('system:xy_item:edit')")
    @Log(title = "西游物资", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XyItem xyItem)
    {
        return toAjax(xyItemService.updateXyItem(xyItem));
    }

    /**
     * 删除西游物资
     */
    @PreAuthorize("@ss.hasPermi('system:xy_item:remove')")
    @Log(title = "西游物资", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(xyItemService.deleteXyItemByIds(ids));
    }
}

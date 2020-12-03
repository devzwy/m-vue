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
import com.ruoyi.system.domain.XyRole;
import com.ruoyi.system.service.IXyRoleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 西游角色Controller
 * 
 * @author Jason
 * @date 2020-12-03
 */
@RestController
@RequestMapping("/system/xy_role")
public class XyRoleController extends BaseController
{
    @Autowired
    private IXyRoleService xyRoleService;

    /**
     * 查询西游角色列表
     */
    @PreAuthorize("@ss.hasPermi('system:xy_role:list')")
    @GetMapping("/list")
    public TableDataInfo list(XyRole xyRole)
    {
        startPage();
        List<XyRole> list = xyRoleService.selectXyRoleList(xyRole);
        return getDataTable(list);
    }

    /**
     * 导出西游角色列表
     */
    @PreAuthorize("@ss.hasPermi('system:xy_role:export')")
    @Log(title = "西游角色", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(XyRole xyRole)
    {
        List<XyRole> list = xyRoleService.selectXyRoleList(xyRole);
        ExcelUtil<XyRole> util = new ExcelUtil<XyRole>(XyRole.class);
        return util.exportExcel(list, "xy_role");
    }

    /**
     * 获取西游角色详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xy_role:query')")
    @GetMapping(value = "/{account}")
    public AjaxResult getInfo(@PathVariable("account") String account)
    {
        return AjaxResult.success(xyRoleService.selectXyRoleById(account));
    }

    /**
     * 新增西游角色
     */
    @PreAuthorize("@ss.hasPermi('system:xy_role:add')")
    @Log(title = "西游角色", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XyRole xyRole)
    {
        return toAjax(xyRoleService.insertXyRole(xyRole));
    }

    /**
     * 修改西游角色
     */
    @PreAuthorize("@ss.hasPermi('system:xy_role:edit')")
    @Log(title = "西游角色", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XyRole xyRole)
    {
        return toAjax(xyRoleService.updateXyRole(xyRole));
    }

    /**
     * 删除西游角色
     */
    @PreAuthorize("@ss.hasPermi('system:xy_role:remove')")
    @Log(title = "西游角色", businessType = BusinessType.DELETE)
	@DeleteMapping("/{accounts}")
    public AjaxResult remove(@PathVariable String[] accounts)
    {
        return toAjax(xyRoleService.deleteXyRoleByIds(accounts));
    }
}

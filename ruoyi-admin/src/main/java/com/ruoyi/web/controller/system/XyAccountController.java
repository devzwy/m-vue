package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.XyRole;
import com.ruoyi.system.service.IXyRoleService;
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
import com.ruoyi.system.domain.XyAccount;
import com.ruoyi.system.service.IXyAccountService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 西游账号Controller
 *
 * @author ruoyi
 * @date 2020-12-04
 */
@RestController
@RequestMapping("/system/xy_account")
public class XyAccountController extends BaseController {
    @Autowired
    private IXyAccountService xyAccountService;

    @Autowired
    private IXyRoleService xyRoleService;


    /**
     * 查询西游账号列表
     */
    @PreAuthorize("@ss.hasPermi('system:xy_account:list')")
    @GetMapping("/list")
    public TableDataInfo list(XyAccount xyAccount) {
        startPage();
        List<XyAccount> list = xyAccountService.selectXyAccountList(xyAccount);
//        for (int i = 0; i < list.size(); i++) {
//            XyRole xyRole = new XyRole();
//            xyRole.setXyAccount(list.get(i).getAccount());
//            list.get(i).setXyRoleNum(Long.valueOf(String.valueOf(xyRoleService.selectXyRoleList(xyRole).size())));
//        }
        return getDataTable(list);
    }

    /**
     * 导出西游账号列表
     */
    @PreAuthorize("@ss.hasPermi('system:xy_account:export')")
    @Log(title = "西游账号", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(XyAccount xyAccount) {
        List<XyAccount> list = xyAccountService.selectXyAccountList(xyAccount);
        ExcelUtil<XyAccount> util = new ExcelUtil<XyAccount>(XyAccount.class);
        return util.exportExcel(list, "xy_account");
    }

    /**
     * 获取西游账号详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:xy_account:query')")
    @GetMapping(value = "/{account}")
    public AjaxResult getInfo(@PathVariable("account") String account) {
        return AjaxResult.success(xyAccountService.selectXyAccountById(account));
    }

    /**
     * 新增西游账号
     */
    @PreAuthorize("@ss.hasPermi('system:xy_account:add')")
    @Log(title = "西游账号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody XyAccount xyAccount) {
        return toAjax(xyAccountService.insertXyAccount(xyAccount));
    }

    /**
     * 修改西游账号
     */
    @PreAuthorize("@ss.hasPermi('system:xy_account:edit')")
    @Log(title = "西游账号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody XyAccount xyAccount) {
        return toAjax(xyAccountService.updateXyAccount(xyAccount));
    }

    /**
     * 删除西游账号
     */
    @PreAuthorize("@ss.hasPermi('system:xy_account:remove')")
    @Log(title = "西游账号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{accounts}")
    public AjaxResult remove(@PathVariable String[] accounts) {
        return toAjax(xyAccountService.deleteXyAccountByIds(accounts));
    }
}
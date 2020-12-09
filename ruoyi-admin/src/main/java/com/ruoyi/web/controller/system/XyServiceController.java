package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.GMUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.XyRole;
import com.ruoyi.system.service.IXyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 西游全服Controller
 *
 * @author ruoyi
 * @date 2020-12-04
 */
@RestController
@RequestMapping("/system/xy_server")
public class XyServiceController extends BaseController {
    @Autowired
    private IXyRoleService xyRoleService;

    @Autowired
    private GMUtils gmUtils;


    /**
     * 发布全服公告
     */
    @Log(title = "西游服务器", businessType = BusinessType.SEND_NOTICE)
    @PutMapping("/sendNotice/{notice}")
    public AjaxResult sendNotice(@PathVariable String notice) {
        gmUtils.sendNotice(1, notice);
        return toAjax(200);
    }


    /**
     * 发布全服补偿
     */
    @Log(title = "西游服务器", businessType = BusinessType.SEND_ALL_GIFT)
    @PutMapping("/sendAllGift/{itemId}/{num}/{title}/{text}/{itemtype}")
    //Integer itemId, Integer num, String title, String text, Integer itemtype
    public AjaxResult sendAllGift(@PathVariable Integer itemId, @PathVariable Integer num, @PathVariable String title, @PathVariable String text, @PathVariable Integer itemtype) {
        gmUtils.OnlineMail(1, itemId, num, title, text, itemtype);
        return toAjax(200);
    }


    /**
     * 发布全服补偿
     */
    @Log(title = "西游服务器", businessType = BusinessType.SEND_ITEM)
    @PutMapping("/sendItems/{playerId}/{itemId}/{num}/{itemtype}")
    //Integer itemId, Integer num, String title, String text, Integer itemtype
    public AjaxResult sendItems(@PathVariable String playerId,@PathVariable Integer itemId, @PathVariable Integer num, @PathVariable Integer itemtype) {
        gmUtils.sendItems(1, playerId,itemId, num, itemtype);
        return toAjax(200);
    }





}

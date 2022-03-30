package com.hohai.component.web.controller;

import com.hohai.component.common.core.Log;
import com.hohai.component.common.core.base.BaseController;
import com.hohai.component.common.core.model.AjaxResult;
import com.hohai.component.common.enums.BusinessType;
import com.hohai.component.common.util.excel.ExcelUtil;
import com.hohai.component.common.util.page.TableDataInfo;
import com.hohai.component.system.entity.SysLoginInfo;
import com.hohai.component.system.service.SysLoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Lynn
 * @date 2022/3/28 10:57
 * @description 系统访问记录
 **/

@RestController
@RequestMapping("monitor/loginInfo")
public class SysLoginInfoController extends BaseController {

    @Autowired
    private SysLoginInfoService loginInfoService;

    @GetMapping("/list")
    public TableDataInfo list(SysLoginInfo loginInfo)
    {
        startPage();
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLoginInfo loginInfo)
    {
        List<SysLoginInfo> list = loginInfoService.selectLoginInfoList(loginInfo);
        ExcelUtil<SysLoginInfo> util = new ExcelUtil<SysLoginInfo>(SysLoginInfo.class);
        util.exportExcel(response, list, "登录日志");
    }

    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds)
    {
        return toAjax(loginInfoService.deleteLoginInfoByIds(infoIds));
    }

    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        loginInfoService.cleanLoginInfo();
        return AjaxResult.success();
    }
}

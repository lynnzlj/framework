package com.hohai.component.web.controller;

import com.hohai.component.common.constant.UserConstants;
import com.hohai.component.common.core.Log;
import com.hohai.component.common.core.base.BaseController;
import com.hohai.component.common.core.model.AjaxResult;
import com.hohai.component.common.enums.BusinessType;
import com.hohai.component.common.util.SecurityUtils;
import com.hohai.component.common.util.StringUtils;
import com.hohai.component.system.entity.SysMenu;
import com.hohai.component.system.entity.SysUser;
import com.hohai.component.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lynn
 * @date 2022/3/22 8:51
 * @description 菜单管理
 **/

@Api(tags = {"菜单管理接口"})
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService menuService;

    @ApiOperation(value = "新增菜单", notes = "isLink属性默认非外链为0，status属性默认正常为0")
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsLink()) && !StringUtils.isHttp(menu.getUrl()))
        {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(getUsername());
        return toAjax(menuService.insertMenu(menu));
    }

    @ApiOperation(value = "修改菜单", notes = "isLink属性默认非外链为0，status属性默认正常为0")
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsLink()) && !StringUtils.isHttp(menu.getUrl()))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getMenuPid()))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUsername());
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @ApiOperation( value = "删除菜单")
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{menuId}")
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.hasChildByMenuId(menuId))
        {
            return AjaxResult.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId))
        {
            return AjaxResult.error("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }


    @ApiOperation(value = "获取菜单列表" )
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysMenu> menus = menuService.selectMenuList(menu, user.getUserId());
        return AjaxResult.success(menus);
    }


    @ApiOperation(value = "根据菜单编号获取详细信息")
    @GetMapping(value = "/{menuId}")
    public AjaxResult getInfo(@PathVariable Long menuId)
    {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    @ApiOperation(value = "获取菜单下拉树列表")
    @GetMapping("/treeSelect")
    public AjaxResult treesSelect(SysMenu menu)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysMenu> menus = menuService.selectMenuList(menu, user.getUserId());
        return AjaxResult.success(menuService.buildMenuTreeSelect(menus));
    }

    @ApiOperation(value = "加载对应角色菜单列表树")
    @GetMapping(value = "/roleMenuTreeSelect/{roleId}")
    public AjaxResult roleMenuTreeSelect(@PathVariable("roleId") Long roleId)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysMenu> menus = menuService.selectMenuList(user.getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

}

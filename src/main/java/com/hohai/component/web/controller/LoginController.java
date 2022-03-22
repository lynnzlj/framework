package com.hohai.component.web.controller;

import com.hohai.component.common.constant.Constants;
import com.hohai.component.common.core.model.AjaxResult;
import com.hohai.component.common.util.SecurityUtils;
import com.hohai.component.system.entity.SysUser;
import com.hohai.component.web.service.LoginService;
import com.hohai.component.web.service.PermissionService;
import com.hohai.component.web.vo.LoginBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author Lynn
 * @date 2022/3/11 14:34
 * @description 登录验证接口
 **/

@Api(tags = {"登录验证接口"})
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation(value = "登录方法", notes = "使用用户名密码登录，登录后获取token")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }


}

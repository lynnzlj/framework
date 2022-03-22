package com.hohai.component.web.service;

import com.hohai.component.common.constant.UserConstants;
import com.hohai.component.common.core.model.AjaxResult;
import com.hohai.component.common.util.SecurityUtils;
import com.hohai.component.common.util.StringUtils;
import com.hohai.component.system.entity.SysUser;
import com.hohai.component.system.service.SysUserService;
import com.hohai.component.web.vo.RegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lynn
 * @date 2022/3/14 10:38
 * @description 注册验证方法
 **/

@Component
public class RegisterService {

    @Autowired
    private SysUserService userService;


    /**
     * 注册
     */
    public AjaxResult register(RegisterBody registerBody)
    {
        String msg, username = registerBody.getUsername(), password = registerBody.getPassword();
        AjaxResult ajax;

        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
            ajax = AjaxResult.error(msg);
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
            ajax = AjaxResult.error(msg);
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
            ajax = AjaxResult.error(msg);
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
            ajax = AjaxResult.error(msg);
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
            ajax = AjaxResult.error(msg);
        }
        else
        {
            SysUser sysUser = new SysUser();
            sysUser.setUserName(username);
            sysUser.setRealName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
                ajax = AjaxResult.error(msg);
            }
            else
            {
                msg = "注册成功";
                ajax = AjaxResult.success(msg);
            }
        }
        return ajax;
    }
}

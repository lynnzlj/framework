package com.hohai.component.web.controller;

import com.hohai.component.common.core.base.BaseController;
import com.hohai.component.common.core.model.AjaxResult;
import com.hohai.component.web.service.RegisterService;
import com.hohai.component.web.vo.RegisterBody;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lynn
 * @date 2022/3/14 10:37
 * @description 注册验证
 **/

//@Api(tags = {"注册接口"})
@RestController
public class RegisterController extends BaseController {

    @Autowired
    private RegisterService registerService;

    @ApiOperation(value = "用户注册", notes = "输入用户名密码进行注册")
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        return registerService.register(user);
    }
}

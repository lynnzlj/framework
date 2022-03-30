package com.hohai.component.common.exception.user;

/**
 * @author Lynn
 * @date 2022/3/29 11:07
 * @description 用户密码不正确或不符合规范异常类
 **/


public class UserPasswordNotMatchException extends UserException{

    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException()
    {
        super("用户名密码不匹配", null);
    }
}

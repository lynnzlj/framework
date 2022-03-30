package com.hohai.component.common.exception.user;

import com.hohai.component.common.exception.BaseException;

/**
 * @author Lynn
 * @date 2022/3/29 11:06
 * @description 用户信息异常类
 **/


public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}

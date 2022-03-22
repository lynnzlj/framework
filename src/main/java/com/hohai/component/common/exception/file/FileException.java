package com.hohai.component.common.exception.file;

import com.hohai.component.common.exception.BaseException;

/**
 * @author Lynn
 * @date 2022/3/18 9:51
 * @description  文件信息异常类
 **/


public class FileException extends BaseException{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }
}

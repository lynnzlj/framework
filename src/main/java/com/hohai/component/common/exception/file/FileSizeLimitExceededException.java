package com.hohai.component.common.exception.file;

/**
 * @author Lynn
 * @date 2022/3/18 10:01
 * @description 文件名大小限制异常类
 **/


public class FileSizeLimitExceededException extends FileException{

    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize)
    {
        super("upload.exceed.maxSize", new Object[] { defaultMaxSize });
    }
}

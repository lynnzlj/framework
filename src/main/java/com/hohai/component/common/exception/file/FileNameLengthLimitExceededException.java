package com.hohai.component.common.exception.file;

/**
 * @author Lynn
 * @date 2022/3/18 10:00
 * @description 文件名称超长限制异常类
 **/


public class FileNameLengthLimitExceededException extends FileException{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}

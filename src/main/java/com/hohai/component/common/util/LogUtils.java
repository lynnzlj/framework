package com.hohai.component.common.util;

/**
 * @author Lynn
 * @date 2022/3/29 10:07
 * @description 处理并记录日志文件
 **/


public class LogUtils {
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}

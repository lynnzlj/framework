package com.hohai.component.manager;

import com.hohai.component.common.constant.Constants;
import com.hohai.component.common.util.LogUtils;
import com.hohai.component.common.util.ServletUtils;
import com.hohai.component.common.util.SpringUtils;
import com.hohai.component.common.util.StringUtils;
import com.hohai.component.common.util.ip.AddressUtils;
import com.hohai.component.common.util.ip.IpUtils;
import com.hohai.component.system.entity.SysLoginInfo;
import com.hohai.component.system.entity.SysOperLog;
import com.hohai.component.system.service.SysLoginInfoService;
import com.hohai.component.system.service.SysOperLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @author Lynn
 * @date 2022/3/29 9:56
 * @description 异步工厂（产生任务用）
 **/


public class AsyncFactory {

    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLoginInfo(final String username, final String status, final String message,
                                             final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIp(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginInfo loginInfo = new SysLoginInfo();
                loginInfo.setOnlineUserName(username);
                loginInfo.setLoginIpaddr(ip);
                loginInfo.setLoginLocation(address);
                loginInfo.setLoginBrowser(browser);
                loginInfo.setLoginOs(os);
                loginInfo.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT))
                {
                    loginInfo.setOnlineStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    loginInfo.setOnlineStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLoginInfoService.class).insertLoginInfo(loginInfo);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIp(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogService.class).insertOperLog(operLog);
            }
        };
    }
}

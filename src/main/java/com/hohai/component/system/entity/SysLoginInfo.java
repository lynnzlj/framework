package com.hohai.component.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hohai.component.common.annotation.Excel;
import com.hohai.component.common.annotation.Excel.ColumnType;
import com.hohai.component.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lynn
 * @date 2022/3/28 11:03
 * @description 系统访问记录表
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class SysLoginInfo extends BaseEntity {

    /** ID */
    @Excel(name = "序号", cellType = ColumnType.NUMERIC)
    private Long onlineId;

    /** 用户Id */
    @Excel(name = "用户Id")
    private Long onlineUserId;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String onlineUserName;

    /** 登录状态 0成功 1失败 */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private String onlineStatus;

    /** 登录IP地址 */
    @Excel(name = "登录地址")
    private String loginIpaddr;

    /** 登录地点 */
    @Excel(name = "登录地点")
    private String loginLocation;

    /** 浏览器类型 */
    @Excel(name = "浏览器")
    private String loginBrowser;

    /** 操作系统 */
    @Excel(name = "操作系统")
    private String loginOs;

    /** 提示消息 */
    @Excel(name = "提示消息")
    private String msg;

    /** 访问时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /** 登出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}

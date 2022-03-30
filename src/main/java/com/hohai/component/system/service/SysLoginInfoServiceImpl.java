package com.hohai.component.system.service;

import com.hohai.component.system.entity.SysLoginInfo;
import com.hohai.component.system.mapper.SysLoginInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lynn
 * @date 2022/3/28 11:10
 * @description 系统访问记录 服务层处理
 **/

@Service
public class SysLoginInfoServiceImpl implements SysLoginInfoService{
    @Autowired
    private SysLoginInfoMapper loginInfoMapper;

    /**
     * 新增系统登录日志
     *
     * @param loginInfo 访问日志对象
     */
    @Override
    public void insertLoginInfo(SysLoginInfo loginInfo)
    {
        loginInfoMapper.insertLoginInfo(loginInfo);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param loginInfo 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public List<SysLoginInfo> selectLoginInfoList(SysLoginInfo loginInfo)
    {
        return loginInfoMapper.selectLoginInfoList(loginInfo);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     */
    @Override
    public int deleteLoginInfoByIds(Long[] infoIds)
    {
        return loginInfoMapper.deleteLoginInfoByIds(infoIds);
    }

    /**
     * 清空系统登录日志
     */
    @Override
    public void cleanLoginInfo()
    {
        loginInfoMapper.cleanLoginInfo();
    }
}

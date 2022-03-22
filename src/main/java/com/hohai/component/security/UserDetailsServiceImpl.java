package com.hohai.component.security;

import com.hohai.component.system.entity.SysUser;
import com.hohai.component.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Lynn
 * @date 2022/3/10 13:51
 * @description 自定义用户认证逻辑，继承SpringSecurity的UserDetailsService类
 **/

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userService.selectUserByUserName(username);
        return new LoginUser(user);
    }

}

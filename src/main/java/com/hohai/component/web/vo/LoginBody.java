package com.hohai.component.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Lynn
 * @date 2022/3/11 15:33
 * @description 用户登录对象
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 唯一标识
     */
    private String uuid = "";
}

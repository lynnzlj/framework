package com.hohai.component.system.entity;

import lombok.Data;

/**
 * @author Lynn
 * @date 2022/3/10 11:16
 * @description 角色和用户关联类
 **/

@Data
public class SysUserRole {

    /** 角色ID */
    private Long roleId;

    /** 用户ID */
    private Long userId;

}

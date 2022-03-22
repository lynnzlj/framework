package com.hohai.component.system.entity;

import lombok.Data;

/**
 * @author Lynn
 * @date 2022/3/10 11:17
 * @description 角色和菜单关联类
 **/

@Data
public class SysRoleMenu {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}

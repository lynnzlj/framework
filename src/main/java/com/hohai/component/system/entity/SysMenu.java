package com.hohai.component.system.entity;

import com.hohai.component.common.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lynn
 * @date 2022/3/10 10:38
 * @description 菜单权限类 sys_menu
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long menuId;

    /** 父菜单ID */
    private Long menuPid;

    /** 菜单名称 */
    private String menuName;

    /** 路由地址 */
    private String url;

    /** 显示顺序 */
    private String sort;

    /** 是否为外链（0否 1是） */
    private String isLink;

    /** 菜单图标 */
    private String icon;

    /** 类型（M目录 C菜单 F按钮） */
    private String menuType;

    /** 菜单状态（0显示 1隐藏） */
    private String status;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();
}

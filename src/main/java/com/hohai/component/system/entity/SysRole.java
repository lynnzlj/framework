package com.hohai.component.system.entity;

import com.hohai.component.common.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Lynn
 * @date 2022/3/10 10:06
 * @description 角色类 sys_role
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long roleId;

    /** 父级角色ID*/
    private Long rolePid;

    /** 角色名称 */
    private String roleName;

    /** 角色权限字符 */
    private String roleKey;

    /** 角色排序 */
    private String sort;

    /** 角色状态（0正常 1停用 ） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 菜单组 */
    private Long[] menuIds;

    public boolean isAdmin()
    {
        return isAdmin(this.roleId);
    }

    public static boolean isAdmin(Long roleId)
    {
        return roleId != null && 1L == roleId;
    }

    public SysRole(Long roleId)
    {
        this.roleId = roleId;
    }

}

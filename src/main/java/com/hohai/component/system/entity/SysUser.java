package com.hohai.component.system.entity;

import com.hohai.component.common.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Lynn
 * @date 2022/3/10 9:57
 * @description 用户对象 sys_user
 **/

@ApiModel(value = "SysUser", description = "用户对象")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {

    /** 用户ID */
    @ApiModelProperty(value = "用户Id")
    private Long userId;

    /** 登录用户名 */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /** 密码 */
    @ApiModelProperty(value = "登录密码")
    private String password;

    /** 用户名字 */
    @ApiModelProperty(value = "用户昵称")
    private String realName;

    /** 用户性别 */
    @ApiModelProperty(value = "用户性别")
    private String sex;

    /** 手机号码 */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /** 用户邮箱 */
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    /** 用户头像 */
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    /** 帐号状态（0正常 1停用 2删除） */
    @ApiModelProperty(value = "账号状态（0正常 1停用 2删除）")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    private String delFlag;

    /** 部门ID */
    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    /** 角色ID */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /** 角色对象 */
    @ApiModelProperty(value = "角色组")
    private List<SysRole> roles;

    /** 角色组 */
    @ApiModelProperty(value = "角色组ID")
    private Long[] roleIds;

    public SysUser(Long userId) {
    }

    @ApiModelProperty(value = "是否管理员")
    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

}


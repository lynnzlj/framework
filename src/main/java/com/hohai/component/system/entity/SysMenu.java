package com.hohai.component.system.entity;

import com.hohai.component.common.core.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lynn
 * @date 2022/3/10 10:38
 * @description 菜单权限类 sys_menu
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜单实体类")
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

    /** 权限字符串 */
    private String perms;

    /** 是否为外链（0是 1否） */
    @ApiModelProperty(value = "是否为外链(0是 1否)")
    private String isLink;

    /** 菜单图标 */
    private String icon;

    /** 类型（M目录 C菜单 F按钮） */
    @ApiModelProperty(value = "类型(M目录 C菜单 F按钮)")
    private String menuType;

    /** 菜单状态（0显示 1隐藏） */
    @ApiModelProperty(value = "菜单状态(0显示 1隐藏)")
    private String status;

    /** 子菜单 */
    private List<SysMenu> children = new ArrayList<SysMenu>();

    /** 请求参数 */
    private Map<String, Object> params;

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    public String getPerms()
    {
        return perms;
    }

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }
}

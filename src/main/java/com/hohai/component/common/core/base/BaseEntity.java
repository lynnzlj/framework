package com.hohai.component.common.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Lynn
 * @date 2022/3/10 9:52
 * @description entity 基类
 **/

@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty(example = "2022-02-22 02:22:22")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty( example = "2022-02-22 02:22:22")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

//    /** 请求参数 */
//    private Map<String, Object> params;
//
//    public Map<String, Object> getParams()
//    {
//        if (params == null)
//        {
//            params = new HashMap<>();
//        }
//        return params;
//    }
}

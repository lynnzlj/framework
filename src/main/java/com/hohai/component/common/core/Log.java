package com.hohai.component.common.core;

import com.hohai.component.common.enums.BusinessType;
import com.hohai.component.common.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author Lynn
 * @date 2022/3/17 15:20
 * @description 自定义操作日志记录注解
 **/

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}

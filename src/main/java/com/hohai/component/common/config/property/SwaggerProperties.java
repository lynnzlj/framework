package com.hohai.component.common.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lynn
 * @date 2022/3/11 14:55
 * @description swagger配置类
 **/


@Component
@ConfigurationProperties("swagger")
public class SwaggerProperties {

    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     * 对应application.yml中的swagger.enable
     */
    private Boolean enable;

    /**
     * 项目应用名
     * 对应application.yml中的swagger.application-name
     */
    private String applicationName;

    /**
     * 项目版本信息
     * 对应application.yml中的swagger.application.version
     */
    private String applicationVersion;

    /**
     * 项目描述信息
     * 对应application.yml中的swagger.application-description
     */
    private String applicationDescription;

    /**
     * 接口调试地址
     * 对应application.yml中的swagger.try-host
     */
    private String tryHost;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getTryHost() {
        return tryHost;
    }

    public void setTryHost(String tryHost) {
        this.tryHost = tryHost;
    }

}

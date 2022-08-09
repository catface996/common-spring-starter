package com.catface.common.api.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author by catface
 * @date 2020/06/26
 */
@Data
@ConfigurationProperties(prefix = "http.config")
public class HttpApiProperties {

    /**
     * 日志打印配置
     */
    private Boolean logEnable = false;

    /**
     * 日志响应结果配置
     */
    private Boolean emptyResultEnable = false;

}

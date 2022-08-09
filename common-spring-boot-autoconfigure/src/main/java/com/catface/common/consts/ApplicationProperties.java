package com.catface.common.consts;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * spring 应用属性
 *
 * @author lzs
 * @date 2020年09月04日 9:52 上午
 */
@Data
@ConfigurationProperties(prefix = "spring")
public class ApplicationProperties {

    /**
     * 应用名称
     */
    @Value("${spring.application.name:unknown}")
    private String app;

    /**
     * 环境信息
     */
    @Value("${spring.profiles.active:local}")
    private String env;

}

package com.catface.common.api.autoconfig;

import javax.servlet.Filter;

import com.catface.common.api.filter.ApiAccessFilter;
import com.catface.common.api.properties.HttpApiProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author catface
 * @date 2019-05-15
 */
@Configuration
@ConditionalOnClass(Filter.class)
@EnableConfigurationProperties(HttpApiProperties.class)
public class ApiAccessFilterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "http.config", value = "log-enable", havingValue = "true")
    public ApiAccessFilter apiAccessFilter(HttpApiProperties httpApiProperties) {
        return new ApiAccessFilter(httpApiProperties);
    }

}


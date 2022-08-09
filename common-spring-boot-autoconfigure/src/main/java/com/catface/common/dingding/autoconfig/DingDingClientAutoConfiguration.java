package com.catface.common.dingding.autoconfig;

import com.catface.common.dingding.client.DingDingClient;
import com.catface.common.dingding.client.DingDingClientImpl;
import com.catface.common.rest.autoconfig.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

/**
 * DingDingClient自动配置类
 *
 * @author lzs
 * @date 2020年07月29日 下午9:30
 */
@Configuration
@Import(RestTemplateAutoConfiguration.class)
@ConditionalOnClass(RestTemplate.class)
public class DingDingClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(RestTemplate.class)
    public DingDingClient dingDingClient(RestTemplate restTemplate) {
        return new DingDingClientImpl(restTemplate);
    }

}

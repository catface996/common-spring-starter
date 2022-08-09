package com.catface.common.mvc.autoconfig;

import com.catface.common.mvc.configurer.LongToStringConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lzs
 * @date 2020年09月04日 2:29 下午
 */
@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
public class LongToStringAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LongToStringConfigurer longToStringConfigurer() {
        return new LongToStringConfigurer();
    }

}

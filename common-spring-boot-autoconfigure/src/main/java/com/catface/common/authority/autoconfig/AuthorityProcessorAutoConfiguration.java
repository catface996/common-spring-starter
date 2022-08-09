package com.catface.common.authority.autoconfig;

import com.catface.common.authority.processor.AuthorityProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lzs
 * @date 2020年09月04日 2:29 下午
 */
@Configuration
@ConditionalOnClass({BeanPostProcessor.class, DispatcherServlet.class, WebMvcConfigurer.class})
public class AuthorityProcessorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuthorityProcessor customAuthorityProcessor() {
        return new AuthorityProcessor();
    }

}

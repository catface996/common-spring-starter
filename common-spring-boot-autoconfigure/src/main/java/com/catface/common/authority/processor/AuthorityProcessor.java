package com.catface.common.authority.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author catface
 * @date 2019-04-04
 */
@Slf4j
public class AuthorityProcessor implements BeanPostProcessor {

    public static final List<PubUrl> PUB_URL_LIST = new ArrayList<>();

    private static final Integer URL_SEGMENT = 4;

    private static final String CONTROLLER_REGEX = "Controller";

    private static final String URL_SPLIT_SYMBOL = "/";

    private static final String PUBLIC_AUTHORITY_REGEX = "/public";

    public AuthorityProcessor() {
        super();
        log.debug("public authority scan start!");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());

        for (Method method : methods) {
            PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
            if (postMapping == null) {
                continue;
            }
            ApiOperation apiOperation = AnnotationUtils.findAnnotation(method, ApiOperation.class);
            // 扫描权限
            processAuthority(beanName, postMapping, apiOperation);

        }

        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }

    private void processAuthority(String beanName, PostMapping postMapping, ApiOperation apiOperation) {
        PubUrl pubUrl = new PubUrl();
        if (postMapping != null && beanName.contains(CONTROLLER_REGEX)) {
            for (int i = 0; i < postMapping.value().length; i++) {
                String url = postMapping.value()[i];

                if (StringUtils.isEmpty(url)) {
                    continue;
                }

                if (!url.startsWith(PUBLIC_AUTHORITY_REGEX)) {
                    continue;
                }

                String[] urlPattern = url.split(URL_SPLIT_SYMBOL);

                if (urlPattern.length != URL_SEGMENT) {
                    log.warn("url格式不合规,请检查!url:{}", url);
                }

                pubUrl.setUrl(url);
                if (apiOperation != null) {
                    pubUrl.setDescription(apiOperation.value());
                }
                PUB_URL_LIST.add(pubUrl);
                log.debug("权限点:{}", pubUrl);
            }
        }
    }

}

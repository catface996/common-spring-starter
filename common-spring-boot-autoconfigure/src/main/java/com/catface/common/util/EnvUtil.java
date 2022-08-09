package com.catface.common.util;

import java.net.Inet4Address;
import java.net.InetAddress;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author by catface
 * @date 2021/7/30 5:51 下午
 */
@Slf4j
@Component
public class EnvUtil {

    private static String env;
    private static String port;
    @Autowired
    private Environment environment;

    public static String getEnv() {
        return env;
    }

    public static String getPort() {
        return port;
    }

    public static String getSwaggerUrl() {
        return "http://" + getIp() + ":" + getPort() + "/swagger-ui.html";
    }

    public static String getDocUrl() {
        return "http://" + getIp() + ":" + getPort() + "/doc.html";
    }

    public static String getIp() {
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            return ip4.getHostAddress();
        } catch (Exception e) {
            log.error("获取IP地址异常!", e);
            return null;
        }
    }

    @PostConstruct
    public void init() {
        env = environment.getProperty("spring.profiles.active");
        port = environment.getProperty("server.port");
    }
}

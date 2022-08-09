package com.catface.common.thread.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 线程池配置
 *
 * @author catface
 * @date 2020/5/22
 */
@Data
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolProperties {

    /**
     * 核心线程数
     **/
    private Integer corePoolSize = 10;

    /**
     * 最大线程数
     **/
    private Integer maxPoolSize = 20;

    /**
     * 存活时间
     **/
    private Integer keepAliveSeconds = 600;

    /**
     * 队列容量
     **/
    private Integer queueCapacity = 200;

    /**
     * 线程名前缀
     */
    private String threadNamePrefix = "async-thread-";

}

# common-spring-boot

一个开箱即用的基础应用配置起步依赖项目

- 默认开启配置异步线程池，支持配置线程池参数
- 默认开启 DingDingClient 配置，方便应用快速接入钉钉机器人推送消息通知
- 默认开启 RestTemplate，支持开启使用 HttpClient 作为 RestTemplate 连接工具，支持配置 Http 连接池参数
- 默认配置 FastJsonHttpMessageConverter Long => String 类型转换
- 支持开启应用启动完成，自动发起一次健康检查并通知多个钉钉群机器人消息
- 支持开启 Http 接口访问日志，自动扫描检查 Post 方法 Public Url 约定格式
- 未完待续

## 使用说明

#### 引入依赖

```xml
<dependency>
    <groupId>com.catface.common</groupId>
    <artifactId>common-spring-boot-starter</artifactId>
    <version>1.0.2-SNAPSHOT</version>
</dependency>
```

#### 应用配置

```c
## 服务配置
spring.application.name=app
spring.profiles.active=dev

## http连接池配置
http-pool.max-total=20
http-pool.default-max-per-route=10
http-pool.connect-timeout=1000
http-pool.connection-request-timeout=3000
http-pool.socket-timeout=5000
http-pool.validate-after-inactivity=7000
http-pool.async=false

## 异步线程池配置
thread.pool.core-pool-size=10
thread.pool.max-pool-size=20
thread.pool.keep-alive-seconds=600
thread.pool.queue-capacity=200
thread.pool.thread-name-prefix=async-thread-

## http api config
http.config.log-enable=true
http.config.empty-result-enable=false


## 应用启动钉钉通知配置
notice.start-notice-urls[0]=https://oapi.dingtalk.com/robot/send?access_token=362f156dab8e31f8e638f16e50d3649ccbdc37c372d7e5eeb513d88bc83fe287

```


#### 使用方式

使用 RestTemplate

```java
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        // 
    }

}
```

使用 DingDingClient

```java
public class DingDingClientTest {

    @Autowired
    private DingDingClient dingDingClient;

    @Test
    public void testRestTemplate() {
        // 
    }

}
```

@Async异步方法实现

```java
public interface HelloService {

    /**
     * 异步sayHello
     *
     * @param name caller name
     */
    void sayHelloAsync(String name);

}

public class HelloServiceImpl implements HelloService {

    /**
     * 异步sayHello
     *
     * @param name caller name
     */
    @Async
    @Override
    public void sayHelloAsync(String name) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            log.error("say hello exception", e);
        }
        log.info("service say hello " + name + "!");
    }

}
```

获取异步线程池

```java
public class ExecutorTest {

    /**
     * 注入异步线程池
     */
    @Autowired
    private Executor executor;

    @Test
    public void testExecute() {
        // 
    }

}
```

关于异常传递

* A系统调用B,C,...等多个系统额RPC,产生多个异常时,无法聚合异常.
* A系统调用B系统,B调用C,B对C调用做了熔断保护,对A调用B来讲,未发生异常.

总和考虑以上两种场景,暂时不支持异常传递,后续会考虑使用链路追踪来排查异常.

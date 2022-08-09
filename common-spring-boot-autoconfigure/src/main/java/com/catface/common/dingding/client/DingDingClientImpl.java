package com.catface.common.dingding.client;

import com.catface.common.dingding.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author by catface
 * @date 2020/08/01
 */
@Slf4j
public class DingDingClientImpl implements DingDingClient {

    private RestTemplate restTemplate;

    public DingDingClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 发送消息
     *
     * @param url     钉钉url
     * @param message 消息
     */
    @Override
    public void sendMessage(String url, Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> request = new HttpEntity<>(message.toJsonString(), headers);
        ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
        String body = postForEntity.getBody();
        log.debug("发送结果:{}", body);
    }
}

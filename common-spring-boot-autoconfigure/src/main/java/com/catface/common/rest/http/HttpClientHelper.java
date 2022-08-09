package com.catface.common.rest.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

/**
 * HttpClientHelper
 *
 * @author lzs
 * @date 2020年07月29日 下午9:33
 */
@Slf4j
public class HttpClientHelper {

    public SSLContext getSslContext() {
        // 在调用SSL之前需要重写验证方法，取消检测SSL
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] xcs, String str) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] xcs, String str) {
            }
        };
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] {trustManager}, null);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("创建 SSL 失败", e);
        }
        assert ctx != null;
        return ctx;
    }

}

package com.catface.common.api.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

/**
 * @author weihainan. 10  * @since 0.1 created on 2017/2/23. 11
 */
@Slf4j
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

  private byte[] body;

  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
    try {
      body = StreamUtils.copyToByteArray(request.getInputStream());
    } catch (Exception e) {
      log.error("复制request body的stream 异常", e);
    }
  }

  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request, Object body) {
    super(request);
    try {
      this.body = JSONObject.toJSONString(body).getBytes();
    } catch (Exception e) {
      log.error("重新设置request body 异常", e);
    }
  }

  public BodyReaderHttpServletRequestWrapper(HttpServletRequest request, JSONObject body) {
    super(request);
    try {
      this.body = body.toJSONString().getBytes();
    } catch (Exception e) {
      log.error("重新设置request body 异常", e);
    }
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() {

    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);

    return new ServletInputStream() {

      @Override
      public int read() {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

    };
  }

  public String getBody() {
    if (body == null || body.length <= 0) {
      return "{}";
    }
    return new String(body);
  }

  public JSONObject getJsonBody() {
    return JSONObject.parseObject(getBody().replaceAll("\n", ""));
  }
}

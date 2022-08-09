package com.catface.common.health;

import com.catface.common.health.response.StatusResponse;
import com.catface.common.model.JsonResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

import lombok.SneakyThrows;

/**
 * @author by catface
 * @date 2021/1/18 9:02 下午
 */
@RestController
public class HealthController {

	@Value("${spring.application.name}")
	private String appName;

	@Value("${spring.profiles.active}")
	private String env;

	@SneakyThrows
	@RequestMapping(value = "/status", method = {RequestMethod.POST, RequestMethod.GET})
	public JsonResult<StatusResponse> status() {
		String ip = InetAddress.getLocalHost().getHostAddress();
		StatusResponse response = new StatusResponse();
		response.setAppName(appName);
		response.setEvn(env);
		response.setIp(ip);
		return JsonResult.success(response);
	}
}

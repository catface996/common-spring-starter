package com.catface.common.health.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author by catface
 * @date 2021/1/18 9:10 下午
 */
@Data
public class StatusResponse {

	@ApiModelProperty(value = "应用名称")
	private String appName;

	@ApiModelProperty(value = "IP地址")
	private String ip;

	@ApiModelProperty(value = "环境")
	private String evn;
}

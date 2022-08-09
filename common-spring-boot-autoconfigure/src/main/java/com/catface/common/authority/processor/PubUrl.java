package com.catface.common.authority.processor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author catface
 * @date 2019-04-04
 */
@Data
@ApiModel("public权限点模型")
public class PubUrl {

  @ApiModelProperty(value = "url", required = true)
  private String url;

  @ApiModelProperty(value = "描述", required = true)
  private String description;

}

package com.cube.pojo.dto;

import java.util.List;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
@Getter
@Setter
@ToString
@Builder
@ApiModel("短信发送请求参数实体")
public class SmsSendDTO {

	@ApiModelProperty("短信接收人")
	@Size(min = 1, message = "短信接收人列表不能为空")
	private List<String> phones;

	@ApiModelProperty("短信内容")
	@Size(min = 1, message = "短信内容不能为空")
	private String cont;

}

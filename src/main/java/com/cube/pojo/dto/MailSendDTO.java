package com.cube.pojo.dto;

import java.util.List;

import javax.validation.Valid;
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
@ApiModel("邮件发送请求参数实体")
public class MailSendDTO {

	/**
	 * 收件人 嵌套验证必须用@Valid
	 */
	@ApiModelProperty("邮件收件人列表")
	@Size(min = 1, message = "收件人列表不能为空", groups = { MailText.class, MailTemplate.class })
	private List<String> toList;

	@ApiModelProperty("邮件主题")
	@Valid
	@Size(min = 1, max = 50, message = "邮件主题参数有误", groups = { MailText.class, MailTemplate.class })
	private String subject;

	/**
	 * 文本邮件内容
	 */
	@ApiModelProperty("文本邮件内容")
	@Size(min = 1, max = 50, message = "邮件内容参数有误", groups = { MailText.class })
	private String content;

	/**
	 * 级联验证
	 */
	@Valid
	@ApiModelProperty("模板邮件内容的参数实体")
	private MailTemplateDTO templateDto;

}

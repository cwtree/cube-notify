package com.cube.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cube.common.GlobalVar;
import com.cube.pojo.MyResp;
import com.cube.pojo.Resp;
import com.cube.pojo.dto.MailSendDTO;
import com.cube.pojo.dto.MailTemplate;
import com.cube.pojo.dto.MailText;
import com.cube.pojo.dto.SmsSendDTO;
import com.cube.service.MailService;
import com.cube.service.MySmsService;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ReUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
@RestController
@Slf4j
@RequestMapping("/notify")
@Api("消息通知")
public class NotifyController {

	@Resource
	private MailService mailService;

	@Resource
	private MySmsService mySmsService;

	/**
	 * 发送文本邮件
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param dto
	 * @return
	 */
	@ApiOperation("发送文本邮件")
	@PostMapping("/mail/sendText")
	@ResponseBody
	public MyResp sendText(@RequestBody @Validated(MailText.class) MailSendDTO dto) {
		List<String> toList = dto.getToList();
		for (String mail : toList) {
			if (!ReUtil.isMatch(GlobalVar.MAIL_PATTERN, mail)) {
				return MyResp.result(Resp.PARAM_MAIL_ERROR);
			}
		}
		try {
			mailService.sendTextMail(dto.getToList(), dto.getSubject(), dto.getContent());
			return MyResp.result(Resp.SUCCESS);
		} catch (Exception e) {
			log.error("文本邮件发送异常", e);
			return MyResp.result(Resp.MAIL_SEND_ERROR, "", ExceptionUtil.getMessage(e));
		}
	}

	/**
	 * 发送文本邮件
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param dto
	 * @return
	 */
	@ApiOperation("发送模板邮件")
	@PostMapping("/mail/sendTemplate")
	@ResponseBody
	public MyResp sendTemplate(@RequestBody @Validated(MailTemplate.class) MailSendDTO dto) {
		List<String> toList = dto.getToList();
		for (String mail : toList) {
			if (!ReUtil.isMatch(GlobalVar.MAIL_PATTERN, mail)) {
				return MyResp.result(Resp.PARAM_MAIL_ERROR);
			}
		}
		try {
			mailService.sendTemplateMail(toList, dto.getSubject(), dto.getTemplateDto());
			return MyResp.result(Resp.SUCCESS);
		} catch (Exception e) {
			log.error("模板邮件发送异常", e);
			return MyResp.result(Resp.MAIL_SEND_ERROR, "", ExceptionUtil.getMessage(e));
		}
	}

	@ApiOperation("短信发送")
	@PostMapping("/sms/send")
	@ResponseBody
	public MyResp sendSms(@RequestBody @Validated SmsSendDTO dto) {
		List<String> toList = dto.getPhones();
		for (String mail : toList) {
			if (!ReUtil.isMatch(GlobalVar.PHONE_PATTERN, mail)) {
				return MyResp.result(Resp.PARAM_PHONE_ERROR);
			}
		}
		try {
			mySmsService.sendSms(toList, dto.getCont());
			return MyResp.result(Resp.SUCCESS);
		} catch (Exception e) {
			log.error("短信发送异常", e);
			return MyResp.result(Resp.SMS_SEND_ERROR, "", ExceptionUtil.getMessage(e));
		}
	}

}

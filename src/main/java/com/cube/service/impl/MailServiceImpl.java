package com.cube.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cube.config.MyConfig;
import com.cube.pojo.dto.MailTemplateDTO;
import com.cube.service.MailService;

import cn.hutool.core.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Resource
	private JavaMailSender mailSender;
	@Resource
	private TemplateEngine templateEngine;
	@Resource
	private MyConfig myConfig;
	@Resource
	private Environment environment;

	@Async("mailExecutor")
	@Override
	public void sendTextMail(List<String> toList, String subject, String cont) throws Exception {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String[] toMails = ArrayUtil.toArray(toList, String.class);
		// 密送邮件
		helper.setBcc(toMails);
		helper.setCc(environment.getProperty("spring.mail.username"));
		helper.setSubject(subject);
		helper.setText(cont, true);
		helper.setFrom(environment.getProperty("spring.mail.username"));
		mailSender.send(message);
		if (log.isInfoEnabled()) {
			log.info("邮件发送成功 收件人 {} 主题 {} 数据 {}", toMails, subject, cont);
		}
	}

	@Async("mailExecutor")
	@Override
	public void sendTemplateMail(List<String> toList, String subject, MailTemplateDTO dto) throws Exception {
		// TODO Auto-generated method stub
		Context ctx = new Context();
		// 设置变量
		ctx.setVariable("bizName", dto.getBizName());
		ctx.setVariable("bizDesc", dto.getBizDesc());
		String emailText = templateEngine.process("mail", ctx);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		String[] toMails = ArrayUtil.toArray(toList, String.class);
		// 密送邮件
		helper.setBcc(toMails);
		helper.setCc(environment.getProperty("spring.mail.username"));
		helper.setSubject(subject);
		helper.setText(emailText, true);
		helper.setFrom(environment.getProperty("spring.mail.username"));
		mailSender.send(message);
		if (log.isInfoEnabled()) {
			log.info("邮件发送成功 收件人 {} 主题 {} 数据 {}", toMails, subject, emailText);
		}
	}

}

package com.cube.service;

import java.util.List;

import com.cube.pojo.dto.MailTemplateDTO;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
public interface MailService {

	/**
	 * 发送文本邮件
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param toList
	 * @param subject
	 * @param cont
	 * @throws Exception
	 */
	void sendTextMail(List<String> toList, String subject, String cont) throws Exception;

	/**
	 * 发送模板邮件
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param toList
	 * @param subject
	 * @param dto
	 * @throws Exception
	 */
	void sendTemplateMail(List<String> toList, String subject, MailTemplateDTO dto) throws Exception;

}

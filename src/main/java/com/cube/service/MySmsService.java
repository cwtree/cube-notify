package com.cube.service;

import java.util.List;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
public interface MySmsService {

	/**
	 * 发送短信
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param phones
	 * @param cont
	 */
	void sendSms(List<String> phones, String cont);

}

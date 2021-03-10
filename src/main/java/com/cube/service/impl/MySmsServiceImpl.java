package com.cube.service.impl;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cube.service.MySmsService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author phoenix
 * @date 2021年3月10日
 */
@Service
@Slf4j
public class MySmsServiceImpl implements MySmsService {

	@Async("smsExecutor")
	@Override
	public void sendSms(List<String> phones, String cont) {
		// TODO Auto-generated method stub
		if (log.isInfoEnabled()) {
			log.info("短信发送，接收人 {}，短信内容 {}", phones, cont);
		}
	}

}

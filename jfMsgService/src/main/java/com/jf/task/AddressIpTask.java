package com.jf.task;


import com.jf.common.utils.DateUtil;
import com.jf.service.MemberInfoService;
import com.jf.service.SourceNicheService;
import okhttp3.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

@Component
public class AddressIpTask {
	private static Logger logger = LoggerFactory.getLogger(AddressIpTask.class);
	
	@Resource
	private MemberInfoService memberInfoService;

	
	/**
	 * 每15分钟的第4秒跑
	 */
	@Scheduled(cron = "4 0/15 * * * ?")
	public void getAddressByIp() {
		logger.info(DateUtil.getStandardDateTime() + "通过Ip地址获取地址:start");
		try {
			memberInfoService.getAddressByIp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info(e.getMessage());
			logger.info(DateUtil.getStandardDateTime() + "通过Ip地址获取地址error");
		}
		logger.info(DateUtil.getStandardDateTime() + "通过Ip地址获取地址:end");
	}

}

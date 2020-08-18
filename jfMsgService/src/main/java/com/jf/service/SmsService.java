package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.SmsUtil;
import com.jf.dao.SmsMapper;
import com.jf.entity.Sms;
import com.jf.entity.SmsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class SmsService extends BaseService<Sms,SmsExample> {
	@Autowired
	private SmsMapper smsMapper;
	@Autowired
	public void setSmsMapper(SmsMapper smsMapper) {
		super.setDao(smsMapper);
		this.smsMapper = smsMapper;
	}
	
	/**
	 * 发送短信
	 * @param sms
	 * @return
	 */
	public Sms sendImmediately(Sms sms){
		
		if(sms.getId()==null){
			sms.setCreateDate(new Date());
			sms.setSendCount(0);
			sms.setDelFlag("0");
		}
		SmsUtil.sendSms(sms);
		sms.setSendCount(sms.getSendCount()==null?0:sms.getSendCount()+1);
		sms.setUpdateDate(new Date());
		sms.setSendDate(new Date());
		if(sms.getId()==null){
			smsMapper.insertSelective(sms);
		}else{
			smsMapper.updateByPrimaryKeySelective(sms);
		}
		return sms;
	}

	public Sms sendImmediately(String mobile, String content, String msgType){
		Sms sms = new Sms();
		sms.setMobile(mobile);
		sms.setSmsType(msgType);
		sms.setContent(content);
		sms.setSendStatus("0");
		return sendImmediately(sms);
	}
	
}

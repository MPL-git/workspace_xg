package com.jf.service;

import com.jf.biz.MchtProductBrandBiz;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MchtUserMapper;
import com.jf.dao.PlatformMsgMapper;
import com.jf.entity.MchtProductBrand;
import com.jf.entity.MchtUser;
import com.jf.entity.MchtUserExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.Sms;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月11日 上午10:29:53 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MchtProductBrandService extends MchtProductBrandBiz {
	
	private static final Log logger = LogFactory.getLog(MchtProductBrandService.class);

	@Autowired
	private MchtUserMapper mchtUserMapper;
	
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	
	@Autowired
	private SmsService smsService;

	public void sendMsg(MchtProductBrand mchtProductBrand) {
		//发送短信
		String mobile=null;
		MchtUserExample e = new MchtUserExample();
		e.createCriteria().andDelFlagEqualTo("0").andMchtIdEqualTo(mchtProductBrand.getMchtId()).andIsPrimaryEqualTo("1").andStatusEqualTo("0");
		List<MchtUser> mchtUsers = mchtUserMapper.selectByExample(e);
		if(mchtUsers!=null && mchtUsers.size()>0){
			mobile = mchtUsers.get(0).getMobile();
		}
		if (StringUtil.isMobile(mobile)) {
			Sms sms = new Sms();
			sms.setMobile(mobile);
			sms.setSmsType("2");
			sms.setContent("您的【"+mchtProductBrand.getProductBrandName()+"】授权即将到期，到期后平台将该暂停品牌");
			sms.setSendStatus("0");
			sms.setCreateDate(new Date());
			sms.setSendCount(0);
			sms.setUpdateDate(new Date());
			sms.setDelFlag("0");
			smsService.sendImmediately(sms);
		}else{
			logger.info("商家手机号不正确，无法发送短信");
		}
		//发送站内信 
		PlatformMsg platformMsg = new PlatformMsg();
		platformMsg.setMchtId(mchtProductBrand.getMchtId());
		platformMsg.setMsgType("TZ");//通知
		platformMsg.setTitle("关于品牌授权即将到期的通知");
		String content = "您的【"+mchtProductBrand.getProductBrandName()+"】授权即将到期，到期后平台将该品牌暂停运营。";
		platformMsg.setContent(content);
		platformMsg.setStatus("0");//未读
		platformMsg.setCreateDate(new Date());
		platformMsg.setDelFlag("0");
		platformMsgMapper.insertSelective(platformMsg);
	}

}

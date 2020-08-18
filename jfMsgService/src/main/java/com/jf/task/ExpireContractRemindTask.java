
package com.jf.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.jf.common.ext.RegCondition;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jf.common.constant.Const;
import com.jf.common.ext.util.StrKit;
import com.jf.entity.MchtContract;
import com.jf.entity.MchtContractCustom;
import com.jf.entity.MchtContractCustomExample;
import com.jf.entity.MchtContractCustomExample.MchtContractCustomCriteria;
import com.jf.entity.MchtContractExample;
import com.jf.entity.MchtContractExample.Criteria;
import com.jf.service.MchtContactService;
import com.jf.service.MchtContractService;
import com.jf.service.MchtPlatformContactService;
import com.jf.service.MchtUserService;
import com.jf.service.OrderDtlService;
import com.jf.service.PlatformMsgService;
import com.jf.service.SmsService;
import com.jf.service.SourceNicheService;

/**
 * 
 * @ClassName ExpireContractRemindTask
 * @Description TODO(合同到期短信提醒)
 * @author chengh
 * @date 2019年8月7日 下午4:38:36
 */
@RegCondition
@Component
public class ExpireContractRemindTask {

	private static Logger logger = LoggerFactory.getLogger(CustomerOrderServiceTask.class);
	
	@Resource
	private MchtContractService mchtContractService;
	@Resource
	private PlatformMsgService platformMsgService;
    @Resource
    private MchtUserService mchtUserService;
    @Resource
    MchtContactService mchtContactService;
    @Resource
    MchtPlatformContactService mchtPlatformContactService;
    @Resource
    SmsService smsService;
    
    
	@Scheduled(cron="0 0 15 * * ?")
    public void expireContractRemind(){	
    	logger.info("合同到期短信提醒开始.......");
    	contractWillExpireRemind(90);
    	contractWillExpireRemind(60);
    	contractWillExpireRemind(30);
    	contractWillExpireRemind(15);
    	contractWillExpireRemind(7);
    	
    	contractExpireRemind(1);
    	contractExpireRemind(8);
    	contractExpireRemind(15);
    	contractExpireRemind(22);
    	logger.info("合同到期短信提醒结束.......");
    }

	/**
	 * @MethodName contractExpireRemind
	 * @Description TODO
	 * @author chengh
	 * @date 2019年8月8日 下午1:45:41
	 */
	private void contractExpireRemind(int day) {
		// TODO Auto-generated method stub
		Date expiredDate = DateTime.now().minusDays(day).toDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			expiredDate = sdf.parse(sdf.format(expiredDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
		mchtContractCustomExample.setOrderByClause("id desc");
		MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE).andEndDateEqualTo(expiredDate);
		//续签状态是续签状态=待申请OR开放续签入口或续签状态是0
		criteria.andTotalStatusEqualTo();
		List<MchtContractCustom> mchtContracts = mchtContractService.selectByCustomExample(mchtContractCustomExample);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		if(mchtContracts.isEmpty()){
			 logger.info("需要处理的记录为0，退出任务。");
	            return;
		 }
		for (MchtContractCustom mchtContract : mchtContracts) {
			// 发送站内信
	         String title = "关于合同到期通知";
	         String content = "您的合同已于"+format.format(mchtContract.getEndDate())+"过期，平台根据合同与平台相关规定对店铺进行暂停";
	         platformMsgService.save(mchtContract.getMchtId(), title, content, "TZ");

	         String content1 = "【醒购】您的合同已于"+format.format(mchtContract.getEndDate())+"过期，平台根据合同及平台相关规定对店铺进行暂停";
	         // 发送短信给商家主账号
	         String mobile = mchtUserService.findMobileByMchtId(mchtContract.getMchtId());
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content1, "5");
	         }

	         // 发送短信给平台招商对接人
	         String content2 = "【醒购】你对接商家{"+mchtContract.getMchtCode()+"}的合同已于"+format.format(mchtContract.getEndDate())+"过期，请尽快联系商家进行续签";
	         mobile = mchtPlatformContactService.findMobile(mchtContract.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content2, "5");
	         }
	         
	         // 发送短信给平台运营对接人
	         mobile = mchtPlatformContactService.findMobile(mchtContract.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION);
	         if(StrKit.notBlank(mobile)){
	              smsService.sendImmediately(mobile, content2, "5");
	         }
	         
	         // 发送短信给商家店铺负责人
	         mobile = mchtContactService.findMobile(mchtContract.getMchtId(), "1");
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content1, "5");
	         }

	         // 发送短信给商家运营专员
	         mobile = mchtContactService.findMobile(mchtContract.getMchtId(), "2");
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content1, "5");
	         } 
		}
	}

	/**
	 * @MethodName contractWillExpireRemind
	 * @Description TODO
	 * @author chengh
     * @param day   距离到期天数
	 * @date 2019年8月8日 上午8:56:11
	 */
	private void contractWillExpireRemind(int day) {
		// TODO Auto-generated method stub
		Date expiredDate = DateTime.now().plusDays(day).toDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			expiredDate = sdf.parse(sdf.format(expiredDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 MchtContractCustomExample mchtContractCustomExample = new MchtContractCustomExample();
		 mchtContractCustomExample.setOrderByClause("id desc");
		 MchtContractCustomCriteria criteria = mchtContractCustomExample.createCriteria();
		 criteria.andDelFlagEqualTo(Const.FLAG_FALSE).andEndDateEqualTo(expiredDate);
		 //续签状态是续签状态=待申请OR开放续签入口或续签状态是0
		 criteria.andTotalStatusEqualTo();
		 List<MchtContractCustom> mchtContracts = mchtContractService.selectByCustomExample(mchtContractCustomExample);
		 SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		 if(mchtContracts.isEmpty()){
			 logger.info("需要处理的记录为0，退出任务。");
	            return;
		 }
		 for (MchtContractCustom mchtContract : mchtContracts) {
			// 发送站内信
	         String title = "关于合同续签通知";
	         String content = "您的合同将于"+format.format(mchtContract.getEndDate())+"过期，请尽快完成续签，逾期将影响店铺的运营。";
	         platformMsgService.save(mchtContract.getMchtId(), title, content, "TZ");

	         String content1 = "【醒购】您的合同将于"+format.format(mchtContract.getEndDate())+"过期，请尽快完成续签，逾期将影响店铺的运营。";
	         // 发送短信给商家主账号
	         String mobile = mchtUserService.findMobileByMchtId(mchtContract.getMchtId());
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content1, "5");
	         }

	         // 发送短信给商家店铺负责人
	         mobile = mchtContactService.findMobile(mchtContract.getMchtId(), "1");
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content1, "5");
	         }

	         // 发送短信给商家运营专员
	         mobile = mchtContactService.findMobile(mchtContract.getMchtId(), "2");
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content1, "5");
	         }
	        
	         // 发送短信给平台招商对接人
	         String content2 = "【醒购】你对接商家{"+mchtContract.getMchtCode()+"}的合同将于"+format.format(mchtContract.getEndDate())+"过期，请尽快联系商家完成续签事宜。";
	         mobile = mchtPlatformContactService.findMobile(mchtContract.getMchtId(), Const.PLAT_CONTACT_TYPE_MERCHANTS);
	         if(StrKit.notBlank(mobile)){
	             smsService.sendImmediately(mobile, content2, "5");
	         }
	         
	         if(day == 7){
	                // 发送短信给平台运营对接人
	                mobile = mchtPlatformContactService.findMobile(mchtContract.getMchtId(), Const.PLAT_CONTACT_TYPE_OPERATION);
	                if(StrKit.notBlank(mobile)){
	                    smsService.sendImmediately(mobile, content2, "5");
	                }
	            }
		}
	}
}

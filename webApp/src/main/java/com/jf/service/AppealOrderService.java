package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.AppealOrderMapper;
import com.jf.entity.AppealLog;
import com.jf.entity.AppealOrder;
import com.jf.entity.AppealOrderExample;
import com.jf.entity.SubOrder;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年5月6日 下午3:23:42 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class AppealOrderService extends BaseService<AppealOrder, AppealOrderExample> {
	
	@Autowired
	private AppealOrderMapper appealOrderMapper;
	@Autowired
	private AppealLogService appealLogService;
	@Autowired
	private AppealLogPicService appealLogPicService;
	@Autowired
	private SubOrderService subOrderService;
	@Autowired
	private PlatformMsgService platformMsgService;
	@Autowired
	private MsgTplService msgTplService;

	@Autowired
	public void setAppealOrderMapper(AppealOrderMapper appealOrderMapper) {
		this.setDao(appealOrderMapper);
		this.appealOrderMapper = appealOrderMapper;
	}

	public void addComplaint(Integer memberId, Integer mchtId, Integer subOrderId, Integer orderDtlId,
			String memberName, String appealType, String contactName, String contactPhone, String remarks, String pic) {
		Date date = new Date();
		AppealOrder appealOrder = new AppealOrder();
		String orderCode = "TS"+CommonUtil.getOrderCode();
		appealOrder.setOrderCode(orderCode);
		appealOrder.setMchtId(mchtId);
		appealOrder.setUserId(memberId);
		appealOrder.setUserType(Const.APPEAL_ORDER_TYPE_MEMBER);
		appealOrder.setUserName(memberName);
		appealOrder.setOrderDtlId(orderDtlId);
		appealOrder.setSubOrderId(subOrderId);
		appealOrder.setAppealType(appealType);
		//状态 1待客户回复 2待商家回复 3投诉单超时关闭 4平台关闭 5用户关闭
		appealOrder.setStatus(Const.APPEAL_ORDER_STATUS_2);
		appealOrder.setContactName(contactName);
		appealOrder.setContactPhone(contactPhone);
		appealOrder.setCreateBy(memberId);
		appealOrder.setCreateDate(date);
		appealOrder.setDelFlag("0");
		appealOrder.setRemarks(remarks);
		saveModel(appealOrder);
		
		AppealLog appealLog = appealLogService.add(appealOrder.getId(),remarks,memberId,date,memberName,Const.APPEAL_ORDER_TYPE_MEMBER);
		
		if(!StringUtil.isBlank(pic)){
			String[] picList = pic.split(",");
			for (String str : picList) {
				str = StringUtil.replace(str,"xgbuy.cc");
				appealLogPicService.add(appealLog.getId(),memberId,date,str,remarks);
			}
		}
		//添加站内信
		SubOrder subOrder = subOrderService.findListById(subOrderId);
		String title = Const.PLATFORM_MSG_TITLE_A4;
		String tplType = Const.MSG_TLP_TYPE_A4;
		String msgType = Const.PLATFORM_MSG_TYPE_A4;
		String content = msgTplService.findMsgContent(tplType,orderCode,appealOrder.getId(),subOrder.getSubOrderCode(),subOrder.getId(),null);
		platformMsgService.add(subOrder.getMchtId(),msgType,title,content,appealOrder.getId(),Const.PLATFORM_MSG_STATUS_0);
		
	}

	public AppealOrder saveModel(AppealOrder appealOrder) {
		appealOrderMapper.insertSelective(appealOrder);
		return appealOrder;
	}

	public void updateAppealOrder(Integer appealOrderId, String content, Integer memberId, String memberName,
			String pic) {
		Date date = new Date();
		//更改投诉单状态为，等待商家留言
		AppealOrder appealOrder = new AppealOrder();
		appealOrder.setId(appealOrderId);
		appealOrder.setStatus(Const.APPEAL_ORDER_STATUS_2);
		appealOrder.setUpdateBy(memberId);
		appealOrder.setUpdateDate(date);
		updateModel(appealOrder);
		
		//添加日志表
		AppealLog appealLog = appealLogService.add(appealOrderId, content, memberId, date, memberName, "1");
		
		//添加图片
		if(!StringUtil.isBlank(pic)){
			String[] pics = pic.split(",");
			for (String picStr : pics) {
				picStr = StringUtil.replace(picStr,"xgbuy.cc");
				appealLogPicService.add(appealLog.getId(), memberId, date, picStr, content);
			}
		}
	}

	public void updateModel(AppealOrder appealOrder) {
		
		appealOrderMapper.updateByPrimaryKeySelective(appealOrder);
	}
	
}

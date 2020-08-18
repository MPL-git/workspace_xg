package com.jf.service;

import com.jf.common.base.ArgException;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.DataDicUtil;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.InterventionOrderMapper;
import com.jf.entity.CustomerServiceOrder;
import com.jf.entity.InterventionOrder;
import com.jf.entity.InterventionOrderExample;
import com.jf.entity.InterventionOrderStatusLog;
import com.jf.entity.InterventionProcess;
import com.jf.entity.InterventionProcessExample;
import com.jf.entity.InterventionProcessPic;
import com.jf.entity.InterventionProcessPicExample;
import com.jf.entity.SubOrder;
import com.jf.entity.SysStatus;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月3日 上午9:21:48 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class InterventionOrderService extends BaseService<InterventionOrder, InterventionOrderExample> {
	
	@Autowired
	private InterventionOrderMapper interventionOrderMapper;
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private InterventionProcessPicService interventionProcessPicService;
	@Resource
	private InterventionProcessService interventionProcessService;
	@Resource
	private InterventionOrderStatusLogService interventionOrderStatusLogService;
	@Resource
	private MemberInfoService memberInfoService;

	@Autowired
	public void setInterventionOrderMapper(InterventionOrderMapper interventionOrderMapper) {
		this.setDao(interventionOrderMapper);
		this.interventionOrderMapper = interventionOrderMapper;
	}

	public void addInterventionOrderProcess(JSONObject reqDataJson) {
		Integer serviceOrderId = reqDataJson.getInt("serviceOrderId");
		Integer memberId = reqDataJson.getInt("memberId");
		String reason = reqDataJson.getString("reason");
		String contacts = reqDataJson.getString("contacts");
		String tel = reqDataJson.getString("tel");
		String message = reqDataJson.getString("message");
		String pics = reqDataJson.getString("pics");
		
		//1:B4,C4拒绝后,5天内不能申请平台介入
		//2:平台介入商家不同意申请（拒绝）(A3,B3,C3),商家不接受（拒绝）(C6,B6),买家填写物流信息后(C4,B4)

		CustomerServiceOrder customerServiceOrder = customerServiceOrderService.selectByPrimaryKey(serviceOrderId);
		if(customerServiceOrder == null){
			throw new ArgException("售后单不存在，请先申请售后");
		}
		if(customerServiceOrder.getServiceType().equals("A")){
			throw new ArgException("尚未开通退款平台介入");
		}
		String proStatus = customerServiceOrder.getProStatus();
		Date mchtRefundDate = customerServiceOrder.getUpdateDate();
		Integer subOrderId = customerServiceOrder.getSubOrderId();
		Date date = new Date();
		if(DateUtil.addDay(mchtRefundDate, 5).after(date)){
			if(proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_B4)
					|| proStatus.equals(Const.CUSTOMER_ORDER_PRO_STATUS_C4)){
				throw new ArgException("请给商家一定的处理时间（大约5天内）、给你造成不便深感抱歉、谢谢您的配合");
			}
		}
		SubOrder subOrder = subOrderService.selectByPrimaryKey(subOrderId);
		if(subOrder == null || subOrder.getStatus().equals(Const.ORDER_STATUS_SUCCESS)){
			throw new ArgException("订单已完成，不能申请介入");
		}
		InterventionOrderExample interventionOrderExample = new InterventionOrderExample();
		interventionOrderExample.createCriteria().andServiceOrderIdEqualTo(serviceOrderId).andProStatusEqualTo(proStatus).andDelFlagEqualTo("0");
		interventionOrderExample.setOrderByClause("id desc");
		List<InterventionOrder> interventionOrders = interventionOrderMapper.selectByExample(interventionOrderExample);
		InterventionOrder interventionOrder = new InterventionOrder();
		if (CollectionUtils.isNotEmpty(interventionOrders)) {
			interventionOrder = interventionOrders.get(0);
			String interventionOrderStatus = interventionOrder.getStatus();
			String processStatus = getProcessStatus(interventionOrderStatus);//流程状态
			if(processStatus.equals("1")){
				throw new ArgException("介入单已申请，请勿重复申请，等待处理中...");
			}else if(processStatus.equals("2")){
				throw new ArgException("介入单已申请，请勿重复申请，平台正在处理中...");
			}else if(processStatus.equals("3")){
				throw new ArgException("介入单已结案，请勿重复申请...");
			}
		}
		//介入单主表
		interventionOrder.setServiceOrderId(serviceOrderId);
		interventionOrder.setMchtId(subOrder.getMchtId());
		interventionOrder.setInterventionCode("JR"+CommonUtil.getOrderCode());
		interventionOrder.setProStatus(proStatus);
		interventionOrder.setReason(reason);
		interventionOrder.setContacts(contacts);
		interventionOrder.setTel(tel);
		interventionOrder.setMessage(message);
		interventionOrder.setStatus("0");
		interventionOrder.setCreateBy(memberId);
		interventionOrder.setCreateDate(date);
		interventionOrder.setUpdateDate(date);
		interventionOrder.setDelFlag("0");
		insertSelective(interventionOrder);
		//介入流程表，图片表，流水表
		saveProcess(interventionOrder,pics);
	}

	public void updateInterventionOrderProcess(JSONObject reqDataJson) {
		Integer interventionOrderId = reqDataJson.getInt("interventionOrderId");
		Integer memberId = reqDataJson.getInt("memberId");
		String reason = reqDataJson.getString("reason");
		String contacts = reqDataJson.getString("contacts");
		String tel = reqDataJson.getString("tel");
		String message = reqDataJson.getString("message");
		String pics = reqDataJson.getString("pics");
		InterventionOrder interventionOrder = selectByPrimaryKey(interventionOrderId);
		Date date = new Date();
		Date updateDate = interventionOrder.getUpdateDate();
		String interventionOrderStatus = interventionOrder.getStatus();
		String processStatus = getProcessStatus(interventionOrderStatus);//流程状态
		if(interventionOrderStatus.equals("3")){
			//更新时间+2天大于当前时间，不允许修改
			if(DateUtil.addDay(updateDate, 2).before(date)){
				throw new ArgException("复审被驳回,48小时后不允许修改介入单,该介入单"+interventionOrder.getInterventionCode()+"已结案");
			}
		}
		//等待处理种和复审被驳回48小时以内才能做修改
		if(processStatus.equals("1") || interventionOrderStatus.equals("3")){
			if(!StringUtil.isBlank(reason)){
				interventionOrder.setReason(reason);
			}
			if(!StringUtil.isBlank(contacts)){
				interventionOrder.setContacts(contacts);		
			}
			if(!StringUtil.isBlank(tel)){
				interventionOrder.setTel(tel);
			}
			if(!StringUtil.isBlank(message)){
				interventionOrder.setMessage(message);
			}
			if(interventionOrderStatus.equals("3")){
				interventionOrder.setStatus("0");
			}
			interventionOrder.setUpdateBy(memberId);
			interventionOrder.setUpdateDate(date);
			updateByPrimaryKeySelective(interventionOrder);
			//介入流程
			if(interventionOrderStatus.equals("3")){
				//复审驳回状态，更新了状态，流程也要添加
				//介入流程表，图片表，流水表
				saveProcess(interventionOrder,pics);
			}else{
				//最新一条数据
				InterventionProcessExample interventionProcessExample = new InterventionProcessExample();
				interventionProcessExample.createCriteria().andInterventionOrderIdEqualTo(interventionOrderId).andOperatorTypeEqualTo("1").andProcessTypeEqualTo("1").andDelFlagEqualTo("0");
				interventionProcessExample.setOrderByClause("id desc");
				List<InterventionProcess> interventionProcesses = interventionProcessService.selectByExample(interventionProcessExample);
				if(CollectionUtils.isNotEmpty(interventionProcesses)){
					//等待处理中，流水表不需要进行更新
					
					//更新流程表数据
					InterventionProcess process = interventionProcesses.get(0);
					process.setContent(message);
					process.setUpdateBy(memberId);
					process.setUpdateDate(date);
					interventionProcessService.updateByPrimaryKeySelective(process);
					//更新图片
					InterventionProcessPicExample processPicExampleUpdateAll = new InterventionProcessPicExample();
					processPicExampleUpdateAll.createCriteria().andInterventionProcessIdEqualTo(process.getId()).andDelFlagEqualTo("0");
					InterventionProcessPic processPicUpdateAll = new InterventionProcessPic();
					processPicUpdateAll.setDelFlag("1");
					interventionProcessPicService.updateByExampleSelective(processPicUpdateAll, processPicExampleUpdateAll);
					if (!StringUtil.isBlank(pics)) {
						String[] picList = pics.split(",");
						for (String pic : picList) {
							pic = StringUtil.replace(pic,"xgbuy.cc");
							//根据图片url查找是否存在,存在就把删除标志设置为0，不存在则 循环走下一次
							InterventionProcessPicExample processPicExampleUpdate = new InterventionProcessPicExample();
							processPicExampleUpdate.createCriteria().andPicEqualTo(pic).andInterventionProcessIdEqualTo(interventionProcesses.get(0).getId());
							InterventionProcessPic processPicUpdate = new InterventionProcessPic();
							processPicUpdate.setDelFlag("0");
							int updatePic = interventionProcessPicService.updateByExampleSelective(processPicUpdate, processPicExampleUpdate);
							if(updatePic > 0){
								continue;
							}
							//不存在则做插入操作
							InterventionProcessPic processPic = new InterventionProcessPic();
							processPic.setInterventionProcessId(process.getId());
							processPic.setPic(pic);
							processPic.setCreateBy(interventionOrder.getCreateBy());
							processPic.setCreateDate(interventionOrder.getCreateDate());
							processPic.setDelFlag("0");
							interventionProcessPicService.insertSelective(processPic);
						}
					}
				}
			}
		}
	}
	
	public void updateInterventionOrderByCancel(JSONObject reqDataJson) {
		Integer interventionOrderId = reqDataJson.getInt("interventionOrderId");
		InterventionOrder interventionOrder = selectByPrimaryKey(interventionOrderId);
		String interventionOrderStatus = interventionOrder.getStatus();
		String processStatus = getProcessStatus(interventionOrderStatus);//流程状态
		if(processStatus.equals("1")){
			interventionOrder.setDelFlag("1");
			interventionOrder.setRemarks("等待处理中撤销");
			updateByPrimaryKeySelective(interventionOrder);
		}else if(processStatus.equals("2") || interventionOrderStatus.equals("3")){
			interventionOrder.setStatus("8");
			interventionOrder.setWinType("2");
			interventionOrder.setRemarks("平台处理中客户撤销");
			updateByPrimaryKeySelective(interventionOrder);
			//新增流水
			//介入单流水表
			InterventionOrderStatusLog orderStatusLog = new InterventionOrderStatusLog();
			orderStatusLog.setInterventionOrderId(interventionOrder.getId());
			orderStatusLog.setStatus("8");
			orderStatusLog.setRemarks("平台处理中客户撤销");
			orderStatusLog.setCreateBy(interventionOrder.getCreateBy());
			orderStatusLog.setCreateDate(interventionOrder.getCreateDate());
			orderStatusLog.setDelFlag("0");
			interventionOrderStatusLogService.insertSelective(orderStatusLog);
		}
	}

	public Map<String, Object> getInterventionOrderList(JSONObject reqDataJson) {
		Integer interventionOrderId = reqDataJson.getInt("interventionOrderId");
		Map<String,Object> map = new HashMap<String,Object>();
		List<String> picList = new ArrayList<String>();
		InterventionOrder interventionOrder = selectByPrimaryKey(interventionOrderId);
		String interventionOrderStatus = interventionOrder.getStatus();
		String winType = interventionOrder.getWinType();
		String processStatus = getProcessStatus(interventionOrderStatus);//流程状态
		
		//详情，最新一条数据
		InterventionProcessExample interventionProcessExample = new InterventionProcessExample();
		interventionProcessExample.createCriteria().andInterventionOrderIdEqualTo(interventionOrderId).andProcessTypeEqualTo("1").andOperatorTypeEqualTo("1").andDelFlagEqualTo("0");
		interventionProcessExample.setOrderByClause("id desc");
		List<InterventionProcess> interventionProcesses = interventionProcessService.selectByExample(interventionProcessExample); 
		if(CollectionUtils.isNotEmpty(interventionProcesses)){
			InterventionProcessPicExample processPicExample = new InterventionProcessPicExample();
			processPicExample.createCriteria().andInterventionProcessIdEqualTo(interventionProcesses.get(0).getId()).andDelFlagEqualTo("0");
			List<InterventionProcessPic> pics = interventionProcessPicService.selectByExample(processPicExample);
			if (CollectionUtils.isNotEmpty(pics)) {
				for (InterventionProcessPic interventionProcessPic : pics) {
					picList.add(StringUtil.getPic(interventionProcessPic.getPic(), ""));
				}
			}
		}
		StringBuffer platFormFeedback = new StringBuffer();
		StringBuffer consultativeContent = new StringBuffer();
		StringBuffer cancelIntervention = new StringBuffer();
		String memberRemind = "";
		boolean updateInterventionButton = false;//修改介入
		boolean cancelInterventionButton = false;//撤销介入
		boolean interventionDetailButton = true;//更多协商
		if(processStatus.equals("1")){
			updateInterventionButton = true;
			cancelInterventionButton = true;
		}
		if(processStatus.equals("2") || interventionOrderStatus.equals("3")){
			cancelInterventionButton = true;
		}
		if(processStatus.equals("3")){
			if(!StringUtil.isBlank(interventionOrder.getClientResultReason())){
				String clientResultReason = DataDicUtil.getStatusDesc("BU_INTERVENTION_ORDER", "CLIENT_RESULT_REASON", interventionOrder.getClientResultReason());
				platFormFeedback.append(clientResultReason);
			}else{
				if(!StringUtil.isBlank(interventionOrder.getRejectReason())){
					String rejectReason = DataDicUtil.getStatusDesc("BU_INTERVENTION_ORDER", "REJECT_REASON", interventionOrder.getRejectReason());
					platFormFeedback.append(rejectReason);
				}
			}
		}
		if(interventionOrderStatus.equals("3")){
			//复审驳回
			interventionOrder.getUpdateDate();
			long seconds = DateUtil.addDay(interventionOrder.getUpdateDate(), 2).getTime()-new Date().getTime();
			long day = 00;
			long hour = 00;
			long minute = 00;
			String timeStr = "00天00时00分";
			if(seconds > 0){
				day=seconds/(60*60*24*1000);//换成天
				hour=(seconds-(60*60*24*1000*day))/(3600*1000);//总秒数-换算成天的秒数=剩余的秒数    剩余的秒数换算为小时
				minute=(seconds-60*60*24*1000*day-3600*1000*hour)/(60*1000);//总秒数-换算成天的秒数-换算成小时的秒数=剩余的秒数    剩余的秒数换算为分
				timeStr = day+"天"+hour+"时"+minute+"分";
				memberRemind = "提醒：您还有"+timeStr+"修改介入信息，否则自动关闭介入单";
				updateInterventionButton = true;
			}else{
				updateInterventionButton = false;
			}
		}
		if(interventionOrderStatus.equals("8") && StringUtil.isBlank(interventionOrder.getWinType())){
			//撤销界面使用（已结案+胜诉方为空）
			cancelIntervention.append("撤销介入<br/>");
			cancelIntervention.append("关闭时间："+interventionOrder.getUpdateDate());
		}
		SysStatus sysStatus = DataDicUtil.getStatus("BU_INTERVENTION_ORDER", "REASON", interventionOrder.getReason());
		consultativeContent.append("协商内容<br/><br/>");
		consultativeContent.append("介入原因："+interventionOrder.getReason());
		consultativeContent.append("<br/>联系人："+interventionOrder.getContacts());
		consultativeContent.append("<br/>联系电话："+interventionOrder.getTel());
		consultativeContent.append("<br/>介入留言："+interventionOrder.getMessage());
		map.put("processStatus", processStatus);
		map.put("updateInterventionButton", updateInterventionButton);
		map.put("cancelInterventionButton", cancelInterventionButton);
		map.put("interventionDetailButton", interventionDetailButton);
		map.put("reason", interventionOrder.getReason());
		map.put("reasonStr", sysStatus.getStatusDesc());
		map.put("contacts", interventionOrder.getContacts());
		map.put("tel", interventionOrder.getTel());
		map.put("message", interventionOrder.getMessage());
		map.put("picList", picList);
		map.put("consultativeContent", consultativeContent.toString());
		map.put("platFormFeedback", platFormFeedback.toString());
		map.put("memberRemind", memberRemind);
		map.put("serviceOrderId", interventionOrder.getServiceOrderId());
		map.put("interventionOrderId", interventionOrder.getId());
		return map;
	}
	
	private void saveProcess(InterventionOrder interventionOrder, String pics) {
		//介入流程
		InterventionProcess process = new InterventionProcess();
		process.setInterventionOrderId(interventionOrder.getId());
		process.setOperatorType("1");
		process.setProcessType("1");
		process.setContent(interventionOrder.getMessage());
		process.setCreateBy(interventionOrder.getCreateBy());
		process.setCreateDate(interventionOrder.getCreateDate());
		process.setDelFlag("0");
		interventionProcessService.insertSelective(process);
		//图片
		if(!StringUtil.isBlank(pics)){
			String[] picList = pics.split(",");
			for (String pic : picList) {
				pic = StringUtil.replace(pic,"xgbuy.cc");
				InterventionProcessPic processPic = new InterventionProcessPic();
				processPic.setInterventionProcessId(process.getId());
				processPic.setPic(pic);
				processPic.setCreateBy(interventionOrder.getCreateBy());
				processPic.setCreateDate(interventionOrder.getCreateDate());
				processPic.setDelFlag("0");
				interventionProcessPicService.insertSelective(processPic);
			}
		}
		//介入单流水表
		InterventionOrderStatusLog orderStatusLog = new InterventionOrderStatusLog();
		orderStatusLog.setInterventionOrderId(interventionOrder.getId());
		orderStatusLog.setStatus("0");
		orderStatusLog.setRemarks(interventionOrder.getMessage());
		orderStatusLog.setCreateBy(interventionOrder.getCreateBy());
		orderStatusLog.setCreateDate(interventionOrder.getCreateDate());
		orderStatusLog.setDelFlag("0");
		interventionOrderStatusLogService.insertSelective(orderStatusLog);
	}
	
	private String getProcessStatus(String interventionOrderStatus) {
		String processStatus = "";
		if (interventionOrderStatus.equals("0") || interventionOrderStatus.equals("1")
				|| interventionOrderStatus.equals("2")) {
			processStatus = "1";
		} else if (interventionOrderStatus.equals("4") || interventionOrderStatus.equals("5")
				|| interventionOrderStatus.equals("6") || interventionOrderStatus.equals("7")) {
			processStatus = "2";
		} else if (interventionOrderStatus.equals("3") || interventionOrderStatus.equals("8")) {
			processStatus = "3";
		}
		return processStatus;
	}

	public Map<String, Object> getInterventionDetailMessageList(JSONObject reqDataJson) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> msgMapList = new ArrayList<Map<String,Object>>();
		Integer interventionOrderId = reqDataJson.getInt("interventionOrderId");
		Integer currentPage = reqDataJson.getInt("currentPage");
		Integer pageSize = Const.RETURN_SIZE_10;
		InterventionOrder interventionOrder = selectByPrimaryKey(interventionOrderId);
		String interventionOrderStatus = interventionOrder.getStatus();
		String processStatus = getProcessStatus(interventionOrderStatus);
		List<String> picListOne = new ArrayList<String>();
		//获取申诉图片（第一次）
		InterventionProcessExample interventionProcessExampleOne = new InterventionProcessExample();
		interventionProcessExampleOne.createCriteria().andInterventionOrderIdEqualTo(interventionOrderId).andProcessTypeEqualTo("1").andOperatorTypeEqualTo("1").andDelFlagEqualTo("0");
		interventionProcessExampleOne.setOrderByClause("id desc");
		List<InterventionProcess> interventionProcessesOne = interventionProcessService.selectByExample(interventionProcessExampleOne); 
		if(CollectionUtils.isNotEmpty(interventionProcessesOne)){
			for (InterventionProcess process : interventionProcessesOne) {
				InterventionProcessPicExample processPicExample = new InterventionProcessPicExample();
				processPicExample.createCriteria().andInterventionProcessIdEqualTo(process.getId()).andDelFlagEqualTo("0");
				List<InterventionProcessPic> pics = interventionProcessPicService.selectByExample(processPicExample);
				if (CollectionUtils.isNotEmpty(pics)) {
					for (InterventionProcessPic interventionProcessPic : pics) {
						picListOne.add(StringUtil.getPic(interventionProcessPic.getPic(), ""));
					}
				}
			}
		}
		//获取留言
		InterventionProcessExample interventionProcessExample = new InterventionProcessExample();
		interventionProcessExample.createCriteria().andInterventionOrderIdEqualTo(interventionOrderId).andProcessTypeEqualTo("2").andOperatorTypeEqualTo("1").andDelFlagEqualTo("0");
		interventionProcessExample.setOrderByClause("id desc");
		interventionProcessExample.setLimitStart(currentPage*pageSize);
		interventionProcessExample.setLimitSize(pageSize);
		List<InterventionProcess> interventionProcesses = interventionProcessService.selectByExample(interventionProcessExample); 
		if(CollectionUtils.isNotEmpty(interventionProcesses)){
			for (InterventionProcess process : interventionProcesses) {
				Map<String,Object> msgMap = new HashMap<String,Object>();
				List<String> picList = new ArrayList<String>();
				String message = process.getContent();
				InterventionProcessPicExample processPicExample = new InterventionProcessPicExample();
				processPicExample.createCriteria().andInterventionProcessIdEqualTo(process.getId()).andDelFlagEqualTo("0");
				List<InterventionProcessPic> pics = interventionProcessPicService.selectByExample(processPicExample);
				if (CollectionUtils.isNotEmpty(pics)) {
					for (InterventionProcessPic interventionProcessPic : pics) {
						picList.add(StringUtil.getPic(interventionProcessPic.getPic(), ""));
					}
				}
				msgMap.put("message", message);
				msgMap.put("createDate", DateUtil.getFormatDate(process.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				msgMap.put("picList", picList);
				msgMapList.add(msgMap);
			}
		}
		boolean messageButton = true;//修改介入
		if(processStatus.equals("3")){
			messageButton = false;
		}
		SysStatus sysStatus = DataDicUtil.getStatus("BU_INTERVENTION_ORDER", "REASON", interventionOrder.getReason());
		map.put("messageButton", messageButton);
		map.put("reason", interventionOrder.getReason());
		map.put("message", interventionOrder.getMessage());
		map.put("picList", picListOne);
		map.put("reasonStr", sysStatus.getStatusDesc());
		map.put("contacts", interventionOrder.getContacts());
		map.put("tel", interventionOrder.getTel());
		map.put("interventionOrderId", interventionOrder.getId());
		map.put("msgMapList", msgMapList);
		
		return map;
	}

	public void addProcessMsg(JSONObject reqDataJson) {
		Integer interventionOrderId = reqDataJson.getInt("interventionOrderId");
		Integer memberId = reqDataJson.getInt("memberId");
		String pics = reqDataJson.getString("pics");
		String message = reqDataJson.getString("message");
		Date date = new Date();
		InterventionOrder interventionOrder = selectByPrimaryKey(interventionOrderId);
		String interventionOrderStatus = interventionOrder.getStatus();
		String processStatus = getProcessStatus(interventionOrderStatus);
		if(processStatus.equals("3")){
			throw new ArgException("介入已结案，不能留言");
		}
		//介入流程
		InterventionProcess process = new InterventionProcess();
		process.setInterventionOrderId(interventionOrder.getId());
		process.setOperatorType("1");
		process.setProcessType("2");
		process.setContent(message);
		process.setCreateBy(memberId);
		process.setCreateDate(date);
		process.setDelFlag("0");
		interventionProcessService.insertSelective(process);
		//图片
		if(!StringUtil.isBlank(pics)){
			String[] picList = pics.split(",");
			for (String pic : picList) {
				pic = StringUtil.replace(pic,"xgbuy.cc");
				InterventionProcessPic processPic = new InterventionProcessPic();
				processPic.setInterventionProcessId(process.getId());
				processPic.setPic(pic);
				processPic.setCreateBy(memberId);
				processPic.setCreateDate(date);
				processPic.setDelFlag("0");
				interventionProcessPicService.insertSelective(processPic);
			}
		}
	}
}

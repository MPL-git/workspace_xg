package com.jf.controller;

import com.alibaba.fastjson.JSON;
import com.jf.common.base.ResponseMsg;
import com.jf.common.constant.Const;
import com.jf.common.ext.core.BaseController;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.entity.Coupon;
import com.jf.entity.MemberCoupon;
import com.jf.entity.SysAppMessage;
import com.jf.entity.Task;
import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskActivitySelectionExample;
import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskMarketingExample;
import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberExample;
import com.jf.entity.TaskSms;
import com.jf.entity.TaskSmsExample;
import com.jf.service.CouponService;
import com.jf.service.MemberInfoService;
import com.jf.service.MemberPvService;
import com.jf.service.TaskActivitySelectionService;
import com.jf.service.TaskMarketingService;
import com.jf.service.TaskSendMemberService;
import com.jf.service.TaskSendSmsService;
import com.jf.service.TaskSmsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Controller
public class TaskSendMemberController extends BaseController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(TaskSendMemberController.class);
	
	@Autowired
	private TaskSendMemberService taskSendMemberService;
	
	@Autowired
	private TaskMarketingService taskMarketingService;
	
	@Autowired
	private TaskActivitySelectionService taskActivitySelectionService;

	@Autowired
	private MemberInfoService memberInfoService;

	@Autowired
	private MemberPvService memberPvService;

	@Autowired
	private TaskSmsService taskSmsService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private TaskSendSmsService taskSendSmsService;


	/**
	 * @MethodName: sendTaskXYSms
	 * @Description: (营销中心歆阳短信任务推送)
	 * @param request
	 * @return
	 */
	@ResponseBody
	//@RequestMapping("/taskSendMember/sendTaskXYSms")
	public ResponseMsg sendTaskXYSms(HttpServletRequest request) {

		logger.info("营销中心歆阳短信任务推送start：" + DateUtil.getStandardDateTime());

		try {
			int sendSms = 0;
			Map<String, Object> paramMap = new HashMap<>();
			List<Map<String, Object>> taskMapList = taskSendMemberService.sendSmsTaskListByChannel(Const.TASK_SHXY_SMS_CHANNEL);
			List<Map<String, Object>> mapList;
			List<List<Map<String, Object>>> resultList;
			for(Map<String, Object> taskMap : taskMapList ) {
				if(taskMap.get("send_count") == null || StringUtil.isEmpty(taskMap.get("send_count").toString()) ) {
					continue;
				}
				boolean giveCouponFlag = "1".equals(taskMap.get("is_give_coupon").toString());
				MemberCoupon memberCoupon = new MemberCoupon();
				if (giveCouponFlag) {
					memberCoupon = this.getMemberCouponTemplate(Integer.parseInt(taskMap.get("coupon_id").toString()));
				}

				paramMap.put("sendChannel", Const.TASK_SHXY_SMS_CHANNEL);
				paramMap.put("taskId", taskMap.get("id").toString());
				paramMap.put("limitSize", Integer.parseInt(taskMap.get("send_count").toString()));
				mapList = taskSendMemberService.sendSmsSendListByChannel(paramMap);
				resultList = new ArrayList<List<Map<String, Object>>>();
				if(mapList != null && mapList.size() > 0 ) {
					int sendCount = Integer.parseInt(taskMap.get("send_count").toString());
					sendCount = sendCount>(Const.MAX_SEND_SMS-sendSms)?(Const.MAX_SEND_SMS-sendSms):sendCount;
					int len = Const.MAX_TASK_SEND_PAGE;
					int size = mapList.size()>sendCount?sendCount:mapList.size();
					int count = (size + len - 1) / len;
					for (int i = 0; i < count; i++) {
						List<Map<String, Object>> subList = mapList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
						resultList.add(subList);
					}
					for(List<Map<String, Object>> resultMapList : resultList) {
						//赠送优惠券
						if (giveCouponFlag) {
							couponService.batchGiveCoupontoMember(resultMapList, memberCoupon);
						}
						taskSendMemberService.sendTaskXYSmsList(resultMapList);
					}
					Task task = new Task();
					task.setId(Integer.parseInt(taskMap.get("id").toString()));
					task.setLastSendDate(new Date());
					task.setUpdateDate(new Date());
					taskSendMemberService.updateTaskKeySelective(task);
					sendSms += size;
				}
				if(sendSms >= Const.MAX_SEND_SMS ) {
					break;
				}
			}

			logger.info("营销中心歆阳短信任务推送end：" + DateUtil.getStandardDateTime());

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	/**
	 * @MethodName: xyNotifyUrl
	 * @Description: (歆阳回调推送数据)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/taskSendMember/xyNotifyUrl")
	public String xyNotifyUrl(HttpServletRequest request) {
		logger.info("上海歆阳回调推送start===============>" + DateUtil.getStandardDateTime());
		String resultStr = "0";//正常返回0
		try {
			String reqPRM = (String) request.getAttribute("par");
			if( StringUtil.isEmpty(reqPRM)) {
				resultStr = "1";
			}else {
				logger.info("上海歆阳回调推送数据 par ===============>"+reqPRM);
                String[] dataList = reqPRM.split(";");
                ArrayList<String> successList = new ArrayList<>();
                ArrayList<String> defeatedList = new ArrayList<>();

                String returnSeqNum = "";
				for(int i=0; i < dataList.length; i++){
					String[] reportlist = dataList[i].split(",");
					//手机号
					String mobile = reportlist[3];
					//MSGID
					String msgID = reportlist[4];
					//状态
					String status = reportlist[5];

                    if(!StringUtil.isEmpty(msgID)){
                        if(i==0){
                            returnSeqNum = dataList[0].split(",")[4];
                            if("DELIVRD".equals(status) || "0".equals(status)) {
                                successList.add(mobile);
                            }else {
                                defeatedList.add(mobile);
                            }
                        }else if(returnSeqNum.equals(msgID)){
                            if("DELIVRD".equals(status) || "0".equals(status)) {
                                successList.add(mobile);
                            }else {
                                defeatedList.add(mobile);
                            }
                        }else{
                            Map<String, Object> paramMap = new HashMap<String, Object>(3);
                            if("DELIVRD".equals(status) || "0".equals(status)) {
                                paramMap.put("status", "2");
                            }else {
                                paramMap.put("status", "3");
                            }
                            paramMap.put("seqNum", msgID);
                            paramMap.put("mobile", mobile);
                            taskSendMemberService.xyNotifyTaskSendMember(paramMap);
                        }
                    }
				}
				TaskSendMemberExample taskSendMemberExample = new TaskSendMemberExample();
				taskSendMemberExample.createCriteria().andSeqNumEqualTo(returnSeqNum).andDelFlagEqualTo("0");
				taskSendMemberExample.setLimitStart(0);
				taskSendMemberExample.setLimitSize(1);
				List<TaskSendMember> taskSendMembers = taskSendMemberService.selectByExample(taskSendMemberExample);
				if(taskSendMembers==null || taskSendMembers.size()<=0){
					 return "1";
				}
				TaskSendMember taskSendMember = taskSendMembers.get(0);

				if(successList.size() > 0 ) {
					Map<String, Object> successMap = new HashMap<String, Object>();
					successMap.put("status", "2");
					successMap.put("seqNum", returnSeqNum);
					successMap.put("mobileList", successList);
					if (!StringUtil.isEmpty(taskSendMember.getMobile())) {
						taskSendMemberService.updateTaskSendMember(successMap);
					}else {
						taskSendMemberService.xyNotifyTaskSendMember(successMap);
					}
				}
				if(defeatedList.size() > 0 ) {
					Map<String, Object> errorMap = new HashMap<String, Object>();
					errorMap.put("status", "3");
					errorMap.put("seqNum", returnSeqNum);
					errorMap.put("mobileList", defeatedList);
					if (!StringUtil.isEmpty(taskSendMember.getMobile())) {
						taskSendMemberService.updateTaskSendMember(errorMap);
					}else {
						taskSendMemberService.xyNotifyTaskSendMember(errorMap);
					}
				}
				logger.info("上海歆阳回调推送end===============>" + DateUtil.getStandardDateTime());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultStr = "1";
		}
		return resultStr;
	}

	/**
	 * @MethodName: sendTaskMwSms
	 * @Description: (营销中心梦网短信任务推送)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/taskSendMember/sendTaskMwSms")
	public ResponseMsg sendTaskMwSms(HttpServletRequest request) {

		logger.info("营销中心梦网短信任务推送start：" + DateUtil.getStandardDateTime());

		try {
			int sendSms = 0;
			Map<String, Object> paramMap = new HashMap<>();
			List<Map<String, Object>> taskMapList = taskSendMemberService.sendSmsTaskListByChannel(Const.TASK_MW_SMS_CHANNEL);
			List<Map<String, Object>> mapList;
			List<List<Map<String, Object>>> resultList;
			for(Map<String, Object> taskMap : taskMapList ) {
				if(taskMap.get("send_count") == null || StringUtil.isEmpty(taskMap.get("send_count").toString()) ) {
					continue;
				}
				boolean giveCouponFlag = "1".equals(taskMap.get("is_give_coupon").toString());
				MemberCoupon memberCoupon = new MemberCoupon();
				if (giveCouponFlag) {
					memberCoupon = this.getMemberCouponTemplate(Integer.parseInt(taskMap.get("coupon_id").toString()));
				}
				String seqNum = taskMap.get("id").toString()+"-"+taskMap.get("send_type").toString();

				logger.info("定时任务taskId=====>：" + taskMap.get("id"));

				paramMap.put("sendChannel", Const.TASK_MW_SMS_CHANNEL);
				paramMap.put("taskId", taskMap.get("id").toString());
				paramMap.put("limitSize", Integer.parseInt(taskMap.get("send_count").toString()));
				mapList = taskSendMemberService.sendSmsSendListByChannel(paramMap);
				resultList = new ArrayList<List<Map<String, Object>>>();

				logger.info("定时任务task任务发总送量=====>：" + mapList.size());


				if(mapList != null && mapList.size() > 0 ) {
					int sendCount = Integer.parseInt(taskMap.get("send_count").toString());
					sendCount = sendCount>(Const.MAX_SEND_SMS-sendSms)?(Const.MAX_SEND_SMS-sendSms):sendCount;
					int len = Const.MW_MAX_TASK_SEND_PAGE;
					int size = mapList.size()>sendCount?sendCount:mapList.size();
					int count = (size + len - 1) / len;
					for (int i = 0; i < count; i++) {
						List<Map<String, Object>> subList = mapList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
						resultList.add(subList);
					}
					for(List<Map<String, Object>> resultMapList : resultList) {
						//赠送优惠券
						if (giveCouponFlag) {
							couponService.batchGiveCoupontoMember(resultMapList, memberCoupon);
						}
						taskSendSmsService.sendTaskMwSmsList(resultMapList, seqNum);

					}
					Task task = new Task();
					task.setId(Integer.parseInt(taskMap.get("id").toString()));
					task.setLastSendDate(new Date());
					task.setUpdateDate(new Date());
					taskSendMemberService.updateTaskKeySelective(task);
					sendSms += size;
				}

				if(Integer.parseInt(taskMap.get("send_count").toString()) > mapList.size()) {
					TaskSmsExample taskSmsExample = new TaskSmsExample();
					taskSmsExample.createCriteria().andDelFlagEqualTo("0")
							.andSendStatusEqualTo("0")
							.andTaskIdEqualTo(Integer.parseInt(taskMap.get("id").toString()));
					TaskSms taskSms = new TaskSms();
					taskSms.setSendStatus("1");
					taskSms.setUpdateDate(new Date());
					taskSmsService.updateByExampleSelective(taskSms, taskSmsExample);
				}
				logger.info("定时任务task:{}本次任务发送完毕", taskMap.get("id"));

				if(sendSms >= Const.MAX_SEND_SMS ) {
					break;
				}
			}
			logger.info("营销中心梦网短信任务推送end：" + DateUtil.getStandardDateTime());

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	/**
	 * @MethodName: mwNotifyUrl
	 * @Description: (梦网回调推送数据)
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/taskSendMember/mwNotifyUrl")
	public String mwNotifyUrl(HttpServletRequest request) {
		logger.info("梦网回调推送start===============>" + DateUtil.getStandardDateTime());
		com.alibaba.fastjson.JSONObject resJson = new com.alibaba.fastjson.JSONObject();
		resJson.put("cmd", "RPT_RESP");
		resJson.put("seqid", request.getParameter("seqid"));
		try {
			resJson.put("result", 0);
			com.alibaba.fastjson.JSONArray rpts = JSON.parseArray(request.getParameter("rpts"));
            HashMap<String, HashMap<String, ArrayList<String>>> map = new HashMap<>();
            for (int i = 0; i < rpts.size(); i++) {
                com.alibaba.fastjson.JSONObject rpt = rpts.getJSONObject(i);
                if(map.containsKey(rpt.getString("custid"))){
                    HashMap<String, ArrayList<String>> itemMap = map.get(rpt.getString("custid"));
                    if(rpt.getInteger("status") == 0){
                        itemMap.get("success").add(rpt.getString("mobile"));
                    }else {
                        itemMap.get("fail").add(rpt.getString("mobile"));
                    }
                }else {
                    HashMap<String, ArrayList<String>> itemMap = new HashMap<>();
                    itemMap.put("success", new ArrayList<String>());
                    itemMap.put("fail", new ArrayList<String>());
                    if(rpt.getInteger("status") == 0){
                        itemMap.get("success").add(rpt.getString("mobile"));
                    }else {
                        itemMap.get("fail").add(rpt.getString("mobile"));
                    }
                    map.put(rpt.getString("custid"), itemMap);
                }
            }
            for (Map.Entry<String, HashMap<String, ArrayList<String>>> itemMap : map.entrySet()) {
				String[] split = itemMap.getKey().split("-");
                if(itemMap.getValue().get("success").size() > 0 ) {
					Map<String, Object> successMap = new HashMap<String, Object>();
                    successMap.put("status", "2");
                    successMap.put("taskId", split[0]);
                    successMap.put("mobileList", itemMap.getValue().get("success"));
                    //0,用户ID 1,手机号 2,全平台用户,
                    if("1".equals(split[1])){
						taskSendMemberService.updateTaskSendMember(successMap);
                    }else if ("0".equals(split[1]) || "2".equals(split[1])) {
						taskSendMemberService.xyNotifyTaskSendMember(successMap);
					}
				}
                if(itemMap.getValue().get("fail").size() > 0 ) {
                    Map<String, Object> errorMap = new HashMap<String, Object>();
                    errorMap.put("status", "3");
                    errorMap.put("taskId", split[0]);
                    errorMap.put("mobileList", itemMap.getValue().get("fail"));
					//0,用户ID 1,手机号 2,全平台用户,
					if("1".equals(split[1])){
						taskSendMemberService.updateTaskSendMember(errorMap);
					}else if ("0".equals(split[1]) || "2".equals(split[1])) {
						taskSendMemberService.xyNotifyTaskSendMember(errorMap);
					}
                }
            }
			logger.info("梦网回调推送数据end===============>" + DateUtil.getStandardDateTime());
		} catch (Exception e) {
			logger.info("梦网回调推送数据异常!");
			e.printStackTrace();
			resJson.put("result", 1);
		}
		return resJson.toString();
	}

	/**
	 * 
	 * @MethodName: sendTaskSms
	 * @Description: (营销中心玄武短信任务推送)
	 * @author Pengl
	 * @date 2019年8月23日 上午9:33:08
	 */
	@ResponseBody
	@RequestMapping("/taskSendMember/sendTaskSms")
	public ResponseMsg sendTaskSms(HttpServletRequest request) {
		
		logger.info("营销中心玄武短信任务推送start：" + DateUtil.getStandardDateTime());
		
		try {
			int sendSms = 0;
			Map<String, Object> paramMap = new HashMap<>();
			List<Map<String, Object>> taskMapList = taskSendMemberService.sendSmsTaskListByChannel(Const.TASK_XW_SMS_CHANNEL);
			List<Map<String, Object>> mapList;
			List<List<Map<String, Object>>> resultList;
			for(Map<String, Object> taskMap : taskMapList ) {
				if(taskMap.get("send_count") == null || StringUtil.isEmpty(taskMap.get("send_count").toString()) ) {
					continue;
				}
				boolean giveCouponFlag = "1".equals(taskMap.get("is_give_coupon").toString());
				MemberCoupon memberCoupon = new MemberCoupon();
				if (giveCouponFlag) {
					memberCoupon = this.getMemberCouponTemplate(Integer.parseInt(taskMap.get("coupon_id").toString()));
				}

				paramMap.put("sendChannel", Const.TASK_XW_SMS_CHANNEL);
				paramMap.put("taskId", taskMap.get("id").toString());
				paramMap.put("limitSize", Integer.parseInt(taskMap.get("send_count").toString()));
				mapList = taskSendMemberService.sendSmsSendListByChannel(paramMap);
				resultList = new ArrayList<List<Map<String, Object>>>();
				if(mapList != null && mapList.size() > 0 ) {
					int sendCount = Integer.parseInt(taskMap.get("send_count").toString());
					sendCount = sendCount>(Const.MAX_SEND_SMS-sendSms)?(Const.MAX_SEND_SMS-sendSms):sendCount;
					int len = Const.MAX_TASK_SEND_PAGE;
					int size = mapList.size()>sendCount?sendCount:mapList.size();
					int count = (size + len - 1) / len;
					for (int i = 0; i < count; i++) {
						List<Map<String, Object>> subList = mapList.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
						resultList.add(subList);
					}
					for(List<Map<String, Object>> resultMapList : resultList) {
						//赠送优惠券
						if (giveCouponFlag) {
							couponService.batchGiveCoupontoMember(resultMapList, memberCoupon);
						}
//						taskSendMemberService.sendTaskSmsList(resultMapList);
					}
					Task task = new Task();
					task.setId(Integer.parseInt(taskMap.get("id").toString()));
					task.setLastSendDate(new Date());
					task.setUpdateDate(new Date());
					taskSendMemberService.updateTaskKeySelective(task);
					sendSms += size;
				}

				if(Integer.parseInt(taskMap.get("send_count").toString()) > mapList.size()) {
					TaskSmsExample taskSmsExample = new TaskSmsExample();
					taskSmsExample.createCriteria().andDelFlagEqualTo("0")
							.andSendStatusEqualTo("0")
							.andTaskIdEqualTo(Integer.parseInt(taskMap.get("id").toString()));
					TaskSms taskSms = new TaskSms();
					taskSms.setSendStatus("1");
					taskSms.setUpdateDate(new Date());
					taskSmsService.updateByExampleSelective(taskSms, taskSmsExample);
				}

				logger.info("定时任务task:{}本次任务发送完毕", taskMap.get("id"));

				if(sendSms >= Integer.parseInt(taskMap.get("send_count").toString()) ) {
					break;
				}

			}

			logger.info("营销中心玄武短信任务推送end：" + DateUtil.getStandardDateTime());

			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}
	
	/**
	 * 
	 * @MethodName: xwNotifyUrl
	 * @Description: (玄武回调推送数据)
	 * @author Pengl
	 * @date 2019年8月19日 上午11:37:55
	 */
	@ResponseBody
	@RequestMapping("/post/taskSendMember/xwNotifyUrl")
	public String xwNotifyUrl(HttpServletRequest request) {
		String resultStr = "0";
		try {
			JSONObject reqPRM = (JSONObject) request.getAttribute("reqPRM");
			if(reqPRM.containsKey("dataList") && reqPRM.get("dataList") != null ) {

				logger.info("玄武回调推送数据begin===============>" + DateUtil.getStandardDateTime());
//				logger.info("玄武回调推送数据dataList===============>"+reqPRM.getJSONArray("dataList"));

				List<String> successList = new ArrayList<>();
				List<String> errorList = new ArrayList<>();
				JSONArray dataList = reqPRM.getJSONArray("dataList");
				for(int i=0; i < dataList.size(); i++){
					String customMsgID = "";
					String state = "";
					if(dataList.getJSONObject(i).containsKey("customMsgID") ) {
						customMsgID = dataList.getJSONObject(i).getString("customMsgID");
					}
					if(dataList.getJSONObject(i).containsKey("state") ) {
						state = dataList.getJSONObject(i).getString("state");
					}
					if(!StringUtil.isEmpty(customMsgID) ) {
						if(customMsgID.contains(Const.customMsgIDHead) && customMsgID.contains(Const.customMsgIDTail) ) {
							if(customMsgID.contains("#") ) {
								customMsgID = customMsgID.substring(0, customMsgID.indexOf("#"));
							}
							if("0".equals(state) ) {
								successList.add(customMsgID);
							}else {
								errorList.add(customMsgID);
							}
						}
					}
				}
				if(successList.size() > 0 ) {
					Map<String, Object> successMap = new HashMap<String, Object>();
					successMap.put("status", "2");
					successMap.put("seqNumList", successList);
					taskSendMemberService.updateTaskSendMember(successMap);
				}
				if(errorList.size() > 0 ) {
					Map<String, Object> errorMap = new HashMap<String, Object>();
					errorMap.put("status", "3");
					errorMap.put("seqNumList", errorList);
					taskSendMemberService.updateTaskSendMember(errorMap);
				}
				logger.info("玄武回调推送数据end===============>" + DateUtil.getStandardDateTime());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr;
	}
	
	/**
	 * 
	 * @MethodName: sendTaskMarketing
	 * @Description: (营销中心极光任务推送)
	 * @author Pengl
	 * @date 2019年8月23日 上午9:30:29
	 */
	@ResponseBody
	@RequestMapping("/taskSendMember/sendTaskMarketing")
	public ResponseMsg sendTaskMarketing(HttpServletRequest request) {

		logger.info("营销中心极光任务推送start：" + DateUtil.getStandardDateTime());

		try {
			int startNum = 1;
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("type", "1");
			paramMap.put("status", "4");
			List<Task> taskIdList = taskSendMemberService.sendTaskList(paramMap);
			if(taskIdList.size() > 0){
				try {
					for(Task task : taskIdList) {
						TaskMarketingExample taskMarketingExample = new TaskMarketingExample();
						taskMarketingExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(task.getId());
						List<TaskMarketing> taskMarketingList = taskMarketingService.selectByExample(taskMarketingExample);
						if(taskMarketingList != null && taskMarketingList.size() > 0 ) {
							startNum = taskSendMemberService.sendTaskMarketing(task, taskMarketingList.get(0), 0, 1000, startNum);
							if(startNum > Const.MAX_PAGE) {
								return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
							}
						}
					}

					logger.info("营销中心极光任务推送end：" + DateUtil.getStandardDateTime());

					if(startNum <= Const.MAX_PAGE) {
						sendTaskActivitySelection(startNum);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				sendTaskActivitySelection(startNum);
			}
			return new ResponseMsg(ResponseMsg.SUCCESS, ResponseMsg.SUCCESS_MSG);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return new ResponseMsg(ResponseMsg.ERROR, ResponseMsg.ERROR_MSG);
		}
	}

	/**
	 *
	 * @MethodName: sendTaskMarketing
	 * @Description: (活动精选极光任务推送)
	 * @author XDD
	 * @date 2019年8月26日
	 */
	private synchronized void sendTaskActivitySelection(Integer pushNumber) {

		logger.info("活动精选极光任务推送start：" + DateUtil.getStandardDateTime());

		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("type", "2");
			paramMap.put("status", "3");
			List<Task> taskIdList = taskSendMemberService.sendTaskList(paramMap);
			for(Task task : taskIdList) {
				TaskActivitySelectionExample taskActivitySelectionExample = new TaskActivitySelectionExample();
				taskActivitySelectionExample.createCriteria().andDelFlagEqualTo("0").andTaskIdEqualTo(task.getId());
				List<TaskActivitySelection> taskActivitySelectionList = taskActivitySelectionService.selectByExample(taskActivitySelectionExample);
				if(taskActivitySelectionList != null && taskActivitySelectionList.size() > 0 ) {
					//创建APP消息记录表实体
					TaskActivitySelection taskActivitySelection = taskActivitySelectionList.get(0);
					SysAppMessage sysAppMessage = new SysAppMessage();
					sysAppMessage.setType("3");
					sysAppMessage.setTitle(task.getName());
					sysAppMessage.setContent(task.getContent());
					sysAppMessage.setPic(taskActivitySelection.getCoverPic());
					sysAppMessage.setLinkType("11");//活动任务精选
					sysAppMessage.setLinkId(taskActivitySelection.getId());
					sysAppMessage.setCreateBy(task.getCreateBy());
					sysAppMessage.setCreateDate(new Date());
					sysAppMessage.setDelFlag("0");
					//是否插入SysAppMessage的状态
					Boolean flag = true;
					pushNumber = taskSendMemberService.sendTaskActivitySelection(task, taskActivitySelection, sysAppMessage, flag, pushNumber,0, 1000);
					if (pushNumber > Const.MAX_PAGE ){
						return;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("活动精选极光任务推送end：" + DateUtil.getStandardDateTime());

	}

	/**
	 * 营销短信优惠券模板
	 * @param couponId
	 * @return
	 */
	private MemberCoupon getMemberCouponTemplate(Integer couponId){
		Coupon coupon = couponService.selectByPrimaryKey(couponId);
		Date date = new Date();
		MemberCoupon memberCoupon = new MemberCoupon();
		memberCoupon.setCouponId(couponId);
		memberCoupon.setRecDate(date);
		//有效期类型（1绝对时间 2相对时间）
		if("1".equals(coupon.getExpiryType())){
			memberCoupon.setExpiryBeginDate(coupon.getExpiryBeginDate());
			memberCoupon.setExpiryEndDate(coupon.getExpiryEndDate());
		} else if ("2".equals(coupon.getExpiryType())) {
			memberCoupon.setExpiryBeginDate(date);
			memberCoupon.setExpiryEndDate(DateUtil.addDay(date, coupon.getExpiryDays()));
		}
		memberCoupon.setStatus("0");
		memberCoupon.setReceiveType("9");//营销短信赠送
		memberCoupon.setCreateDate(new Date());
		memberCoupon.setDelFlag("0");
		return memberCoupon;
	}

}

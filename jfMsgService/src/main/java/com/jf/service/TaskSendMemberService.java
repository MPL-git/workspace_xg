package com.jf.service;

import cn.jpush.api.push.PushResult;
import com.esms.common.entity.GsmsResponse;
import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.utils.Config;
import com.jf.common.utils.StringUtil;
import com.jf.common.utils.xwsms.XwMessageData;
import com.jf.common.utils.xwsms.XwSmsUtil;
import com.jf.common.utils.xysms.XySmsUtil;
import com.jf.dao.SysAppMessageCustomMapper;
import com.jf.dao.TaskMapper;
import com.jf.dao.TaskSendMemberCustomMapper;
import com.jf.dao.TaskSendMemberMapper;
import com.jf.entity.SysAppMessage;
import com.jf.entity.Task;
import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskMarketing;
import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberCustom;
import com.jf.entity.TaskSendMemberCustomExample;
import com.jf.entity.TaskSendMemberExample;
import com.jf.task.JpushTask;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TaskSendMemberService extends BaseService<TaskSendMember, TaskSendMemberExample> {

	private static Logger logger = LoggerFactory.getLogger(TaskSendMemberService.class);
	
	@Autowired
	private TaskSendMemberMapper taskSendMemberMapper;
	
	@Autowired
	private TaskSendMemberCustomMapper taskSendMemberCustomMapper;

	@Autowired
	private SysAppMessageCustomMapper sysAppMessageCustomMapper;

	@Autowired
	private TaskMapper taskMapper;

	private static Integer EXCEPTION_NUM = 0;
	
	@Autowired
	public void setTaskSendMemberMapper(TaskSendMemberMapper taskSendMemberMapper) {
		this.setDao(taskSendMemberMapper);
		this.taskSendMemberMapper = taskSendMemberMapper;
	}

	public int countByCustomExample(TaskSendMemberCustomExample example) {
		return taskSendMemberCustomMapper.countByCustomExample(example);
	}

	public List<TaskSendMemberCustom> selectByCustomExample(TaskSendMemberCustomExample example) {
		return taskSendMemberCustomMapper.selectByCustomExample(example);
	}

	public TaskSendMemberCustom selectByCustomPrimaryKey(Integer id) {
		return taskSendMemberCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") TaskSendMember record, @Param("example") TaskSendMemberCustomExample example) {
		return taskSendMemberCustomMapper.updateByCustomExampleSelective(record, example);
	}

	public List<Map<String, Object>> sendSmsXwSendList(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.sendSmsXwSendList(paramMap);
	}

	public List<Map<String, Object>> sendSmsXwTaskList() {
		return taskSendMemberCustomMapper.sendSmsXwTaskList();
	}

	public List<Map<String,Object>> sendSmsXYTaskList() {
		return taskSendMemberCustomMapper.sendSmsXYTaskList();
	}

	public List<Map<String,Object>> sendSmsTaskListByChannel(String smsChannel) {
		return taskSendMemberCustomMapper.sendSmsTaskListByChannel(smsChannel);
	}

	public List<Map<String, Object>> sendSmsXYSendList(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.sendSmsXYSendList(paramMap);
	}

	public List<Map<String, Object>> sendSmsSendListByChannel(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.sendSmsSendListByChannel(paramMap);
	}

	public int updateTaskSendMemberList(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.updateTaskSendMemberList(paramMap);
	}
	
	public int updateTaskSendMember(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.updateTaskSendMember(paramMap);
	}

	public int xyNotifyTaskSendMember(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.xyNotifyTaskSendMember(paramMap);
	}
	
	public List<Task> sendTaskList(Map<String, Object> paramMap) {
		return taskSendMemberCustomMapper.sendTaskList(paramMap);
	}
	
	public void updateTaskStatus(Integer taskId,String status){
		taskSendMemberCustomMapper.updateTaskStatus(taskId,status);
	}

	public void updateTaskKeySelective(Task task){
		taskMapper.updateByPrimaryKeySelective(task);
	}
	
	public void sendTaskXYSmsList(List<Map<String, Object>> resultMapList) {
		String taskSendIds = "";
		String mobiles = "";
		for (Map<String, Object> stringObjectMap : resultMapList) {
			taskSendIds += stringObjectMap.get("taskSendId")+",";
			mobiles += stringObjectMap.get("mobile")+",";
		}
		String content = formatXySmsContent(resultMapList.get(0).get("content").toString());
		String sendResult = XySmsUtil.SendXySms(mobiles.substring(0, mobiles.length() - 1), content, "", "");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("taskSendIdList", taskSendIds.substring(0, taskSendIds.length() - 1).split(","));
		paramMap.put("seqNum", sendResult);
		if (!sendResult.startsWith("-") && sendResult.length() > 0) { //成功
			logger.info("上海歆阳短信发送成功 返回值:" + sendResult);
			paramMap.put("status", "1");
		} else {
			logger.info("上海歆阳短信发送失败 返回值:" + sendResult);
			paramMap.put("status", "3");
		}
		taskSendMemberCustomMapper.updateTaskSendMemberList(paramMap);

	}

	private String formatXySmsContent(String content) {
		char[] charArray = content.toCharArray();
		StringBuffer stringBuffer = new StringBuffer();
		for (char c : charArray) {
			if (' ' == c) {
				stringBuffer.append("&#032;");
			}else if('¥' == c){
				stringBuffer.append("&#165;");
			}else if('<' == c){
				stringBuffer.append("&#060;");
			}else if('>' == c){
				stringBuffer.append("&#062;");
			}else if('&' == c){
				stringBuffer.append("&#038;");
			}else {
				stringBuffer.append(c);
			}
		}
		return Const.MESSAGE_SIGNATURES + stringBuffer.toString();
	}

	public void sendTaskSmsList(List<Map<String, Object>> resultMapList) {
		List<Integer> taskSendIdList = new ArrayList<Integer>();
		List<XwMessageData> xwMessageDataList = new ArrayList<XwMessageData>();
		for(Map<String, Object> map : resultMapList) {
			taskSendIdList.add(Integer.parseInt(map.get("taskSendId").toString()));
			XwMessageData xwMessageData = new XwMessageData();
			xwMessageData.setPhone(map.get("mobile").toString());
			xwMessageData.setContent(map.get("content").toString());
			xwMessageData.setCustomMsgID(Const.customMsgIDHead+"-"+map.get("taskSendId")+"-"+Const.customMsgIDTail);
			xwMessageDataList.add(xwMessageData);
		}
		GsmsResponse gsmsResponse = XwSmsUtil.sendSmsXw(xwMessageDataList);
		if(gsmsResponse != null ) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("taskSendIdList", taskSendIdList);
			paramMap.put("oldStatus", "0");
			if(0 == gsmsResponse.getResult() ) { //成功
				paramMap.put("status", "1");
				paramMap.put("customMsgIDHead", Const.customMsgIDHead);
				paramMap.put("customMsgIDTail", Const.customMsgIDTail);
			}else {
				paramMap.put("status", "3");
				paramMap.put("customMsgIDHead", null);
				paramMap.put("customMsgIDTail", null);
			}
			taskSendMemberCustomMapper.updateTaskSendMemberList(paramMap);
		}
	}
	
	public Integer sendTaskMarketing(Task task, TaskMarketing taskMarketing, Integer currentPage, Integer pageSize, Integer startNum) {
		
		logger.info("营销中心极光任务推送: pageSize{}", pageSize);

		try {
			TaskSendMemberExample taskSendMemberExample = new TaskSendMemberExample();
			taskSendMemberExample.createCriteria().andDelFlagEqualTo("0").andMemberIdIsNotNull()
				.andTaskIdEqualTo(task.getId()).andStatusEqualTo("0").andSendCountLessThan(3);
			taskSendMemberExample.setLimitStart(currentPage);
			taskSendMemberExample.setLimitSize(pageSize);
			List<TaskSendMember> taskSendMemberList = taskSendMemberMapper.selectByExample(taskSendMemberExample);
			if(CollectionUtils.isNotEmpty(taskSendMemberList) ) { 
				logger.info("扫描到的推送用户：{}", taskSendMemberList.size());
				PushResult result = null;
				List<Integer> taskSendIdList = new ArrayList<Integer>();
				List<String> aliasList = new ArrayList<String>();
				Map<String, String> extras = new HashMap<String, String>();
				String alert = EmojiParser.parseToUnicode(task.getContent()==null?"":task.getContent());
				for (TaskSendMember taskSendMember : taskSendMemberList) {
					taskSendIdList.add(taskSendMember.getId());
					aliasList.add(taskSendMember.getMemberId().toString());
				}
				String linkType = taskMarketing.getLinkType();
				extras.put("source", ("108".equals(linkType) || "109".equals(linkType))?"":linkType);
				if("7".equals(linkType) || "107".equals(linkType) ) {
					extras.put("id", "");
					extras.put("activityAreaId", "");
					extras.put("remark1", taskMarketing.getLinkUrl()==null?"":taskMarketing.getLinkUrl());
				}else if("1".equals(linkType) ) {
					extras.put("id", "");
					extras.put("activityAreaId", taskMarketing.getLinkUrl()==null?"":taskMarketing.getLinkUrl());
					extras.put("remark1", "");
				}else{
					extras.put("id", taskMarketing.getLinkUrl()==null?"":taskMarketing.getLinkUrl());
					extras.put("activityAreaId", "");
					extras.put("remark1", "");
				}
				extras.put("activityId", "");
				extras.put("remark2", "");
				extras.put("remark3", "");
				if(!StringUtil.isEmpty(taskMarketing.getIosContext()) ) {
					boolean iosApns = true;
					if("0".equals(taskMarketing.getIosContext()) ) {
						iosApns = false;
					}
					extras.put("title", EmojiParser.parseToUnicode(taskMarketing.getIosTitle()==null?"":taskMarketing.getIosTitle()));
					extras.put("name", EmojiParser.parseToUnicode(taskMarketing.getIosTitle()==null?"":taskMarketing.getIosTitle()));
					if(CollectionUtils.isNotEmpty(aliasList) ) {
						logger.info("极光任务Ios推送: start");
						startNum++;
						result = JpushTask.sendPlatformAndAlias(alert, aliasList, extras, iosApns, Const.PLATFORM_IOS, taskMarketing.getBigPushDuration());
						logger.info("极光任务Ios推送: end");
					}
				}
				if(!StringUtil.isEmpty(taskMarketing.getAndroidContext()) ) {
					boolean androidApns = true;
					if("0".equals(taskMarketing.getIosContext()) ) {
						androidApns = false;
					}
					extras.put("title", EmojiParser.parseToUnicode(taskMarketing.getAndroidTitle()==null?"":taskMarketing.getAndroidTitle()));
					extras.put("name", EmojiParser.parseToUnicode(taskMarketing.getAndroidTitle()==null?"":taskMarketing.getAndroidTitle()));
					if(CollectionUtils.isNotEmpty(aliasList) ) {
						logger.info("极光任务Android推送: start");
						result = JpushTask.sendPlatformAndAlias(alert, aliasList, extras, androidApns, Const.PLATFORM_ANDROID, taskMarketing.getBigPushDuration());
						logger.info("极光任务Android推送: end");
					}
				}
				Map<String, Object> paramMap = new HashMap<String, Object>();
				if(result != null ) {
					if(result.statusCode == 0 ) {
						paramMap.put("status", "2");
					}else {
						paramMap.put("status", "3");
					}
					paramMap.put("seqNum", result.msg_id);
					paramMap.put("commitResult", result.sendno);
					paramMap.put("resultStatus", result.statusCode);
					paramMap.put("oldStatus", "0");
					paramMap.put("taskId", task.getId());
					paramMap.put("taskSendIdList", taskSendIdList);
					paramMap.put("resultDate", "now");
					
					taskSendMemberCustomMapper.updateTaskSendMemberList(paramMap);
				}
				if(taskSendMemberList.size() < pageSize){
					return startNum;
				}else if(startNum <= Const.MAX_PAGE ){
					startNum = sendTaskMarketing(task, taskMarketing, currentPage, pageSize, startNum);
				}else {
					return startNum;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(EXCEPTION_NUM > 2){ //异常超过3次直接跳出不跑了
				
				logger.info("营销中心极光任务推送===========================>异常超过3次直接跳出不跑了");
				
				return startNum;
			}
			EXCEPTION_NUM++;
			startNum = sendTaskMarketing(task, taskMarketing, currentPage, pageSize, startNum);
		}
		return startNum;
	}
	
	public int sendTaskActivitySelection(Task task, TaskActivitySelection taskActivitySelection, SysAppMessage sysAppMessage, Boolean flag, int pushNumber, Integer currentPage, Integer pageSize) {
		
		logger.info("极光任务推送: pageSize{}", pageSize);
		
		try {
			TaskSendMemberExample taskSendMemberExample = new TaskSendMemberExample();
			taskSendMemberExample.createCriteria().andDelFlagEqualTo("0").andMemberIdIsNotNull()
			.andTaskIdEqualTo(task.getId()).andStatusEqualTo("0").andSendCountLessThan(3);
			taskSendMemberExample.setLimitStart(currentPage);
			taskSendMemberExample.setLimitSize(pageSize);
			List<TaskSendMember> taskSendMemberList = taskSendMemberMapper.selectByExample(taskSendMemberExample);
			if(CollectionUtils.isNotEmpty(taskSendMemberList) ) {
				PushResult result = null;
				List<Integer> taskSendIdList = new ArrayList<Integer>();
				if(pushNumber <= Const.MAX_PAGE){
					if(flag){
						sysAppMessageCustomMapper.batchInsertSysAppMessageByTaskActivitySelectionId(taskSendMemberList, sysAppMessage);
					}
					logger.info("扫描到的推送用户：{}", taskSendMemberList.size());

					List<String> aliasList = new ArrayList<String>();
					Map<String, String> extras = new HashMap<String, String>();
					String alert = task.getContent();
					for (TaskSendMember taskSendMember : taskSendMemberList) {
						taskSendIdList.add(taskSendMember.getId());
						aliasList.add(taskSendMember.getMemberId().toString());
					}
					extras.put("source", "107");
					extras.put("id", "");
					extras.put("activityAreaId", "");
					String linkValue = Config.getProperty("mUrl")+"/xgbuy/views/activity/nest/decorate/brand_decorate_activesel.html?id="+taskActivitySelection.getId();
					extras.put("remark1", linkValue);
					extras.put("activityId", "");
					extras.put("remark2", "");
					extras.put("remark3", "");
					extras.put("title", task.getName());
					extras.put("name", task.getName());
					logger.info("任务精选极光推送: start");

					pushNumber++;
					result = JpushTask.sendPlatformAndAlias(alert, aliasList, extras, true, Const.PLATFORM_ALL, null);
				}else {
					return pushNumber;
				}
				logger.info("任务精选极光推送: end");

				Map<String, Object> paramMap = new HashMap<String, Object>();
				if(result != null ) {
					if(result.statusCode == 0 ) {
						paramMap.put("status", "2");
					}else {
						paramMap.put("status", "3");
					}
					paramMap.put("seqNum", result.msg_id);
					paramMap.put("commitResult", result.sendno);
					paramMap.put("resultStatus", result.statusCode);
					paramMap.put("oldStatus", "0");
					paramMap.put("taskId", task.getId());
					paramMap.put("taskSendIdList", taskSendIdList);
					paramMap.put("resultDate", "now");
					taskSendMemberCustomMapper.updateTaskSendMemberList(paramMap);
				}
				if(taskSendMemberList.size() < pageSize){
                    this.updateTaskStatus( task.getId(),"4");
					return pushNumber;
				}else{
					pushNumber = sendTaskActivitySelection(task, taskActivitySelection, sysAppMessage, true, pushNumber, currentPage, pageSize);
				}
			}else {
                this.updateTaskStatus( task.getId(),"4");
            }
			return pushNumber;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(EXCEPTION_NUM > 2){ //异常超过3次直接跳出不跑了
				
				logger.info("任务精选极光任务推送===========================>异常超过3次直接跳出不跑了");
				
				return pushNumber;
			}
			EXCEPTION_NUM++;
			pushNumber = sendTaskActivitySelection(task, taskActivitySelection, sysAppMessage,false, pushNumber, currentPage, pageSize);
		}

		return pushNumber;
	}


}

package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.utils.StringUtils;
import com.jf.dao.MemberInfoMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.dao.TaskSendMemberCustomMapper;
import com.jf.dao.TaskSendMemberMapper;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberInfoExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.TaskSendMember;
import com.jf.entity.TaskSendMemberCustom;
import com.jf.entity.TaskSendMemberCustomExample;
import com.jf.entity.TaskSendMemberExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class TaskSendMemberService extends BaseService<TaskSendMember, TaskSendMemberExample> {

	@Autowired
	private TaskSendMemberMapper taskSendMemberMapper;
	
	@Autowired
	private TaskSendMemberCustomMapper taskSendMemberCustomMapper;

	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	public void setTaskSendMemberMapper(TaskSendMemberMapper taskSendMemberMapper) {
		super.setDao(taskSendMemberMapper);
		this.taskSendMemberMapper = taskSendMemberMapper;
	}
	
	public int countByCustomExample(TaskSendMemberCustomExample example) {
		return taskSendMemberCustomMapper.countByCustomExample(example);
	}

    public List<TaskSendMemberCustom> selectByCustomExample(TaskSendMemberCustomExample example) {
    	return taskSendMemberCustomMapper.selectByCustomExample(example);
    }
    
    public List<TaskSendMemberCustom> getTaskSendMemberList(TaskSendMemberCustomExample example, String firstSendDate) {
    	List<TaskSendMemberCustom> taskSendMemberCustomList = taskSendMemberCustomMapper.selectByCustomExample(example);
    	String taskSendCount = "";
    	String taskSendAmount = "0";
    	if(taskSendMemberCustomList != null && taskSendMemberCustomList.size() > 0 ) {
    		if(Const.XW_SMS_SEND_CHANNEL.equals(taskSendMemberCustomList.get(0).getTaskSendChannel()) ) {
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XW_SEND_PRICE);
				List<SysParamCfg> sysParamCfgList = sysParamCfgMapper.selectByExample(sysParamCfgExample);
				if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
					taskSendAmount = sysParamCfgList.get(0).getParamValue();
				}
				if(!StringUtils.isEmpty(taskSendMemberCustomList.get(0).getTaskContent()) && taskSendMemberCustomList.get(0).getTaskContent().length() > 66 && taskSendMemberCustomList.get(0).getTaskContent().length() <= 136 ) {
					taskSendCount = "2";
				}else if(!StringUtils.isEmpty(taskSendMemberCustomList.get(0).getTaskContent()) && taskSendMemberCustomList.get(0).getTaskContent().length() > 136 ){
					taskSendCount = "3";
				}else{
					taskSendCount = "1";
				}
				taskSendAmount = new BigDecimal(taskSendCount).multiply(new BigDecimal(taskSendAmount)).toString();
			}else if(Const.XY_SMS_SEND_CHANNEL.equals(taskSendMemberCustomList.get(0).getTaskSendChannel()) ) {
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.XY_SEND_PRICE);
				List<SysParamCfg> sysParamCfgList = sysParamCfgMapper.selectByExample(sysParamCfgExample);
				if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
					taskSendAmount = sysParamCfgList.get(0).getParamValue();
				}
				if(!StringUtils.isEmpty(taskSendMemberCustomList.get(0).getTaskContent()) && taskSendMemberCustomList.get(0).getTaskContent().length() > 66 && taskSendMemberCustomList.get(0).getTaskContent().length() <= 134 ) {
					taskSendCount = "2";
				}else if(!StringUtils.isEmpty(taskSendMemberCustomList.get(0).getTaskContent()) && taskSendMemberCustomList.get(0).getTaskContent().length() > 134 ){
					taskSendCount = "3";
				}else{
					taskSendCount = "1";
				}
				taskSendAmount = new BigDecimal(taskSendCount).multiply(new BigDecimal(taskSendAmount)).toString();
			}else if(Const.MW_SMS_SEND_CHANNEL.equals(taskSendMemberCustomList.get(0).getTaskSendChannel()) ) {
				SysParamCfgExample sysParamCfgExample = new SysParamCfgExample();
				sysParamCfgExample.createCriteria().andParamCodeEqualTo(Const.MW_SEND_PRICE);
				List<SysParamCfg> sysParamCfgList = sysParamCfgMapper.selectByExample(sysParamCfgExample);
				if(sysParamCfgList != null && sysParamCfgList.size() > 0 ) {
					taskSendAmount = sysParamCfgList.get(0).getParamValue();
				}
				if (!StringUtils.isEmpty(taskSendMemberCustomList.get(0).getTaskContent())) {
					int contentLength = taskSendMemberCustomList.get(0).getTaskContent().length();
					if(contentLength <= 66){
						taskSendCount = "1";
					}else if(contentLength <= 130){
						taskSendCount = "2";
					}else if(contentLength <= 197){
						taskSendCount = "3";
					}else{
						taskSendCount = "4";
					}
				}else {
					taskSendCount = "1";
				}
				taskSendAmount = new BigDecimal(taskSendCount).multiply(new BigDecimal(taskSendAmount)).toString();
			}
    	}
    	for(TaskSendMemberCustom taskSendMemberCustom : taskSendMemberCustomList ) {
    		taskSendMemberCustom.setTaskSendCount(taskSendCount);
    		taskSendMemberCustom.setTaskSendAmount(new BigDecimal(taskSendAmount));
    		Map<String, Object> paramMap = new HashMap<String, Object>();
    		paramMap.put("sendType", taskSendMemberCustom.getTaskSendType());
			paramMap.put("sendMemberId", taskSendMemberCustom.getId());
			paramMap.put("firstSendDate", firstSendDate);
			taskSendMemberCustom.setMemberLoginDate(taskSendMemberCustomMapper.getMemberLoginDate(paramMap));
			taskSendMemberCustom.setCombineOrderCount(taskSendMemberCustomMapper.getCombineOrderCount(paramMap));
    		if("0".equals(taskSendMemberCustom.getTaskSendType()) ) { //0 用户ID
    			MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(taskSendMemberCustom.getMemberId());
    			if(memberInfo != null ) {
    				taskSendMemberCustom.setMemberNick(memberInfo.getNick());
    				taskSendMemberCustom.setMobile(memberInfo.getMobile());
    			}
    		}else if("1".equals(taskSendMemberCustom.getTaskSendType()) ) { //1 手机号
    			MemberInfoExample memberInfoExample = new MemberInfoExample();
    			memberInfoExample.createCriteria().andDelFlagEqualTo("0").andMobileEqualTo(taskSendMemberCustom.getMobile());
    			memberInfoExample.setLimitSize(1);
    			List<MemberInfo> memberInfoList = memberInfoMapper.selectByExample(memberInfoExample);
    			if(memberInfoList != null && memberInfoList.size() > 0 ) {
    				MemberInfo memberInfo = memberInfoList.get(0);
    				taskSendMemberCustom.setMemberNick(memberInfo.getNick());
        			taskSendMemberCustom.setMemberId(memberInfo.getId());
    			}
    		}
    	}
    	return taskSendMemberCustomList;
    }

    public TaskSendMemberCustom selectByCustomPrimaryKey(Integer id) {
    	return taskSendMemberCustomMapper.selectByCustomPrimaryKey(id);
    }

    public int updateByCustomExampleSelective(@Param("record") TaskSendMember record, @Param("example") TaskSendMemberCustomExample example) {
    	return taskSendMemberCustomMapper.updateByCustomExampleSelective(record, example);
    }

    public int addTaskSendMember(@Param("sendType")String sendType, @Param("sendValueSet")Set<String> sendValueSet, @Param("taskSendMember")TaskSendMember taskSendMember) {
    	return taskSendMemberCustomMapper.addTaskSendMember(sendType, sendValueSet, taskSendMember);
    }
    
    public int getTaskMemberLoginCount(Map<String, Object> paramMap) {
    	return taskSendMemberCustomMapper.getCombineOrderCount(paramMap);
    }
    
    public int getTaskMemberOrderCount(Map<String, Object> paramMap) {
    	return taskSendMemberCustomMapper.getTaskCombineOrderCount(paramMap);
    }
    
    public int getTaskCombineOrderCount(Map<String, Object> paramMap) {
    	return taskSendMemberCustomMapper.getTaskCombineOrderCount(paramMap);
    }
    
    public Date getMemberLoginDate(Map<String, Object> paramMap) {
    	return taskSendMemberCustomMapper.getMemberLoginDate(paramMap);
    }
    
    public int getCombineOrderCount(Map<String, Object> paramMap) {
    	return taskSendMemberCustomMapper.getCombineOrderCount(paramMap);
    }
    
	
}

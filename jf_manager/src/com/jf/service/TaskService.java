package com.jf.service;

import com.jf.common.constant.Const;
import com.jf.common.ext.util.StrKit;
import com.jf.common.utils.ExcelUtils;
import com.jf.common.utils.LargeExcelFileReadUtil;
import com.jf.common.utils.StringUtils;
import com.jf.controller.TaskController;
import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.dao.MemberInfoCustomMapper;
import com.jf.dao.MemberLabelGroupRelationMapper;
import com.jf.dao.MemberLabelRelationCustomMapper;
import com.jf.dao.MemberLabelRelationMapper;
import com.jf.dao.SysAppMessageCustomMapper;
import com.jf.dao.TaskActivitySelectionMapper;
import com.jf.dao.TaskCustomMapper;
import com.jf.dao.TaskLogMapper;
import com.jf.dao.TaskMapper;
import com.jf.dao.TaskSendMemberCustomMapper;
import com.jf.dao.TaskSendMemberMapper;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;
import com.jf.entity.MemberLabelGroupRelation;
import com.jf.entity.MemberLabelGroupRelationExample;
import com.jf.entity.MemberLabelRelation;
import com.jf.entity.MemberLabelRelationCustom;
import com.jf.entity.MemberLabelRelationCustomExample;
import com.jf.entity.MemberLabelRelationExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.Task;
import com.jf.entity.TaskActivitySelection;
import com.jf.entity.TaskActivitySelectionExample;
import com.jf.entity.TaskCustom;
import com.jf.entity.TaskCustomExample;
import com.jf.entity.TaskExample;
import com.jf.entity.TaskLog;
import com.jf.entity.TaskSendMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;




@Service
@Transactional
public class TaskService extends BaseService<Task, TaskExample>{
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	
	@Autowired
	private TaskCustomMapper taskCustomMapper;
	
	@Autowired
	private TaskActivitySelectionMapper taskActivitySelectionMapper;
	
	@Autowired
	private TaskLogMapper taskLogMapper;
	
	@Autowired
	private TaskSendMemberMapper taskSendMemberMapper;
	
	@Autowired
	private MemberLabelGroupRelationMapper memberLabelGroupRelationMapper;
	
	@Autowired
	private MemberLabelRelationMapper memberLabelRelationMapper;
	
	@Autowired
	private MemberLabelRelationCustomMapper memberLabelRelationCustomMapper;
	
	@Autowired
	private TaskSendMemberCustomMapper taskSendMemberCustomMapper;
	
	@Autowired
	private SysAppMessageCustomMapper sysAppMessageCustomMapper;

	@Autowired
	private MemberInfoCustomMapper memberInfoCustomMapper;

	@Autowired
	private SysParamCfgService sysParamCfgService;

	@Autowired
	public void setTaskMapper(TaskMapper taskMapper) {
		super.setDao(taskMapper);
		this.taskMapper = taskMapper;
	}
	
	public void auditTask(Task task, TaskLog taskLog) {
		taskMapper.updateByPrimaryKeySelective(task);
		taskLogMapper.insertSelective(taskLog);
		if("4".equals(task.getStatus()) ) {
			addTaskSendMemeber(task);
		}
	}
	
	public void addTaskSendMemeber(Task task) {
		try {
			String sendType = task.getSendType();
			String filePath = task.getFilePath();
			String sendValues = task.getSendValues();
			String labelGroupIds = task.getLabelGroupIds();
			Set<String> set = new HashSet<String>();
			if(!"2".equals(sendType) ) {
				if(!StringUtils.isEmpty(filePath) ) {
					InputStream stream = TaskController.class.getResourceAsStream("/base_config.properties");
					Properties properties = new Properties();
					properties.load(stream);
					String defaultPath = properties.getProperty("annex.filePath");
					stream.close();
					File file = new File(defaultPath+filePath);
					List<ArrayList<String>> dataList;
					dataList = ExcelUtils.setCellRead(file, file.getName(), 1, "0", 1);
					//1表示取excel表格的前1列
					for (ArrayList<String> arrayList : dataList) {
						set.add(arrayList.get(0));
					}
				}
				if(!StringUtils.isEmpty(sendValues) ) {
					String[] vals = sendValues.split(",");
					for(String val : vals ) {
						set.add(val);
					}
				}
				if(!StringUtils.isEmpty(labelGroupIds) ) {
					if("0".equals(sendType) ) { //用户ID
						List<Integer> groupIdList = new ArrayList<Integer>();
						String[] groupIds = labelGroupIds.split(",");
						for(String groupId : groupIds) {
							groupIdList.add(Integer.parseInt(groupId));
						}
						if(groupIdList.size() > 0 ) {
							MemberLabelGroupRelationExample memberLabelGroupRelationExample = new MemberLabelGroupRelationExample();
							memberLabelGroupRelationExample.createCriteria().andDelFlagEqualTo("0")
								.andTypeEqualTo("0").andLabelGroupIdIn(groupIdList);
							List<MemberLabelGroupRelation> memberLabelGroupRelationList = memberLabelGroupRelationMapper.selectByExample(memberLabelGroupRelationExample);
							List<Integer> labelIdList = new ArrayList<Integer>();
							List<Integer> labelTypeIdList = new ArrayList<Integer>();
							for(MemberLabelGroupRelation memberLabelGroupRelation : memberLabelGroupRelationList) {
								labelIdList.add(memberLabelGroupRelation.getLabelId());
								labelTypeIdList.add(memberLabelGroupRelation.getLabelTypeId());
							}
							if(labelTypeIdList.size() > 0 ) {
								MemberLabelRelationCustomExample memberLabelRelationCustomExample = new MemberLabelRelationCustomExample();
								MemberLabelRelationCustomExample.MemberLabelRelationCustomCriteria memberLabelRelationCustomCriteria  = memberLabelRelationCustomExample.createCriteria();
								memberLabelRelationCustomCriteria.andDelFlagEqualTo("0")
									.andLabelIdIn(labelIdList).andLabelTypeIdIn(labelTypeIdList);
								memberLabelRelationCustomCriteria.andMemberIdNotEqualTo(labelGroupIds);
								List<MemberLabelRelationCustom> memberLabelRelationCustomList = memberLabelRelationCustomMapper.selectMemberLabelRelationCustomExample(memberLabelRelationCustomExample);
								for(MemberLabelRelationCustom memberLabelRelationCustom : memberLabelRelationCustomList) {
									set.add(memberLabelRelationCustom.getMemeberId().toString());
								}
							}
						}
					}else if("1".equals(sendType) ) { //手机号
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("labelGroupId", labelGroupIds);
						List<MemberLabelRelationCustom> memberLabelRelationCustomList = memberLabelRelationCustomMapper.getMemberLabelRelationList(paramMap);
						for(MemberLabelRelationCustom memberLabelRelationCustom : memberLabelRelationCustomList) {
							if(!StringUtils.isEmpty(memberLabelRelationCustom.getMemberMobile()) ) {
								set.add(memberLabelRelationCustom.getMemberMobile());
							}
						}
					}
				}
				//需求-2045 短信必发名单
				SysParamCfg sysParamCfg;
				if("1".equals(sendType) ) {
					sysParamCfg = sysParamCfgService.findByCode("TASK_MUST_SEND_SMS_MOBILE");
				}else {
					sysParamCfg = sysParamCfgService.findByCode("TASK_MUST_SEND_SMS_MEMBERID");
				}
				if(sysParamCfg != null && StrKit.notBlank(sysParamCfg.getParamValue())){
					String[] values = sysParamCfg.getParamValue().split(",");
					set.addAll(Arrays.asList(values));
				}
				if(set.size() > 0 ) {
					TaskSendMember taskSendMember = new TaskSendMember();
					taskSendMember.setTaskId(task.getId());
					taskSendMember.setStatus("0");
					taskSendMember.setSendCount(0);
					taskSendMember.setCreateBy(task.getUpdateBy());
					taskSendMember.setCreateDate(task.getUpdateDate());
					taskSendMember.setDelFlag("0");
					if(set.size() > Const.TASK_SMS_INSERT_COUNT ) {
						Set<String> sendValueSet = new HashSet<String>();
						int i = 0;
						for(String val : set) {
							i++;
							sendValueSet.add(val);
							if(sendValueSet.size() > Const.TASK_SMS_INSERT_COUNT || i == set.size()) {
								taskSendMemberCustomMapper.addTaskSendMember(sendType, sendValueSet, taskSendMember);
								sendValueSet.clear();
							}
						}
					}else {
						taskSendMemberCustomMapper.addTaskSendMember(sendType, set, taskSendMember);
					}
				}
			}else {
				TaskSendMember taskSendMember = new TaskSendMember();
				taskSendMember.setTaskId(task.getId());
				taskSendMember.setStatus("0");
				taskSendMember.setSendCount(0);
				taskSendMember.setCreateBy(task.getUpdateBy());
				taskSendMember.setCreateDate(task.getUpdateDate());
				taskSendMember.setDelFlag("0");
				Map<String, Integer> paramMap = new HashMap<>();
				paramMap.put("limitCount", Const.TASK_SMS_LIMIT_START);
				paramMap.put("limitStart", Const.TASK_SMS_LIMIT_START);
				paramMap.put("limitSize", Const.TASK_SMS_LIMIT_SIZE);
				paramMap = getMemeberIdList(sendType, taskSendMember, paramMap);
				for(;;) {
					if(paramMap.get("limitCount") == -1) {
						break;
					}else {
						paramMap = getMemeberIdList(sendType, taskSendMember, paramMap);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Map<String, Integer> getMemeberIdList(String sendType, TaskSendMember taskSendMember, Map<String, Integer> paramMap) {
		List<Map<String, Object>> memberIdList = memberInfoCustomMapper.getMemeberIdList(paramMap);
		if(memberIdList.size() > 0) {
			if(memberIdList.size() >= paramMap.get("limitSize") ) {
				paramMap.put("limitCount", paramMap.get("limitCount")+1);
				paramMap.put("limitStart", paramMap.get("limitCount")*paramMap.get("limitSize"));
			}else {
				paramMap.put("limitCount", -1);
			}
			Set<String> sendValueSet = new HashSet<String>();
			int i = 0;
			for(Map<String, Object> memberIdMap : memberIdList) {
				i++;
				sendValueSet.add(memberIdMap.get("id").toString());
				if(sendValueSet.size() > Const.TASK_SMS_INSERT_COUNT || i == memberIdList.size()) {
					taskSendMemberCustomMapper.addTaskSendMember(sendType, sendValueSet, taskSendMember);
					sendValueSet.clear();
				}
			}
		}else{
			paramMap.put("limitCount", -1);
		}
		return paramMap;
	}

	public int countByExample(TaskCustomExample example){
		return taskCustomMapper.countByExample(example);
	}
	
	public List<TaskCustom> selectByExample(TaskCustomExample example){
		return taskCustomMapper.selectByExample(example);
	}
	
	public List<TaskCustom> detailAuditSelectByExample(TaskCustomExample example){
		return taskCustomMapper.detailAuditSelectByExample(example);
	}
	
	public void editTask(Task task,TaskLog taskLog){
		taskMapper.updateByPrimaryKeySelective(task);
		taskLogMapper.insertSelective(taskLog);
	}

	public void confirmTaskEdit(Task task,Integer staffID,String coverPic){
		Date date = new Date();
		if(!StringUtils.isEmpty(task.getId())){
			//修改task
			task.setStatus("0");//状态变为待提审
			task.setTimeInterval(5);
			task.setSendCount(5000);
			task.setUpdateBy(staffID);
			task.setUpdateDate(date);
			taskMapper.updateByPrimaryKeySelective(task);
			//修改taskActivitySelection
			TaskActivitySelection taskActivitySelection = new TaskActivitySelection();
			TaskActivitySelectionExample taskActivitySelectionExample = new TaskActivitySelectionExample();
			taskActivitySelectionExample.createCriteria().andTaskIdEqualTo(task.getId()).andDelFlagEqualTo("0");
			taskActivitySelection.setUpdateBy(staffID);
			taskActivitySelection.setUpdateDate(date);
			taskActivitySelection.setCoverPic(coverPic);
			taskActivitySelectionMapper.updateByExampleSelective(taskActivitySelection, taskActivitySelectionExample);
			//存入buTaskLog
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(task.getId());
			taskLog.setOperatorType("0");
			taskLog.setStatus("0");
			taskLog.setCreateBy(staffID);
			taskLog.setCreateDate(date);
			taskLog.setDelFlag("0");
			taskLogMapper.insertSelective(taskLog);
		}else{
			//创建装修信息和分区
			DecorateInfo decorateInfo = new DecorateInfo();
			decorateInfo.setDelFlag("0");
			decorateInfo.setCreateDate(date);
			decorateInfo.setDecorateName(task.getName());
			decorateInfoMapper.insertSelective(decorateInfo);
			DecorateArea decorateArea = new DecorateArea();
			decorateArea.setDelFlag("0");
			decorateArea.setCreateDate(date);
			decorateArea.setDecorateInfoId(decorateInfo.getId());
			decorateArea.setRemarks(task.getName());
			decorateAreaMapper.insertSelective(decorateArea);
			//创建task
			task.setStatus("0");//状态变为待提审
			task.setType("2");//任务精选
			task.setSendType("0");//发送类型:用户ID
			task.setSendChannel("1");//极光推送
			task.setCreateBy(staffID);
			task.setCreateDate(date);
			task.setDelFlag("0");
			taskMapper.insertSelective(task);
			Integer trakId = task.getId();
			//创建taskActivitySelection
			TaskActivitySelection taskActivitySelection = new TaskActivitySelection();
			taskActivitySelection.setTaskId(trakId);
			taskActivitySelection.setCreateBy(staffID);
			taskActivitySelection.setCreateDate(date);
			taskActivitySelection.setCoverPic(coverPic);
			taskActivitySelection.setDecorateInfoId(decorateInfo.getId());
			taskActivitySelection.setDelFlag("0");
			taskActivitySelectionMapper.insertSelective(taskActivitySelection);
			//存入buTaskLog
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(trakId);
			taskLog.setOperatorType("0");
			taskLog.setStatus("0");
			taskLog.setCreateBy(staffID);
			taskLog.setCreateDate(date);
			taskLog.setDelFlag("0");
			taskLogMapper.insertSelective(taskLog);
		}
	}
	
	public void confirmTaskAudit(Task task,String type,String auditStatus,String statusRemarks,Integer staffID){
		Date date = new Date();
		if(type.equals("1")){//运营审核
			if(auditStatus.equals("1")){
				task.setStatus("2");
			}else if (auditStatus.equals("2")) {
				task.setStatus("5");
			}
			task.setUpdateBy(staffID);
			task.setUpdateDate(date);
			taskMapper.updateByPrimaryKeySelective(task);
			
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(task.getId());
			taskLog.setOperatorType("1");
			taskLog.setStatus(auditStatus);
			taskLog.setRemarks(statusRemarks);
			taskLog.setCreateBy(staffID);
			taskLog.setCreateDate(date);
			taskLog.setDelFlag("0");
			taskLogMapper.insertSelective(taskLog);
		}else if(type.equals("2")){//法务审核
			if(auditStatus.equals("1")){
				task.setStatus("3");
				
				HashSet<Integer> insertSet = new HashSet<Integer>();
		        //手动输入的用户ID存入set
				if(!StringUtils.isEmpty(task.getSendValues())){
					String[] arrs = task.getSendValues().split(",");
					for (String memberId : arrs) {
						insertSet.add(Integer.parseInt(memberId));
					}
				}
				//读取excel中的用户ID存入set
				if(!StringUtils.isEmpty(task.getFilePath())){
					try {
						InputStream stream = TaskService.class.getResourceAsStream("/base_config.properties");
			  	        Properties properties = new Properties();
						properties.load(stream);
					    String defaultPath = properties.getProperty("annex.filePath");
					    stream.close();
					    String fileName = task.getFilePath();
					    if("xlsx".equals(fileName.substring(fileName.lastIndexOf(".") + 1))){
					    	//数据量比较大(8万条以上)的xlsx文件解析
					        LargeExcelFileReadUtil largeExcelFileReadUtil = new LargeExcelFileReadUtil();
					        largeExcelFileReadUtil.processOneSheet(defaultPath+task.getFilePath());
					        LinkedHashMap<String, String>  map=largeExcelFileReadUtil.getRowContents();
					        Iterator<Entry<String, String>> it= map.entrySet().iterator();
					        //int count=0;
					        String prePos="";
					        Pattern pattern = Pattern.compile("[0-9]*");
					        while (it.hasNext()){
					            Map.Entry<String, String> entry=(Map.Entry<String, String>)it.next();
					            String pos=entry.getKey();
					            if(!pos.substring(1).equals(prePos)){
					                prePos=pos.substring(1);
					                //count++;
						        	if(pattern.matcher(entry.getValue()).matches()){
						        		insertSet.add(Integer.parseInt(entry.getValue()));
						        	}
					            }
					            //System.out.println(pos+";"+entry.getValue());
					        }
					        //System.out.println(count);
					    }else {
					    	File file = new File(defaultPath+task.getFilePath());
							List<ArrayList<String>> dataList = ExcelUtils.read(file, file.getName(), 1,"0");//1表示取excel表格的前1列
							for (ArrayList<String> arr : dataList) {
								Pattern pattern = Pattern.compile("[0-9]*");
					        	if(pattern.matcher(arr.get(0)).matches()){
					        		insertSet.add(Integer.parseInt(arr.get(0)));
					        	}
							}
						}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//将分组中的用户ID存入set
				if(!StringUtils.isEmpty(task.getLabelGroupIds())){
					List<Integer> groupIdList = new ArrayList<Integer>();
					String labelGroupIds = task.getLabelGroupIds();
					String[] groupIds = task.getLabelGroupIds().split(",");
 					for(String groupId : groupIds) {
						groupIdList.add(Integer.parseInt(groupId));
					}
					MemberLabelGroupRelationExample memberLabelGroupRelationExample = new MemberLabelGroupRelationExample();
					memberLabelGroupRelationExample.createCriteria().andDelFlagEqualTo("0")
						.andTypeEqualTo("0").andLabelGroupIdIn(groupIdList);
					List<MemberLabelGroupRelation> memberLabelGroupRelationList = memberLabelGroupRelationMapper.selectByExample(memberLabelGroupRelationExample);
					List<Integer> labelIdList = new ArrayList<Integer>();
					List<Integer> labelTypeIdList = new ArrayList<Integer>();
					for(MemberLabelGroupRelation memberLabelGroupRelation : memberLabelGroupRelationList) {
						labelIdList.add(memberLabelGroupRelation.getLabelId());
						labelTypeIdList.add(memberLabelGroupRelation.getLabelTypeId());
					}
					MemberLabelRelationExample memberLabelRelationExample = new MemberLabelRelationExample();
					memberLabelRelationExample.createCriteria().andDelFlagEqualTo("0")
						.andLabelIdIn(labelIdList).andLabelTypeIdIn(labelTypeIdList);
					List<MemberLabelRelation> memberLabelRelationList = memberLabelRelationMapper.selectByExample(memberLabelRelationExample);
					for(MemberLabelRelation memberLabelRelation : memberLabelRelationList) {
						insertSet.add(memberLabelRelation.getMemeberId());
					}
				}
				
				//将insert中的数据插入taskSendMember
				//创建任务发送用户表实体
				TaskSendMember taskSendMember = new TaskSendMember();
				taskSendMember.setCreateBy(staffID);
				taskSendMember.setCreateDate(date);
				taskSendMember.setTaskId(task.getId());
				taskSendMember.setStatus("0");
				taskSendMember.setDelFlag("0");
				taskSendMember.setSendDate(task.getSendDate());

				//每一万条插入一次
				int size =insertSet.size();
		        int num = (size) % 10000 == 0 ? (size / 10000) : (size / 10000 + 1);// 按每10000条记录插入
		        int start =0;
		        int end  =0;
		        List<Integer> insertList = new ArrayList<Integer>(insertSet);
		        List<Integer> itemsList = new ArrayList<Integer>();
		        for (int i = 1; i <= num; i++){
	              end=(i*10000)> size ? size :(i*10000);
	              start=(i-1)*10000;
	              itemsList = insertList.subList(start,end);
	              taskCustomMapper.batchInsertTaskSendMemberByTaskId(itemsList, taskSendMember);
		        }
			}else if (auditStatus.equals("2")) {
				task.setStatus("6");
			}
			task.setUpdateBy(staffID);
			task.setUpdateDate(date);
			taskMapper.updateByPrimaryKeySelective(task);
			
			TaskLog taskLog = new TaskLog();
			taskLog.setTaskId(task.getId());
			taskLog.setOperatorType("2");
			taskLog.setStatus(auditStatus);
			taskLog.setRemarks(statusRemarks);
			taskLog.setCreateBy(staffID);
			taskLog.setCreateDate(date);
			taskLog.setDelFlag("0");
			taskLogMapper.insertSelective(taskLog);
		}
	}

}

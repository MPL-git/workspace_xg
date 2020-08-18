package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.SysConfig;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.dao.PropertyRightComplainCustomMapper;
import com.jf.dao.PropertyRightComplainMapper;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealCustom;
import com.jf.entity.IntellectualPropertyRightCustom;
import com.jf.entity.Obligee;
import com.jf.entity.ObligeeCustom;
import com.jf.entity.ObligeePic;
import com.jf.entity.ObligeePicExample;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PropertyRightAppealLog;
import com.jf.entity.PropertyRightAppealLogCustom;
import com.jf.entity.PropertyRightAppealLogExample;
import com.jf.entity.PropertyRightAppealPic;
import com.jf.entity.PropertyRightAppealPicExample;
import com.jf.entity.PropertyRightComplain;
import com.jf.entity.PropertyRightComplainCustom;
import com.jf.entity.PropertyRightComplainCustomExample;
import com.jf.entity.PropertyRightComplainExample;
import com.jf.entity.PropertyRightComplainLogCustom;
import com.jf.entity.PropertyRightComplainLogExample;
import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;
import com.jf.entity.SysStaffRoleCustom;


@Service
@Transactional
public class PropertyRightComplainService extends BaseService<PropertyRightComplain, PropertyRightComplainExample> {
	
	private static final Log logger = LogFactory.getLog(PropertyRightComplainService.class);
	
	@Autowired
	private PropertyRightComplainMapper mapper;
	
	@Autowired
	private PropertyRightComplainCustomMapper customMapper;
	
	@Autowired
	private IntellectualPropertyRightAppealService rightAppealService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	private ObligeeService obligeeService;
	
	@Autowired
	private ObligeePicService obligeePicService;
	
	@Autowired
	private ObligeeNoticeService obligeeNoticeService;
	
	@Autowired
	private IntellectualPropertyRightService propertyRightService;
	
	@Autowired
	private PropertyRightAppealPicService propertyRightAppealPicService;
	
	@Autowired
	private PropertyRightAppealLogService propertyRightAppealLogService;
	
	@Autowired
	private PropertyRightComplainPicService propertyRightComplainPicService;
	
	@Autowired
	private PropertyRightComplainLogService propertyRightComplainLogService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MchtUserService mchtUserService;
	
	@Autowired
	public void setPropertyRightComplainMapper(PropertyRightComplainMapper propertyRightComplainMapper) {
		super.setDao(propertyRightComplainMapper);
		this.mapper = propertyRightComplainMapper;
	}
	
	public int countByExample(PropertyRightComplainExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightComplainCustom> selectCustomByExample(PropertyRightComplainCustomExample customExample){
    	return customMapper.selectByExample(customExample);
    }
    
    public PropertyRightComplainCustom selectCustomByPrimaryKey(Integer id){
    	return customMapper.selectByPrimaryKey(id);
    }
    
    public int countCustomByExample(PropertyRightComplainCustomExample customExample){
		return customMapper.countByExample(customExample);
	}
    
    /**
     * 获取处理人下拉列表
     * 
     * @param staffId
     * @return
     */
	public List<SysStaffRoleCustom> getStaffList(Integer staffId) {
		List<SysStaffRoleCustom> staffList = sysStaffRoleService.selectStaffRoleList(88);
		for (SysStaffRoleCustom sysStaffRoleCustom : staffList) {
			if (sysStaffRoleCustom.getStaffId().equals(staffId)) {
				sysStaffRoleCustom.setStatus("X");
				break;
			}
		}
		
		
		List<SysStaffRoleCustom> resultList = new ArrayList<SysStaffRoleCustom>();
		SysStaffRoleCustom firstStaff = new SysStaffRoleCustom();
		firstStaff.setStaffId(staffId);
		firstStaff.setStaffName("我自己");
		firstStaff.setStatus("A");
		
		resultList.add(firstStaff);
		resultList.addAll(staffList);
	
		return resultList;
	}
	
    /**
     * 获取详情数据
     * 
     * @param id
     * @return
     */
    public Map<String, Object> getPageData(Integer id) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	//商家申明信息
    	PropertyRightComplainCustom rightComplainCustom = customMapper.selectByPrimaryKey(id);
    	
    	if (rightComplainCustom == null) {
    		return null;
    	}
    	
    	resultMap.put("rightComplainCustom", rightComplainCustom);
    	
    	//投诉信息
    	IntellectualPropertyRightAppealCustom rightAppealCustom = rightAppealService.selectCustomByPrimaryKey(rightComplainCustom.getIntellectualPropertyRightAppealId());
    	
    	resultMap.put("rightAppealCustom", rightAppealCustom);
    	
    	// 权利人信息
    	ObligeeCustom obligeeCustom = obligeeService.selectCustomByPrimaryKey(rightAppealCustom.getObligeeId());
    	resultMap.put("obligee", obligeeCustom);
    	
    	//权利人身份证明
    	ObligeePicExample obligeePicExample = new ObligeePicExample();
    	ObligeePicExample.Criteria obligeePicCriteria = obligeePicExample.createCriteria();
    	obligeePicCriteria.andObligeeIdEqualTo(obligeeCustom.getId());
    	obligeePicCriteria.andDelFlagEqualTo("0");
    	List<ObligeePic> obligeePicList = obligeePicService.selectByExample(obligeePicExample);
    	resultMap.put("obligeePicList", obligeePicList);
    	
    	//知识产权信息
    	IntellectualPropertyRightCustom rightCustom = propertyRightService.selectCustomByPrimaryKey(rightAppealCustom.getIntellectualPropertyRightId());
    	resultMap.put("rightCustom", rightCustom);
    	
    	// 举证材料
    	PropertyRightAppealPicExample appealPicExample = new PropertyRightAppealPicExample();
    	PropertyRightAppealPicExample.Criteria appealPicCriteria = appealPicExample.createCriteria();
    	appealPicCriteria.andIntellectualPropertyRightAppealIdEqualTo(rightAppealCustom.getId());
    	appealPicCriteria.andDelFlagEqualTo("0");
    	List<PropertyRightAppealPic> appealPicList = propertyRightAppealPicService.selectByExample(appealPicExample);
    	resultMap.put("appealPicList", appealPicList);
    	
    	// 商家申诉材料
    	PropertyRightComplainPicExample complainPicExample = new PropertyRightComplainPicExample();
    	PropertyRightComplainPicExample.Criteria complainPicCriteria = complainPicExample.createCriteria();
    	complainPicCriteria.andPropertyRightComplainIdEqualTo(rightComplainCustom.getId());
    	complainPicCriteria.andDelFlagEqualTo("0");
    	List<PropertyRightComplainPic> complainPicList = propertyRightComplainPicService.selectByExample(complainPicExample);
    	resultMap.put("complainPicList", complainPicList);
    	
    	// 声明日志
    	PropertyRightComplainLogExample complainLogExample = new PropertyRightComplainLogExample();
    	PropertyRightComplainLogExample.Criteria complainLogCriteria = complainLogExample.createCriteria();
    	complainLogCriteria.andPropertyRightComplainIdEqualTo(rightComplainCustom.getId());
    	complainLogCriteria.andDelFlagEqualTo("0");
    	List<PropertyRightComplainLogCustom> complainLogList = propertyRightComplainLogService.selectCustomByExample(complainLogExample);
    	resultMap.put("complainLogList", complainLogList);
    	
    	// 投诉日志
    	PropertyRightAppealLogExample appealLogExample = new PropertyRightAppealLogExample();
    	PropertyRightAppealLogExample.Criteria appealLogCriteria = appealLogExample.createCriteria();
    	appealLogCriteria.andIntellectualPropertyRightAppealIdEqualTo(rightAppealCustom.getId());
    	appealLogCriteria.andDelFlagEqualTo("0");
    	List<PropertyRightAppealLogCustom> appealLogList = propertyRightAppealLogService.selectCustomByExample(appealLogExample);
    	resultMap.put("appealLogList", appealLogList);
    	
    	return resultMap;
    }
    
    /**
     * 更新审核状态并且发送短信以及通知
     * 
     * @param rightCustom
     */
    public void updateStatus(PropertyRightComplainCustom rightComplainCustom) {
    	if (rightComplainCustom == null || rightComplainCustom.getId() == null) {
    		throw new ArgException("提交数据错误");
    	}
    	if (StringUtil.isEmpty(rightComplainCustom.getStatus())) {
    		throw new ArgException("请选择审核状态");
    	}
    	PropertyRightComplain complain = new PropertyRightComplain();
    	complain.setId(rightComplainCustom.getId());
    	complain.setStatus(rightComplainCustom.getStatus());
    	complain.setUpdateBy(rightComplainCustom.getUpdateBy());
    	Date updateDate = new Date();
    	complain.setUpdateDate(updateDate);
    	complain.setRemarksToMcht(rightComplainCustom.getRemarksToMcht());
    	if ("4".equals(rightComplainCustom.getStatus())) {
    		complain.setRemarksToObligee(rightComplainCustom.getRemarksToObligee());
    		Date prosecutionEndDate = DateUtils.addDays(updateDate, 15);
    		complain.setProsecutionEndDate(prosecutionEndDate);
    		complain.setComplainDate(updateDate);
    	}
    	complain.setInnerRemarks(rightComplainCustom.getInnerRemarks());
    	// 更新申诉状态
    	this.mapper.updateByPrimaryKeySelective(complain);
    	
    	PropertyRightComplain propertyRightComplain = this.mapper.selectByPrimaryKey(rightComplainCustom.getId());
    	
    	IntellectualPropertyRightAppeal rightAppeal = rightAppealService.selectByPrimaryKey(propertyRightComplain.getIntellectualPropertyRightAppealId());
    	
    	// 通知商家修改
    	if ("2".equals(rightComplainCustom.getStatus())) {
    		String title = "关于产权投诉通知";
    		String content = "您的申诉的描述存在模糊，请修改后再次提交。";
    		String contentMsg = "您提交的知识产权申诉的描述存在模糊，请修改后再次提交。超时未提交视为放弃申诉。";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		
    		// 修改时 比较当前时间是否大于截止日期+2天 如果是 则截止日期基础上再加2天作为新的截止日期 
    		Date plusDate = DateUtils.addDays(updateDate, 2);
    		if (plusDate.after(rightAppeal.getComplainEndDate())) {
    			Date complainEndDate = DateUtils.addDays(rightAppeal.getComplainEndDate(), 2);
        		rightAppeal.setComplainEndDate(complainEndDate);
        		rightAppeal.setUpdateDate(updateDate);
        		rightAppeal.setUpdateBy(rightComplainCustom.getUpdateBy());
        		rightAppealService.updateByPrimaryKeySelective(rightAppeal);
    		}
    	} else if ("3".equals(rightComplainCustom.getStatus())) {//多次未按要求整改
    		// 更新投诉表的投诉状态为完成
    		rightAppeal.setStatus("2");
    		rightAppeal.setStatusDate(updateDate);
    		rightAppeal.setUpdateDate(updateDate);
    		rightAppeal.setUpdateBy(rightComplainCustom.getUpdateBy());
    		rightAppealService.updateByPrimaryKeySelective(rightAppeal);
    		
    		String title = "关于产权申诉修改通知";
    		String content = "您的申诉多次未按要求提交，平台视为您已放弃申诉。平台维持投诉处罚";
    		String contentMsg = "您的申诉多次未按要求提交，平台视为您已放弃申诉。平台维持投诉处罚";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		
			// 通知权利人
	    	obligeeNoticeService.insertRightComplainNotice(rightAppeal, "2", rightComplainCustom.getUpdateBy());
	    	
	    	// 发送信息给权利人
	    	String contentMsgToObligee = "商家未在规定时间提交声明，平台将维持对商家的处理。";
	    	this.sendMsgToObligee(rightAppeal.getObligeeId(), contentMsgToObligee);
    	} else if ("4".equals(rightComplainCustom.getStatus())) {//声明通过
    		String title = "关于产权申诉转发通知";
    		String content = "您的申诉已转发给产权权利人，请保持关注。";
    		String contentMsg = "您的申诉已转发给产权权利人，请保持关注投诉。";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		
			// 通知权利人
	    	obligeeNoticeService.insertRightComplainNotice(rightAppeal, "1", rightComplainCustom.getUpdateBy());
	    	
	    	// 发送信息给权利人
	    	String contentMsgToObligee = "商家已发布声明，如有异议您可以向有关主管部门投诉或者向人民法院起诉并登入平台填写相关信息。";
	    	this.sendMsgToObligee(rightAppeal.getObligeeId(), contentMsgToObligee);
	    	
	    	//知识产权投诉日志表插入记录
			PropertyRightAppealLog propertyRightAppealLog = new PropertyRightAppealLog();
			String appealLogContent = "发起声明";
			propertyRightAppealLog.setContent(appealLogContent);
			propertyRightAppealLog.setIntellectualPropertyRightAppealId(rightAppeal.getId());
			propertyRightAppealLog.setOperatorType("3");
			propertyRightAppealLog.setCreateDate(new Date());
			propertyRightAppealLog.setDelFlag("0");
			propertyRightAppealLogService.insert(propertyRightAppealLog);
    	}
    }
    
    private void noticeMcht(Integer mchtId, String title, String content, String contentMsg) {
    	// 通知商家站内信
    	PlatformMsg platformMsg = new PlatformMsg();
    	platformMsg.setMchtId(mchtId);
    	platformMsg.setMsgType("CQ");
    	platformMsg.setTitle(title);
    	platformMsg.setContent(content);
    	platformMsg.setStatus("0");
    	platformMsg.setCreateDate(new Date());
    	platformMsg.setDelFlag("0");
    	platformMsgService.insertSelective(platformMsg);
    	
		// 发送信息给商家端主账号
		String mobile = mchtUserService.selectMobileByMchtId(mchtId);
		JSONObject paramToMcht = new JSONObject();
		JSONObject reqDataToMcht = new JSONObject();
		reqDataToMcht.put("mobile", mobile);
		reqDataToMcht.put("content", contentMsg);
		reqDataToMcht.put("smsType", "4");
		paramToMcht.put("reqData", reqDataToMcht);
		JSONObject resultToMcht = JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(paramToMcht).toString()));
		if(!"0000".equals(resultToMcht.getString("returnCode"))){
			logger.info("知识产权投诉声明转发，短信发送商家失败！！！！！");
		}
    }
    
    private void sendMsgToObligee(Integer obligeeId, String contentMsg) {
    	// 发送信息给权利人
    	Obligee obligee = obligeeService.selectByPrimaryKey(obligeeId);
    	JSONObject param = new JSONObject();
		JSONObject reqData = new JSONObject();
		reqData.put("mobile", obligee.getMobile());
		reqData.put("content", contentMsg);
		reqData.put("smsType", "4");
		param.put("reqData", reqData);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
		if(!"0000".equals(result.getString("returnCode"))){
			logger.info("知识产权投诉声明转发，短信发送权利人失败！！！！！");
		}
	}
}

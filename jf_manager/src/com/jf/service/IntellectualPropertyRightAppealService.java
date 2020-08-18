package com.jf.service;

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

import com.gzs.common.utils.DateUtil;
import com.gzs.common.utils.StringUtil;
import com.jf.common.constant.SysConfig;
import com.jf.common.exception.ArgException;
import com.jf.common.utils.CommonUtil;
import com.jf.common.utils.HttpUtil;
import com.jf.dao.IntellectualPropertyRightAppealCustomMapper;
import com.jf.dao.IntellectualPropertyRightAppealMapper;
import com.jf.entity.IntellectualPropertyRight;
import com.jf.entity.IntellectualPropertyRightAppeal;
import com.jf.entity.IntellectualPropertyRightAppealCustom;
import com.jf.entity.IntellectualPropertyRightAppealCustomExample;
import com.jf.entity.IntellectualPropertyRightAppealExample;
import com.jf.entity.IntellectualPropertyRightCustom;
import com.jf.entity.MchtInfo;
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
import com.jf.entity.SysStaffRoleCustom;


@Service
@Transactional
public class IntellectualPropertyRightAppealService extends BaseService<IntellectualPropertyRightAppeal, IntellectualPropertyRightAppealExample> {
	
	private static final Log logger = LogFactory.getLog(IntellectualPropertyRightAppealService.class);
	
	@Autowired
	private IntellectualPropertyRightAppealMapper mapper;
	
	@Autowired
	private IntellectualPropertyRightAppealCustomMapper customMapper;
	
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
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MchtUserService mchtUserService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	public void setIntellectualPropertyRightAppealMapper(IntellectualPropertyRightAppealMapper propertyRightAppealMapper) {
		super.setDao(propertyRightAppealMapper);
		this.mapper = propertyRightAppealMapper;
	}
	
	public int countByExample(IntellectualPropertyRightAppealExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<IntellectualPropertyRightAppealCustom> selectCustomByExample(IntellectualPropertyRightAppealCustomExample customExample){
    	return customMapper.selectByExample(customExample);
    }
    
    public IntellectualPropertyRightAppealCustom selectCustomByPrimaryKey(Integer id){
    	return customMapper.selectByPrimaryKey(id);
    }
    
    public int countCustomByExample(IntellectualPropertyRightAppealCustomExample customExample){
		return customMapper.countByExample(customExample);
	}
    
    /**
     * 全部产权投诉列表查询
     * 
     * @param customExample
     * @return
     */
    public List<IntellectualPropertyRightAppealCustom> selectAllCustomByExample(IntellectualPropertyRightAppealCustomExample customExample){
    	return customMapper.selectAllByExample(customExample);
    }
    
    /**
     * 全部产权投诉列表count查询
     * 
     * @param customExample
     * @return
     */
    public int countAllByExample(IntellectualPropertyRightAppealCustomExample customExample){
		return customMapper.countAllByExample(customExample);
	}
    
    /**
     * 更新领取人
     * 
     * @param rightAppealCustom
     */
    public void updateStaffId(IntellectualPropertyRightAppealCustom rightAppealCustom) {
    	rightAppealCustom.setUpdateDate(new Date());
    	this.mapper.updateByPrimaryKeySelective(rightAppealCustom);
    }
	
    /**
     * 获取详情数据
     * 
     * @param id
     * @return
     */
    public Map<String, Object> getPageData(Integer id) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	//权利人申请信息
    	IntellectualPropertyRightAppealCustom rightAppealCustom = customMapper.selectByPrimaryKey(id);
    	
    	if (rightAppealCustom == null) {
    		return null;
    	}
    	
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
     * 获取商家基本信息
     * 
     * @param mchtCode
     * @return
     */
    public Map<String, Object> checkMchtInfo(String mchtCode) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	resultMap.put("mchtInfo", mchtInfoService.selectByMchtCode(mchtCode));
    	return resultMap;
    }
    
    /**
     * 更新审核状态并且发送短信以及通知
     * 
     * @param rightCustom
     */
    public void updateAcceptStatus(IntellectualPropertyRightAppealCustom rightAppealCustom) {
    	if (rightAppealCustom == null || rightAppealCustom.getId() == null) {
    		throw new ArgException("提交数据错误");
    	}
    	if (StringUtil.isEmpty(rightAppealCustom.getAcceptStatus())) {
    		throw new ArgException("请选择审核状态");
    	}
    	if (!"2".equals(rightAppealCustom.getAcceptStatus()) && StringUtil.isEmpty(rightAppealCustom.getMchtCode())) {
    		throw new ArgException("请填写商家序号");
    	}
    	IntellectualPropertyRightAppeal propertyRightAppeal = new IntellectualPropertyRightAppeal();
    	// 获取商家信息
    	Map<String, Object> mchtInfoMap;
    	String mchtId = null;
    	if ("1".equals(rightAppealCustom.getAcceptStatus())) {
	    	mchtInfoMap = mchtInfoService.selectByMchtCode(rightAppealCustom.getMchtCode());
	    	if (mchtInfoMap == null || mchtInfoMap.get("mchtId") == null) {
	    		throw new ArgException("商家信息错误");
	    	}
	    	// 商家ID
	    	mchtId = mchtInfoMap.get("mchtId").toString();
	    	// 投诉表关联商家id
	    	propertyRightAppeal.setMchtId(Integer.parseInt(mchtId));
    	}
    	
    	// 更新受理时间 更新申诉截止时间(受理时间+7天)
    	Date updateDate = new Date();
    	Date complainEndDate = DateUtils.addDays(updateDate, 7);
    	
    	propertyRightAppeal.setId(rightAppealCustom.getId());
    	propertyRightAppeal.setAcceptStatus(rightAppealCustom.getAcceptStatus());
    	propertyRightAppeal.setUpdateBy(rightAppealCustom.getUpdateBy());
    	propertyRightAppeal.setUpdateDate(updateDate);
    	propertyRightAppeal.setCommitDate(updateDate);
    	propertyRightAppeal.setComplainEndDate(complainEndDate);
    	propertyRightAppeal.setRemarksToObligee(rightAppealCustom.getRemarksToObligee());
    	if ("1".equals(rightAppealCustom.getAcceptStatus())) {
    		propertyRightAppeal.setRemarksToMcht(rightAppealCustom.getRemarksToMcht());
    	}
    	propertyRightAppeal.setInnerRemarks(rightAppealCustom.getInnerRemarks());
    	// 更新受理状态
    	this.mapper.updateByPrimaryKeySelective(propertyRightAppeal);
    	
    	IntellectualPropertyRightAppeal rightAppeal = this.mapper.selectByPrimaryKey(rightAppealCustom.getId());
    	
    	// 通知权利人
    	obligeeNoticeService.insertRightAppealNotice(rightAppeal, rightAppealCustom.getAcceptStatus(), rightAppealCustom.getUpdateBy());
    	
    	// 发送信息给权利人
    	Obligee obligee = obligeeService.selectByPrimaryKey(rightAppeal.getObligeeId());
    	JSONObject param = new JSONObject();
		JSONObject reqData = new JSONObject();
		String content = "您的投诉平台已受理，并对商家相应的处罚。等待商家的声明";
		if ("2".equals(rightAppealCustom.getAcceptStatus())){
			content = "您提交的投诉不符合受理条件，请及时登入平台查看原因并完善投诉信息。";
		}
		reqData.put("mobile", obligee.getMobile());
		reqData.put("content", content);
		reqData.put("smsType", "4");
		param.put("reqData", reqData);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
		if(!"0000".equals(result.getString("returnCode"))){
			logger.info("知识产权投诉平台受理短信发送权利人失败！！！！！");
		}
		
		// 受理审核状态为受理时，需要通知商家
		if ("1".equals(rightAppealCustom.getAcceptStatus())) {
			// 通知商家站内信
	    	PlatformMsg platformMsg = new PlatformMsg();
	    	platformMsg.setMchtId(Integer.parseInt(mchtId));
	    	platformMsg.setMsgType("CQ");
	    	platformMsg.setTitle("关于产权投诉通知");
	    	platformMsg.setContent("贵司涉嫌侵犯他人的知识产权，我们将对侵权内容采取必要措施，请及时登入平台查看具体信息，如有疑问请联系平台客服。");
	    	platformMsg.setStatus("0");
	    	platformMsg.setCreateDate(new Date());
	    	platformMsg.setDelFlag("0");
	    	platformMsgService.insertSelective(platformMsg);
	    	
			// 发送信息给商家端主账号
			String mobile = mchtUserService.selectMobileByMchtId(Integer.parseInt(mchtId));
			JSONObject paramToMcht = new JSONObject();
			JSONObject reqDataToMcht = new JSONObject();
			String contentToMcht = "贵司涉嫌侵犯他人的知识产权，我们将对侵权内容采取必要措施，请及时登录平台查看相关信息，如有疑问请联系平台客服。";
			reqDataToMcht.put("mobile", mobile);
			reqDataToMcht.put("content", contentToMcht);
			reqDataToMcht.put("smsType", "4");
			paramToMcht.put("reqData", reqDataToMcht);
			JSONObject resultToMcht = JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(paramToMcht).toString()));
			if(!"0000".equals(resultToMcht.getString("returnCode"))){
				logger.info("知识产权投诉平台受理短信发送商家失败！！！！！");
			}
			
			
		}
		
		//知识产权投诉日志表插入记录
		PropertyRightAppealLog propertyRightAppealLog = new PropertyRightAppealLog();
		String appealLogContent = "平台受理";
		if ("2".equals(rightAppealCustom.getAcceptStatus())) {
			appealLogContent = "平台受理驳回";
		}
		propertyRightAppealLog.setContent(appealLogContent);
		propertyRightAppealLog.setIntellectualPropertyRightAppealId(rightAppealCustom.getId());
		propertyRightAppealLog.setOperatorType("2");
		propertyRightAppealLog.setCreateDate(new Date());
		propertyRightAppealLog.setDelFlag("0");
		propertyRightAppealLogService.insert(propertyRightAppealLog);
    }
    
    /**
     * 获取操作权限为88的系统用户
     * 
     * @return
     */
    public List<SysStaffRoleCustom> getStaffRoleList() {
    	return sysStaffRoleService.selectStaffRoleList(88);
    }
    
    /**
     * 判断该用户是否能领取
     * 
     * @param staffId
     * @return
     */
    public Integer getCanOperate(Integer staffId) {
    	return propertyRightService.getCanOperate(staffId);
    }
}

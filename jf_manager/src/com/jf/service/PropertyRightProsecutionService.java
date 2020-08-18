package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

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
import com.jf.dao.PropertyRightProsecutionCustomMapper;
import com.jf.dao.PropertyRightProsecutionMapper;
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
import com.jf.entity.PropertyRightComplainCustom;
import com.jf.entity.PropertyRightComplainLogCustom;
import com.jf.entity.PropertyRightComplainLogExample;
import com.jf.entity.PropertyRightComplainPic;
import com.jf.entity.PropertyRightComplainPicExample;
import com.jf.entity.PropertyRightProsecution;
import com.jf.entity.PropertyRightProsecutionCustom;
import com.jf.entity.PropertyRightProsecutionCustomExample;
import com.jf.entity.PropertyRightProsecutionExample;
import com.jf.entity.PropertyRightProsecutionLog;
import com.jf.entity.PropertyRightProsecutionPic;
import com.jf.entity.PropertyRightProsecutionPicExample;
import com.jf.entity.SysStaffRoleCustom;


@Service
@Transactional
public class PropertyRightProsecutionService extends BaseService<PropertyRightProsecution, PropertyRightProsecutionExample> {
	
	private static final Log logger = LogFactory.getLog(PropertyRightProsecutionService.class);
	
	@Autowired
	private PropertyRightProsecutionMapper mapper;
	
	@Autowired
	private PropertyRightProsecutionCustomMapper customMapper;
	
	@Autowired
	private IntellectualPropertyRightAppealService rightAppealService;
	
	@Autowired
	private SysStaffInfoService sysStaffInfoService;
	
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
	private PropertyRightComplainService propertyRightComplainService;
	
	@Autowired
	private PropertyRightComplainPicService propertyRightComplainPicService;
	
	@Autowired
	private PropertyRightComplainLogService propertyRightComplainLogService;
	
	@Autowired
	private PropertyRightProsecutionPicService prosecutionPicService;
	
	@Autowired
	private PropertyRightProsecutionLogService prosecutionLogService;
	
	@Autowired
	private MchtInfoService mchtInfoService;
	
	@Autowired
	private PlatformMsgService platformMsgService;
	
	@Autowired
	private MchtUserService mchtUserService;
	
	@Autowired
	public void setPropertyRightProsecutionMapper(PropertyRightProsecutionMapper propertyRightProsecutionMapper) {
		super.setDao(propertyRightProsecutionMapper);
		this.mapper = propertyRightProsecutionMapper;
	}
	
	public int countByExample(PropertyRightProsecutionExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<PropertyRightProsecutionCustom> selectCustomByExample(PropertyRightProsecutionCustomExample customExample){
    	return customMapper.selectByExample(customExample);
    }
    
    public PropertyRightProsecutionCustom selectCustomByPrimaryKey(Integer id){
    	return customMapper.selectByPrimaryKey(id);
    }
    
    public int countCustomByExample(PropertyRightProsecutionCustomExample customExample){
		return customMapper.countByExample(customExample);
	}
    
    /**
     * 获取处理人下拉列表
     * 
     * @param staffId
     * @return
     */
	public List<SysStaffRoleCustom> getStaffList(Integer staffId) {
		return propertyRightComplainService.getStaffList(staffId);
	}
	
    /**
     * 获取详情数据
     * 
     * @param id
     * @return
     */
    public Map<String, Object> getPageData(Integer id) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	// 起诉信息
    	PropertyRightProsecutionCustom rightProsecutionCustom = customMapper.selectByPrimaryKey(id);
    	
    	if (rightProsecutionCustom == null) {
    		return null;
    	}
    	
    	resultMap.put("rightProsecutionCustom", rightProsecutionCustom);
    	
    	// 起诉材料
    	PropertyRightProsecutionPicExample prosecutionPicExample = new PropertyRightProsecutionPicExample();
    	PropertyRightProsecutionPicExample.Criteria prosecutionPicCriteria = prosecutionPicExample.createCriteria();
    	prosecutionPicCriteria.andPropertyRightProsecutionIdEqualTo(rightProsecutionCustom.getId());
    	prosecutionPicCriteria.andDelFlagEqualTo("0");
    	List<PropertyRightProsecutionPic> prosecutionPics = prosecutionPicService.selectByExample(prosecutionPicExample);
    	resultMap.put("prosecutionPics", prosecutionPics);
    	
    	//投诉信息
    	IntellectualPropertyRightAppealCustom rightAppealCustom = rightAppealService.selectCustomByPrimaryKey(rightProsecutionCustom.getIntellectualPropertyRightAppealId());
    	
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
    	
    	// 商家声明信息
    	PropertyRightComplainCustom rightComplainCustom = propertyRightComplainService.selectCustomByPrimaryKey(rightProsecutionCustom.getPropertyRightComplainId());
    	resultMap.put("rightComplainCustom", rightComplainCustom);
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
     * 起诉跟进
     * 
     * @param prosecutionCustom
     */
    public void updateStatus(PropertyRightProsecutionCustom prosecutionCustom) {
    	if (prosecutionCustom == null || prosecutionCustom.getId() == null) {
    		throw new ArgException("提交数据错误");
    	}
    	if (StringUtil.isEmpty(prosecutionCustom.getStatus())) {
    		throw new ArgException("请选择审核状态");
    	}
    	PropertyRightProsecution prosecution = new PropertyRightProsecution();
    	prosecution.setId(prosecutionCustom.getId());
    	prosecution.setStatus(prosecutionCustom.getStatus());
    	prosecution.setUpdateBy(prosecutionCustom.getUpdateBy());
    	prosecution.setUpdateDate(new Date());
    	prosecution.setRemarksToMcht(prosecutionCustom.getRemarksToMcht());
    	prosecution.setRemarksToObligee(prosecutionCustom.getRemarksToObligee());
    	prosecution.setRemarkToInner(prosecutionCustom.getRemarkToInner());
    	// 更新申诉状态
    	this.mapper.updateByPrimaryKeySelective(prosecution);
    	
    	PropertyRightProsecution propertyRightProsecution = this.mapper.selectByPrimaryKey(prosecutionCustom.getId());
    	
    	IntellectualPropertyRightAppeal rightAppeal = rightAppealService.selectByPrimaryKey(propertyRightProsecution.getIntellectualPropertyRightAppealId());
    	
    	// 起诉成立
    	if ("2".equals(prosecutionCustom.getStatus())) {
    		String title = "关于产权侵权通知";
    		String content = "权利人已起诉/投诉，平台会持续跟进起诉/投诉结果。";
    		String contentMsg = "权利人已起诉/投诉，平台会持续跟进起诉/投诉结果。";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		
    		// 通知权利人
	    	obligeeNoticeService.insertRightProsecutionNotice(rightAppeal, "1", prosecutionCustom.getUpdateBy());
	    	
	    	// 发送信息给权利人
	    	String contentMsgToObligee = "您提交起诉/投诉信息平台已收悉，平台会维持对商家的处罚。感谢您对平台的支持。";
	    	this.sendMsgToObligee(rightAppeal.getObligeeId(), contentMsgToObligee);
	    	
    	} else if ("3".equals(prosecutionCustom.getStatus())) {//起诉无效
    		String title = "关于撤销处罚的通知";
    		String content = "您的申诉成功，平台已撤销对违规内容的处罚措施。请及时登入平台查看。如有疑问可联系平台客服。";
    		String contentMsg = "您的申诉成功，平台已撤销对违规内容的处罚措施。详情请登录平台查看。起诉成立（信息有效）";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		
			// 通知权利人
	    	obligeeNoticeService.insertRightProsecutionNotice(rightAppeal, "2", prosecutionCustom.getUpdateBy());
	    	
	    	// 发送信息给权利人
	    	String contentMsgToObligee = "您提交起诉信息有误，平台视为您放弃投诉，平台已撤销处罚 详情请登录平台查看";
	    	this.sendMsgToObligee(rightAppeal.getObligeeId(), contentMsgToObligee);
	    	
	    	// 起诉无效 投诉撤销
	    	rightAppeal.setStatus("3");
	    	rightAppealService.updateByPrimaryKeySelective(rightAppeal);
    	}
    	
//    	//知识产权起诉日志表插入记录
//		PropertyRightProsecutionLog prosecutionLog = new PropertyRightProsecutionLog();
//		String prosecutionLogContent = "平台起诉跟进";
//		prosecutionLog.setContent(prosecutionLogContent);
//		prosecutionLog.setPropertyRightProsecutionId(propertyRightProsecution.getId());
//		prosecutionLog.setOperatorType("2");
//		prosecutionLog.setCreateDate(new Date());
//		prosecutionLog.setDelFlag("0");
//		prosecutionLogService.insert(prosecutionLog);
		
		//知识产权投诉日志表插入记录
		PropertyRightAppealLog propertyRightAppealLog = new PropertyRightAppealLog();
		String appealLogContent = "平台起诉跟进";
		propertyRightAppealLog.setContent(appealLogContent);
		propertyRightAppealLog.setIntellectualPropertyRightAppealId(rightAppeal.getId());
		propertyRightAppealLog.setOperatorType("2");
		propertyRightAppealLog.setCreateDate(new Date());
		propertyRightAppealLog.setDelFlag("0");
		propertyRightAppealLogService.insert(propertyRightAppealLog);
    }
    
    /**
     * 起诉结果提交
     * 
     * @param prosecutionCustom
     */
    public void submitResult(PropertyRightProsecutionCustom prosecutionCustom) {
    	if (prosecutionCustom == null || prosecutionCustom.getId() == null) {
    		throw new ArgException("提交数据错误");
    	}
    	if (StringUtil.isEmpty(prosecutionCustom.getWinType())){
    		throw new ArgException("请选择起诉结果");
    	}
    	PropertyRightProsecution prosecution = new PropertyRightProsecution();
    	prosecution.setId(prosecutionCustom.getId());
    	prosecution.setWinType(prosecutionCustom.getWinType());
    	prosecution.setUpdateBy(prosecutionCustom.getUpdateBy());
    	prosecution.setUpdateDate(new Date());
    	prosecution.setResultToObligee(prosecutionCustom.getResultToObligee());
    	prosecution.setResultToMcht(prosecutionCustom.getResultToMcht());
    	prosecution.setInnerRemarks(prosecutionCustom.getInnerRemarks());
    	// 权利人胜诉
    	if ("1".equals(prosecutionCustom.getWinType())) {
    		prosecution.setStatus("4");
    	} else if ("2".equals(prosecutionCustom.getWinType())) {//权利人败诉 
    		prosecution.setStatus("5");
    	}
    	
    	// 更新申诉状态
    	this.mapper.updateByPrimaryKeySelective(prosecution);
    	
    	PropertyRightProsecution propertyRightProsecution = this.mapper.selectByPrimaryKey(prosecutionCustom.getId());
    	
    	IntellectualPropertyRightAppeal rightAppeal = rightAppealService.selectByPrimaryKey(propertyRightProsecution.getIntellectualPropertyRightAppealId());
    	
    	// 权利人胜诉
    	if ("1".equals(prosecutionCustom.getWinType())) {
    		String title = "关于产权侵权通知";
    		String content = "经核实，产权投诉有效，平台维持处罚。";
    		String contentMsg = "经核实，产权投诉有效，平台维持处罚。";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		
    		// 通知权利人
	    	obligeeNoticeService.insertRightProsecutionResult(rightAppeal, "1", prosecutionCustom.getUpdateBy());
	    	
	    	// 发送信息给权利人
	    	String contentMsgToObligee = "经核实，产权投诉有效，平台维持对商家处罚";
	    	this.sendMsgToObligee(rightAppeal.getObligeeId(), contentMsgToObligee);
	    	// 起诉成立 投诉完成
	    	rightAppeal.setStatus("2");
	    	rightAppealService.updateByPrimaryKeySelective(rightAppeal);
    	} else if ("2".equals(prosecutionCustom.getWinType())) {//权利人败诉
    		String title = "关于投诉不成立通知";
    		String content = " 经核实，产权投诉无效，平台已撤销处罚。";
    		String contentMsg = "经核实，产权投诉无效，平台已撤销处罚。";
    		this.noticeMcht(rightAppeal.getMchtId(), title, content, contentMsg);
    		// 权利人败诉 投诉撤销
	    	rightAppeal.setStatus("3");
	    	rightAppealService.updateByPrimaryKeySelective(rightAppeal);
    	}
    	
//    	//知识产权起诉日志表插入记录
//		PropertyRightProsecutionLog prosecutionLog = new PropertyRightProsecutionLog();
//		String prosecutionLogContent = "提交起诉结果";
//		prosecutionLog.setContent(prosecutionLogContent);
//		prosecutionLog.setPropertyRightProsecutionId(propertyRightProsecution.getId());
//		prosecutionLog.setOperatorType("2");
//		prosecutionLog.setCreateDate(new Date());
//		prosecutionLog.setDelFlag("0");
//		prosecutionLogService.insert(prosecutionLog);
		
		//知识产权投诉日志表插入记录
		PropertyRightAppealLog propertyRightAppealLog = new PropertyRightAppealLog();
		String appealLogContent = "提交起诉结果";
		propertyRightAppealLog.setContent(appealLogContent);
		propertyRightAppealLog.setIntellectualPropertyRightAppealId(rightAppeal.getId());
		propertyRightAppealLog.setOperatorType("2");
		propertyRightAppealLog.setCreateDate(new Date());
		propertyRightAppealLog.setDelFlag("0");
		propertyRightAppealLogService.insert(propertyRightAppealLog);
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
			logger.info("知识产权投诉平台起诉，短信发送商家失败！！！！！");
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

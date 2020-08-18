package com.jf.service;

import java.util.ArrayList;
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
import com.jf.dao.IntellectualPropertyRightCustomMapper;
import com.jf.dao.IntellectualPropertyRightMapper;
import com.jf.entity.IntellectualPropertyRight;
import com.jf.entity.IntellectualPropertyRightCustom;
import com.jf.entity.IntellectualPropertyRightCustomExample;
import com.jf.entity.IntellectualPropertyRightExample;
import com.jf.entity.IntellectualPropertyRightPic;
import com.jf.entity.IntellectualPropertyRightPicExample;
import com.jf.entity.Obligee;
import com.jf.entity.ObligeeCustom;
import com.jf.entity.ObligeePic;
import com.jf.entity.ObligeePicExample;
import com.jf.entity.SysStaffRole;
import com.jf.entity.SysStaffRoleExample;


@Service
@Transactional
public class IntellectualPropertyRightService extends BaseService<IntellectualPropertyRight,IntellectualPropertyRightExample> {
	
	private static final Log logger = LogFactory.getLog(IntellectualPropertyRightService.class);
	
	@Autowired
	private IntellectualPropertyRightMapper mapper;
	
	@Autowired
	private IntellectualPropertyRightCustomMapper customMapper;
	
	@Autowired
	private IntellectualPropertyRightPicService rightPicService;
	
	@Autowired
	private ObligeeService obligeeService;
	
	@Autowired
	private ObligeePicService obligeePicService;
	
	@Autowired
	private ObligeeNoticeService obligeeNoticeService;
	
	@Autowired
	private SysStaffRoleService sysStaffRoleService;
	
	@Autowired
	public void setIntellectualPropertyRightMapper(IntellectualPropertyRightMapper intellectualPropertyRightMapper) {
		super.setDao(intellectualPropertyRightMapper);
		this.mapper = intellectualPropertyRightMapper;
	}
	
	public int countByExample(IntellectualPropertyRightExample example){
		return this.mapper.countByExample(example);
	}
	
    public List<IntellectualPropertyRightCustom> selectCustomByExample(IntellectualPropertyRightCustomExample customExample){
    	return customMapper.selectByExample(customExample);
    }
    
    public IntellectualPropertyRightCustom selectCustomByPrimaryKey(Integer id){
    	return customMapper.selectByPrimaryKey(id);
    }
    
    public int countCustomByExample(IntellectualPropertyRightCustomExample customExample){
		return customMapper.countByExample(customExample);
	}
	
    /**
     * 获取详情页数据
     * 
     * @param id
     * @return
     */
    public Map<String, Object> getPageData(Integer id, Integer staffId) {
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	//权利人申请信息
    	IntellectualPropertyRightCustom rightCustom = customMapper.selectByPrimaryKey(id);
    	
    	if (rightCustom == null) {
    		return null;
    	}
    	
    	resultMap.put("rightCustom", rightCustom);
    	
    	IntellectualPropertyRightPicExample example = new IntellectualPropertyRightPicExample();
    	IntellectualPropertyRightPicExample.Criteria rightPicCriteria = example.createCriteria();
    	rightPicCriteria.andIntellectualPropertyRightIdEqualTo(rightCustom.getId());
    	rightPicCriteria.andDelFlagEqualTo("0");
    	List<IntellectualPropertyRightPic> rightPicList = rightPicService.selectByExample(example);
    	//权利人证明
    	List<Map<String, Object>> obligeeProofList = new ArrayList<Map<String, Object>>();;
    	//产权证明
    	List<Map<String, Object>> rightProofList = new ArrayList<Map<String, Object>>();;
    	//委托书
    	List<Map<String, Object>> entrustProofList = new ArrayList<Map<String, Object>>();;
    	
    	for (IntellectualPropertyRightPic rightPic: rightPicList) {
    		if (rightPic == null || rightPic.getPic() == null || StringUtil.isEmpty(rightPic.getPic())) {
    			continue;
    		}
    		Map<String, Object> map = new HashMap<String, Object>();
    		
    		String picString = rightPic.getPic();
    		
    		int begin = picString.lastIndexOf("/") + 1;
    		int end = picString.length();
    		map.put("name", picString.substring(begin, end));
    		map.put("pic", picString);
    		
    		int suffixBegin = picString.lastIndexOf(".") + 1;
    		String suffixString = picString.substring(suffixBegin, end);
    		if (suffixString != null && "pdf".equals(suffixString)) {
    			map.put("pdf", true);
    		}
    		
			if ("1".equals(rightPic.getPicType())) {
				obligeeProofList.add(map);
			} else if ("2".equals(rightPic.getPicType())) {
				rightProofList.add(map);
			} else if ("3".equals(rightPic.getPicType())) {
				entrustProofList.add(map);
			}
		}
    	
    	resultMap.put("obligeeProofList", obligeeProofList);
    	resultMap.put("rightProofList", rightProofList);
    	resultMap.put("entrustProofList", entrustProofList);
    	
    	// 权利人信息
    	ObligeeCustom obligeeCustom = obligeeService.selectCustomByPrimaryKey(rightCustom.getObligeeId());
    	resultMap.put("obligee", obligeeCustom);
    	
    	//权利人身份证明   注意:与上面的权利人证明不同 上面的权利人可以视为产权人，这边的权利人是用户
    	ObligeePicExample obligeePicExample = new ObligeePicExample();
    	ObligeePicExample.Criteria obligeePicCriteria = obligeePicExample.createCriteria();
    	obligeePicCriteria.andObligeeIdEqualTo(obligeeCustom.getId());
    	obligeePicCriteria.andDelFlagEqualTo("0");
    	List<ObligeePic> obligeePicList = obligeePicService.selectByExample(obligeePicExample);
    	resultMap.put("obligeePicList", obligeePicList);
    	
    	// 当前操作人员是否能操作标识
    	resultMap.put("canOperate", this.getCanOperate(staffId));
    	return resultMap;
    }
    
    /**
     * 更新审核状态并且发送短信以及通知
     * 
     * @param rightCustom
     */
    public void updateStatus(IntellectualPropertyRightCustom rightCustom) {
    	if (rightCustom == null || rightCustom.getId() == null) {
    		throw new ArgException("提交数据错误");
    	}
    	if (StringUtil.isEmpty(rightCustom.getStatus())) {
    		throw new ArgException("请选择审核状态");
    	}
    	if ("2".equals(rightCustom.getStatus()) && StringUtil.isEmpty(rightCustom.getAuditRemarks())) {
    		throw new ArgException("请填写驳回原因");
    	}
    	
    	IntellectualPropertyRight propertyRight = new IntellectualPropertyRight();
    	propertyRight.setId(rightCustom.getId());
    	propertyRight.setStatus(rightCustom.getStatus());
    	propertyRight.setUpdateBy(rightCustom.getUpdateBy());
    	propertyRight.setUpdateDate(new Date());
    	if ("2".equals(rightCustom.getStatus())){
			propertyRight.setAuditRemarks(rightCustom.getAuditRemarks());
    	}
    	// 更新状态
    	this.mapper.updateByPrimaryKeySelective(propertyRight);
    	
    	IntellectualPropertyRight right = this.mapper.selectByPrimaryKey(rightCustom.getId());
    	
    	// 插入通知
    	obligeeNoticeService.insertRightAuditNotice(right, rightCustom.getStatus(), rightCustom.getUpdateBy());
    	
    	// 发送信息
    	Obligee obligee = obligeeService.selectByPrimaryKey(right.getObligeeId());
    	JSONObject param = new JSONObject();
		JSONObject reqData = new JSONObject();
		String content = "您提交的【 " + right.getPropertyRightName() + "】已通过审核";
		if ("2".equals(rightCustom.getStatus())){
			content = "您提交的【 " + right.getPropertyRightName() + "】被驳回。具体原因请登录平台查看";
		}
		reqData.put("mobile", obligee.getMobile());
		reqData.put("content", content);
		reqData.put("smsType", "4");
		param.put("reqData", reqData);
		JSONObject result=JSONObject.fromObject(HttpUtil.sendPostRequest(SysConfig.msgServerUrl+"/api/sms/sendImmediately", CommonUtil.createReqData(param).toString()));
		if(!"0000".equals(result.getString("returnCode"))){
			logger.info("知识产权审核短信发送用户失败！！！！！");
		}
    }
    
    /**
     * 判断当前操作用户是否为角色id 88 的用户
     * 角色id为88 才能进行下一步操作
     * 
     * @param staffId
     * @return
     */
    public Integer getCanOperate(Integer staffId) {
    	SysStaffRoleExample roleExample = new SysStaffRoleExample();
    	SysStaffRoleExample.Criteria roleCriteria = roleExample.createCriteria();
    	roleCriteria.andStaffIdEqualTo(staffId);
    	roleCriteria.andRoleIdEqualTo(88);
    	roleCriteria.andStatusEqualTo("A");
    	List<SysStaffRole> list = sysStaffRoleService.selectByExample(roleExample);
    	if (list.isEmpty()) {
    		return 0;
    	}
    	return 1;
    }
}

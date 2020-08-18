package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.jf.common.constant.Const;
import com.jf.dao.MemberPvMiddleMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.entity.MemberPvMiddle;
import com.jf.entity.MemberPvMiddleExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;

@Service
@Transactional
public class MemberPvMiddleService extends BaseService<MemberPvMiddle, MemberPvMiddleExample, MemberPvMiddleMapper> {

	@Autowired
	private MemberPvMiddleMapper memberPvMiddleMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	public Map<String, Object> sysConfig(JSONObject reqPRM) {
		Map<String, Object> map = new HashMap<String, Object>();
		String isCollect = "0";
		String commitIntervalTime = "300";
		SysParamCfgExample isCollectExample = new SysParamCfgExample();
		isCollectExample.createCriteria().andParamCodeEqualTo(Const.FLOW_IS_COLLECT);
		List<SysParamCfg> isCollectList = sysParamCfgMapper.selectByExample(isCollectExample);
		if(CollectionUtils.isNotEmpty(isCollectList) ) {
			isCollect = isCollectList.get(0).getParamValue();
		}
		SysParamCfgExample commitIntervalTimeExample = new SysParamCfgExample();
		commitIntervalTimeExample.createCriteria().andParamCodeEqualTo(Const.FLOW_COMMIT_INTERVAL_TIME);
		List<SysParamCfg> commitIntervalTimeList = sysParamCfgMapper.selectByExample(commitIntervalTimeExample);
		if(CollectionUtils.isNotEmpty(commitIntervalTimeList) ) {
			commitIntervalTime = commitIntervalTimeList.get(0).getParamValue();
		}
		map.put("isCollect", isCollect);
		map.put("commitIntervalTime", commitIntervalTime);
		return map;
	}
	
	public void memberPvCommit(JSONObject reqPRM) {
		Object reqData = reqPRM.get("reqData");
		if(reqData != null) {
			Date date = new Date();
			MemberPvMiddle memberPvMiddle = new MemberPvMiddle();
			memberPvMiddle.setClientSource(reqPRM.getString("client"));
			memberPvMiddle.setContent(reqData.toString());
			memberPvMiddle.setSourceType(Const.PV_SOURCE_TYPE1);
			memberPvMiddle.setCreateDate(date);
			memberPvMiddle.setUpdateDate(date);
			memberPvMiddleMapper.insertSelective(memberPvMiddle);
		}
	}
	
	public void memberPvDtlCommit(JSONObject reqPRM) {
		Object reqData = reqPRM.get("reqData");
		if(reqData != null) {
			Date date = new Date();
			MemberPvMiddle memberPvMiddle = new MemberPvMiddle();
			memberPvMiddle.setClientSource(reqPRM.getString("client"));
			memberPvMiddle.setContent(reqData.toString());
			memberPvMiddle.setSourceType(Const.PV_SOURCE_TYPE2);
			memberPvMiddle.setCreateDate(date);
			memberPvMiddle.setUpdateDate(date);
			memberPvMiddleMapper.insertSelective(memberPvMiddle);
		}
	}
	
	public void memberActionCommit(JSONObject reqPRM) {
		Object reqData = reqPRM.get("reqData");
		if(reqData != null) {
			Date date = new Date();
			MemberPvMiddle memberPvMiddle = new MemberPvMiddle();
			memberPvMiddle.setClientSource(reqPRM.getString("client"));
			memberPvMiddle.setContent(reqData.toString());
			memberPvMiddle.setSourceType(Const.PV_SOURCE_TYPE3);
			memberPvMiddle.setCreateDate(date);
			memberPvMiddle.setUpdateDate(date);
			memberPvMiddleMapper.insertSelective(memberPvMiddle);
		}
	}
	
}

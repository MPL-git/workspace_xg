package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysAppMessageCustomMapper;
import com.jf.dao.SysAppMessageMapper;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageExample;
import com.jf.entity.SysAppMessageMember;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月15日 上午10:24:50 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SysAppMessageService extends BaseService<SysAppMessage, SysAppMessageExample> {
	
	@Autowired
	private SysAppMessageMapper sysAppMessageMapper;
	@Autowired
	private SysAppMessageCustomMapper sysAppMessageCustomMapper;
	

	@Autowired
	public void setSysAppMessageMapper(SysAppMessageMapper sysAppMessageMapper) {
		this.setDao(sysAppMessageMapper);
		this.sysAppMessageMapper = sysAppMessageMapper;
	}


	public SysAppMessage add(String type, String title, String content,
			String linkType, Integer linkId, Integer memberId, Integer createBy, String pushFlag) {
		
		SysAppMessage sysAppMessage = new SysAppMessage();
		sysAppMessage.setType(type);
		sysAppMessage.setTitle(title);
		sysAppMessage.setContent(content);
		sysAppMessage.setLinkType(linkType);
		sysAppMessage.setLinkId(linkId);
		sysAppMessage.setMemberId(memberId);
		sysAppMessage.setPushFlag(pushFlag);
		sysAppMessage.setCreateBy(createBy);
		sysAppMessage.setCreateDate(new Date());
		sysAppMessage.setDelFlag("0");
		
		return saveModel(sysAppMessage);
	}


	public SysAppMessage saveModel(SysAppMessage sysAppMessage) {
		sysAppMessageMapper.insertSelective(sysAppMessage);
		return sysAppMessage;
	}


	public void insertBatchForMemberMsg(List<SysAppMessageMember> memberMsgs) {
		
		sysAppMessageCustomMapper.insertBatchForMemberMsg(memberMsgs);
	}


	public List<SysAppMessage> findModelsByTitle(String title, String content, String type) {
		SysAppMessageExample sysAppMessageExample = new SysAppMessageExample();
		sysAppMessageExample.createCriteria().andTitleEqualTo(title).andContentEqualTo(content).andTypeEqualTo(type).andDelFlagEqualTo("0");
		sysAppMessageExample.setOrderByClause("id desc");
		sysAppMessageExample.setLimitStart(0);
		sysAppMessageExample.setLimitSize(1);
		return selectByExample(sysAppMessageExample);
	}


	public Integer createAppSystemMsg(String title, String content, String type, String linkType) {
		Integer systemAppMsgId = null;
		List<SysAppMessage> appMessages = findModelsByTitle(title, content, type);
		if(CollectionUtils.isNotEmpty(appMessages)){
			systemAppMsgId = appMessages.get(0).getId();
		}else{
			SysAppMessage sysAppMessage = addModel(title,content,type,linkType);
			systemAppMsgId = sysAppMessage.getId();
		}
		return systemAppMsgId;
	}


	private SysAppMessage addModel(String title, String content, String type, String linkType) {
		SysAppMessage sysAppMessage = new SysAppMessage();
		sysAppMessage.setType(type);
		sysAppMessage.setTitle(title);
		sysAppMessage.setLinkType(linkType);
		sysAppMessage.setLinkId(0);
		sysAppMessage.setContent(content);
		sysAppMessage.setPushFlag("1");
		sysAppMessage.setCreateDate(new Date());
		sysAppMessage.setDelFlag("0");
		insertSelective(sysAppMessage);
		return sysAppMessage;
	}
	
	
}

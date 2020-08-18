package com.jf.service;

import java.util.Date;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.PlatformMsgMapper;
import com.jf.entity.PlatformMsg;
import com.jf.entity.PlatformMsgExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月24日 下午4:45:57 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class PlatformMsgService extends BaseService<PlatformMsg, PlatformMsgExample> {
	
	@Autowired
	private PlatformMsgMapper platformMsgMapper;
	@Autowired
	public void setPlatformMsgMapper(PlatformMsgMapper platformMsgMapper) {
		this.setDao(platformMsgMapper);
		this.platformMsgMapper = platformMsgMapper;
	}
	public PlatformMsg add(Integer mchtId, String msgType, String title, String content, Integer id, String status) {
		Date date = new Date();
		PlatformMsg platformMsg = new PlatformMsg();
		platformMsg.setMchtId(mchtId);
		platformMsg.setMsgType(msgType);
		platformMsg.setTitle(title);
		platformMsg.setContent(content);
		platformMsg.setBizId(id);
		platformMsg.setStatus(status);
		platformMsg.setCreateBy(1);
		platformMsg.setCreateDate(date);
		platformMsg.setDelFlag("0");
		
		return saveModel(platformMsg);
	}
	public PlatformMsg saveModel(PlatformMsg platformMsg) {
		platformMsgMapper.insertSelective(platformMsg);
		return platformMsg;
	}
	
	
}

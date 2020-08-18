package com.jf.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysAppMessageCustomMapper;
import com.jf.dao.SysAppMessageMapper;
import com.jf.entity.SysAppMessage;
import com.jf.entity.SysAppMessageCustom;
import com.jf.entity.SysAppMessageExample;

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
	
	/**
	 * 
	 * 方法描述 ：获取系统消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月15日 上午11:57:53 
	 * @version
	 * @param params
	 * @return
	 */
	public List<SysAppMessageCustom> getSystemMsg(Map<String, Object> params) {
		
		return sysAppMessageCustomMapper.getSystemMsg(params);
	}
	/**
	 * 
	 * 方法描述 ：获取交易消息
	 * @author  chenwf: 
	 * @date 创建时间：2017年6月15日 上午11:58:03 
	 * @version
	 * @param params
	 * @return
	 */
	public List<SysAppMessageCustom> getTransactionMsg(Map<String, Object> params) {
		
		return sysAppMessageCustomMapper.getTransactionMsg(params);
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
	
	
}

package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinXcxSprLogMapper;
import com.jf.entity.WeixinXcxSprLog;
import com.jf.entity.WeixinXcxSprLogExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:29:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinXcxSprLogService extends BaseService<WeixinXcxSprLog, WeixinXcxSprLogExample> {
	@Autowired
	private WeixinXcxSprLogMapper weixinXcxSprLogMapper;

	@Autowired
	public void setWeixinXcxSprLogMapper(WeixinXcxSprLogMapper weixinXcxSprLogMapper) {
		this.setDao(weixinXcxSprLogMapper);
		this.weixinXcxSprLogMapper = weixinXcxSprLogMapper;
	}
	
	
}

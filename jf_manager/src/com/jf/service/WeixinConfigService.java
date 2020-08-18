package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinConfigMapper;
import com.jf.entity.WeixinConfig;
import com.jf.entity.WeixinConfigExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月5日 上午11:13:28 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinConfigService extends BaseService<WeixinConfig, WeixinConfigExample>{
	@Autowired
	private WeixinConfigMapper weixinConfigMapper;

	@Autowired
	public void setWeixinConfigMapper(WeixinConfigMapper weixinConfigMapper) {
		this.setDao(weixinConfigMapper);
		this.weixinConfigMapper = weixinConfigMapper;
	}
}

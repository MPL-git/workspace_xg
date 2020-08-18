package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinXcxSprDtlMapper;
import com.jf.entity.WeixinXcxSprDtl;
import com.jf.entity.WeixinXcxSprDtlExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:29:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinXcxSprDtlService extends BaseService<WeixinXcxSprDtl, WeixinXcxSprDtlExample> {
	@Autowired
	private WeixinXcxSprDtlMapper weixinXcxSprDtlMapper;

	@Autowired
	public void setWeixinXcxSprDtlMapper(WeixinXcxSprDtlMapper weixinXcxSprDtlMapper) {
		this.setDao(weixinXcxSprDtlMapper);
		this.weixinXcxSprDtlMapper = weixinXcxSprDtlMapper;
	}
	
	
}

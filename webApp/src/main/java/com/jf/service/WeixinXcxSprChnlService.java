package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinXcxSprChnlMapper;
import com.jf.entity.WeixinXcxSprChnl;
import com.jf.entity.WeixinXcxSprChnlExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:29:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WeixinXcxSprChnlService extends BaseService<WeixinXcxSprChnl, WeixinXcxSprChnlExample> {
	@Autowired
	private WeixinXcxSprChnlMapper weixinXcxSprChnlMapper;

	@Autowired
	public void setWeixinXcxSprChnlMapper(WeixinXcxSprChnlMapper weixinXcxSprChnlMapper) {
		this.setDao(weixinXcxSprChnlMapper);
		this.weixinXcxSprChnlMapper = weixinXcxSprChnlMapper;
	}
	
	
}

package com.jf.service;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WxRedpackMapper;
import com.jf.entity.WxRedpack;
import com.jf.entity.WxRedpackExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月17日 上午9:29:58 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class WxRedpackService extends BaseService<WxRedpack, WxRedpackExample> {
	@Autowired
	private WxRedpackMapper wxRedpackMapper;

	@Autowired
	public void setWxRedpackMapper(WxRedpackMapper wxRedpackMapper) {
		this.setDao(wxRedpackMapper);
		this.wxRedpackMapper = wxRedpackMapper;
	}
	
	
}

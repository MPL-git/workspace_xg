package com.jf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.WeixinJsapiTicketMapper;
import com.jf.entity.WeixinJsapiTicket;
import com.jf.entity.WeixinJsapiTicketExample;

/**
 * @author chenwf:
 * @date 创建时间：2017年9月5日 上午11:13:28
 * @version 1.0
 * @parameter
 * @return
 */
@Service
@Transactional
public class WeixinJsapiTicketService extends BaseService<WeixinJsapiTicket, WeixinJsapiTicketExample> {
	@Autowired
	private WeixinJsapiTicketMapper weixinJsapiTicketMapper;

	@Autowired
	public void setWeixinJsapiTicketMapper(WeixinJsapiTicketMapper weixinJsapiTicketMapper) {
		this.setDao(weixinJsapiTicketMapper);
		this.weixinJsapiTicketMapper = weixinJsapiTicketMapper;
	}

}

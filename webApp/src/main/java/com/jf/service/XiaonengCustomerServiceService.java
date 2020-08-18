package com.jf.service;

import javax.annotation.Resource;

import com.jf.common.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.XiaonengCustomerServiceMapper;
import com.jf.entity.MchtInfo;
import com.jf.entity.XiaonengCustomerService;
import com.jf.entity.XiaonengCustomerServiceExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年4月8日 下午2:05:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class XiaonengCustomerServiceService extends BaseService<XiaonengCustomerService, XiaonengCustomerServiceExample> {
	@Autowired
	private XiaonengCustomerServiceMapper xiaonengCustomerServiceMapper;
	
	@Resource
	private ProductService productService;
	@Resource
	private MchtInfoService mchtInfoService;
	@Resource
	private SubOrderService subOrderService;
	@Resource
	private ProductPropValueService productPropValueService;
	@Resource
	private CustomerServiceOrderService customerServiceOrderService;
	@Autowired
	public void setXiaonengCustomerServiceMapper(XiaonengCustomerServiceMapper xiaonengCustomerServiceMapper) {
		this.setDao(xiaonengCustomerServiceMapper);
		this.xiaonengCustomerServiceMapper = xiaonengCustomerServiceMapper;
	}
	
	public String getCustomerService(Integer mchtId, String type) {
		Integer xiaonengId = null;
		if(mchtId != null){
			MchtInfo mchtInfo = mchtInfoService.selectByPrimaryKey(mchtId);
			if(mchtInfo.getStatus().equals("1") || mchtInfo.getStatus().equals("2")){
				xiaonengId = mchtInfo.getXiaonengId();
			}else{
				if(type.equals("1") || type.equals("5")){//商品详情客服
					xiaonengId = 2;
				}else if(type.equals("2") || type.equals("3") || type.equals("4")){//订单客服,我的平台客服,售后客服
					xiaonengId = 1;
				}
			}
			if(mchtInfo.getXiaonengId() == null){
				if(type.equals("1") || type.equals("5")){//商品详情客服
					xiaonengId = 2;
				}else if(type.equals("2") || type.equals("3") || type.equals("4")){//订单客服,我的平台客服,售后客服
					xiaonengId = 1;
				}
			}
		}else{
			xiaonengId = 1;
		}
		XiaonengCustomerService xg = selectByPrimaryKey(xiaonengId);
		return xg.getCode();
	}

}

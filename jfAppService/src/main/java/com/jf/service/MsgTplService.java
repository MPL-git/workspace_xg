package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.MsgTplMapper;
import com.jf.entity.MsgTpl;
import com.jf.entity.MsgTplExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年7月24日 下午4:48:17 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MsgTplService extends BaseService<MsgTpl, MsgTplExample> {
	@Autowired
	private MsgTplMapper msgTplMapper;
	@Autowired
	public void setMsgTplMapper(MsgTplMapper msgTplMapper) {
		this.setDao(msgTplMapper);
		this.msgTplMapper = msgTplMapper;
	}
	public String findMsgContent(String tplType, String orderCode,Integer orderCodeId, String subOrderCode,Integer subOrderCodeId, BigDecimal amount) {
		String content = "";
		MsgTplExample tplExample = new MsgTplExample();
		tplExample.createCriteria().andTplTypeEqualTo("A").andMsgTypeEqualTo(tplType).andDelFlagEqualTo("0");
		List<MsgTpl> msgTpls = msgTplMapper.selectByExample(tplExample);
		if(CollectionUtils.isNotEmpty(msgTpls)){
			content = msgTpls.get(0).getContent();
			//售后单
			//您有1条新的退款申请，请及时处理！退款编号《<a href="javascript:void(0)" 
			//onclick="getContent('customerServiceOrder/customerServiceOrderView?id=298')">A201707251703011614</a>》， 
			//退款金额：0.01元，相关订单号：《<a href="javascript:void(0)" 
			//onclick="getContent('subOrder/subOrderView?id=1262')">XGS201707251702294350</a>》。
			//如超过2天未处理，将自动审核通过。
			String url = "";
			if(content.contains("{service_code}")){
				url = "'customerServiceOrder/customerServiceOrderView?id="+orderCodeId+"'";
				orderCode = "《<a href=\"javascript:void(0)\" onclick=\"showDetail("+url+")\">"+orderCode+"</a>》";
				content = content.replace("{service_code}", orderCode);
			}
			if(content.contains("{order_code}")){
				url = "'subOrder/subOrderView?id="+subOrderCodeId+"'";
				subOrderCode = "《<a href=\"javascript:void(0)\" onclick=\"showDetail("+url+")\">"+subOrderCode+"</a>》";
				content = content.replace("{order_code}", subOrderCode);
			}
			if(content.contains("{amount}")){
				content = content.replace("{amount}", amount.toString());
			}
			//投诉单
			if(content.contains("{complain_code}")){
				url = "'appealOrder/appealOrderView?id="+orderCodeId+"'";
				orderCode = "《<a href=\"javascript:void(0)\" onclick=\"showDetail("+url+")\">"+orderCode+"</a>》";
				content = content.replace("{complain_code}", orderCode);
			}
		}
		return content;
	}
	
	
}

package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.common.utils.StringUtil;
import com.jf.dao.SysPaymentMapper;
import com.jf.entity.SysPayment;
import com.jf.entity.SysPaymentExample;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年6月9日 上午9:36:11 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SysPaymentService extends BaseService<SysPayment, SysPaymentExample> {
	
	@Autowired
	private SysPaymentMapper sysPaymentMapper;

	
	@Autowired
	public void setSysPaymentMapper(SysPaymentMapper sysPaymentMapper) {
		this.setDao(sysPaymentMapper);
		this.sysPaymentMapper = sysPaymentMapper;
	}
	
	public List<SysPayment> findListByQuery(QueryObject queryObject){
		return sysPaymentMapper.selectByExample(builderQuery(queryObject));
	}

	private SysPaymentExample builderQuery(QueryObject queryObject) {
		SysPaymentExample sysPaymentExample = new SysPaymentExample();
		SysPaymentExample.Criteria criteria = sysPaymentExample.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("isEffect") != null){
			criteria.andIsEffectEqualTo(queryObject.getQueryToStr("isEffect"));
		}
		if(queryObject.getQuery("payment") != null){
			criteria.andIsEffectEqualTo(queryObject.getQueryToStr("payment"));
		}
		if(queryObject.getQuery("ids") != null){
			List<Integer> ids = queryObject.getQuery("ids");
			criteria.andIdIn(ids);
		}
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		return sysPaymentExample;
	}
	
	/**
	 * 支付方式
	 * @param paymentId 
	 * @param reqPRM 
	 * @return
	 */
	public List<Map<String, Object>> getPayMethod(String paymentId, JSONObject reqPRM) {
		List<Map<String, Object>> payMapList = new ArrayList<Map<String, Object>>();
		String system = reqPRM.getString("system");
		List<Integer> ids = new ArrayList<Integer>();
		if(system.equals(Const.ANDROID) || system.equals(Const.IOS)){//APP
			ids.add(1);
			ids.add(2);
		}else if(system.equals(Const.MICRO_MALL)){//微商城H5
			ids.add(5);
			ids.add(6);
		}else if(system.equals(Const.WEB_APP)){//微信公众号
			ids.add(4);
		}else if(system.equals(Const.WX_XCX)){//微信小程序
			ids.add(7);
		}
		if(CollectionUtils.isNotEmpty(ids)){
			QueryObject queryObject = new QueryObject();
			queryObject.addQuery("isEffect", "1");
			queryObject.addQuery("ids", ids);
			List<SysPayment> sysPayments = findListByQuery(queryObject);
			if(CollectionUtils.isNotEmpty(sysPayments)){
				for (SysPayment sysPayment : sysPayments) {
					Map<String, Object> payMap = new HashMap<String, Object>();
					String describe = "";
					String defaultPay = "0";
					Integer payId = sysPayment.getId();
					if(StringUtil.isBlank(paymentId)){
						if(sysPayment.getId() == 2){
							//默认为微信支付
							defaultPay = "1";
						}
					}else{
						if(paymentId.equals(payId.toString())){
							//原支付默认方式
							defaultPay = "1";
						}
					}
					if(payId == 1){
						describe = "支持花呗";
					}
					payMap.put("payId", payId);
					payMap.put("payName", sysPayment.getName());
					payMap.put("payIcon", StringUtil.getPic(sysPayment.getPaymentIcon(), ""));
					payMap.put("PayType", sysPayment.getPaymentType());
					payMap.put("defaultPay", defaultPay);
					payMap.put("describe", describe);
					payMapList.add(payMap);
				}
			}
		}
		return payMapList;
	}
	
}

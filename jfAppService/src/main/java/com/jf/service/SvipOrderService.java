package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.dao.SvipOrderCustomMapper;
import com.jf.dao.SvipOrderMapper;
import com.jf.entity.SvipOrder;
import com.jf.entity.SvipOrderCustom;
import com.jf.entity.SvipOrderExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SvipOrderService extends BaseService<SvipOrder, SvipOrderExample> {
	@Autowired
	private SvipOrderMapper svipOrderMapper;
	@Autowired
	private SvipOrderCustomMapper svipOrderCustomMapper;
	@Autowired
	public void setSvipOrderMapper(SvipOrderMapper svipOrderMapper) {
		this.setDao(svipOrderMapper);
		this.svipOrderMapper = svipOrderMapper;
	}
	public int getSvipOrderInfo(Integer memberId) {
		int monthIntegral = 0;
		List<SvipOrderCustom> customs = svipOrderCustomMapper.getSvipOrderInfo(memberId);
		if(CollectionUtils.isNotEmpty(customs)){
			monthIntegral = customs.get(0).getMonthIntegral();
		}
		return monthIntegral;
	}
	public List<SvipOrder> findPaySuccessOrder(Integer memberId) {
		SvipOrderExample svipOrderExample = new SvipOrderExample();
		svipOrderExample.createCriteria().andStatusEqualTo(Const.COMBINE_ORDER_STATUS_PAID).andPayStatusEqualTo(Const.COMBINE_ORDER_PAY_STATUS_SUCCESS)
		.andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		return selectByExample(svipOrderExample);
	}
	
	
}

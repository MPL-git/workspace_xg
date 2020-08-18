package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.CollectionUtil;
import com.jf.dao.CombineOrderMapper;
import com.jf.entity.CombineOrder;
import com.jf.entity.CombineOrderExample;
import com.jf.entity.MemberInfo;
import com.jf.entity.MemberNovaRecord;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月27日 上午10:57:54 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CombineOrderService extends BaseService<CombineOrder, CombineOrderExample> {
	
	@Autowired
	private CombineOrderMapper combineOrderMapper;
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberNovaRecordService memberNovaRecordService;
	@Autowired
	public void setCombineOrderMapper(CombineOrderMapper combineOrderMapper) {
		this.setDao(combineOrderMapper);
		this.combineOrderMapper = combineOrderMapper;
	}

	public int getMemberSubOrderCount(MemberInfo memberInfo, Integer memberId, String openType) {
		int count = 0;
		if(memberInfo == null){
			memberInfo = memberInfoService.selectByPrimaryKey(memberId);
		}
		MemberNovaRecord memberNovaRecord = memberNovaRecordService.getCurrentContractSigning(memberInfo.getId(),openType);
		if(memberNovaRecord != null){
			Date novaProjectBeginDate = memberNovaRecord.getAgreementBeginDate();
			Date novaProjectEndDate = memberNovaRecord.getAgreementEndDate();
			//找出会员的下级
			List<Integer> memberIds = memberInfoService.getMemberSubUser(memberId);
			if(CollectionUtils.isNotEmpty(memberIds)){
				CombineOrderExample combineOrderExample = new CombineOrderExample();
				combineOrderExample.createCriteria().andMemberIdIn(memberIds).andStatusEqualTo("1").andPayDateGreaterThanOrEqualTo(novaProjectBeginDate)
				.andPayDateLessThanOrEqualTo(new Date()).andDelFlagEqualTo("0");
				count = countByExample(combineOrderExample);
			}
		}
		return count;
	}

	public CombineOrder getMemberOrder(Integer memberId, Integer combineOrderId) {
		CombineOrderExample example = new CombineOrderExample();
		example.createCriteria()
				.andIdEqualTo(combineOrderId)
				.andMemberIdEqualTo(memberId);
		List<CombineOrder> list = this.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}
}

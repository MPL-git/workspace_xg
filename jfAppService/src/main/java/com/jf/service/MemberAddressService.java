package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.Const;
import com.jf.common.ext.query.QueryObject;
import com.jf.dao.MemberAddressCustomMapper;
import com.jf.dao.MemberAddressMapper;
import com.jf.entity.MemberAddress;
import com.jf.entity.MemberAddressCustom;
import com.jf.entity.MemberAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月19日 下午6:51:03 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberAddressService extends BaseService<MemberAddress, MemberAddressExample> {
	@Autowired
	private MemberAddressMapper memberAddressMapper;
	
	@Autowired
	private MemberAddressCustomMapper memberAddressCustomMapper;
	
	@Autowired
	public void setMemberAddressMapper(MemberAddressMapper memberAddressMapper) {
		super.setDao(memberAddressMapper);
		this.memberAddressMapper = memberAddressMapper;
	}

	public void updateDefaultAddressByMemberId(Integer memberId) {
		
		memberAddressCustomMapper.updateDefaultAddressByMemberId(memberId);
	}

	public void updateDefaultAddressById(Integer id) {
		
		memberAddressCustomMapper.updateDefaultAddressById(id);
	}

	public Integer getMemberAddressCount(Map<String, Object> params) {
		
		return memberAddressCustomMapper.getMemberAddressCount(params);
	}

	public List<MemberAddressCustom> getMemberAddressList(Map<String, Object> params) {
		
		return memberAddressCustomMapper.getMemberAddressList(params);
	}

	public MemberAddressCustom getAddressById(Integer memberId, Integer addressId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("addressId", addressId);
		return memberAddressCustomMapper.getAddressById(params);
	}

	public MemberAddressCustom getAddressById(Map<String, Object> addressParamsMap) {
		
		return memberAddressCustomMapper.getAddressById(addressParamsMap);
	}

	public MemberAddressCustom getAddressByMemberId(Map<String, Object> addressParamsMap) {
		
		return memberAddressCustomMapper.getAddressByMemberId(addressParamsMap);
	}

	public List<MemberAddress> findListQuery(QueryObject queryObject) {
		
		return memberAddressMapper.selectByExample(builderQuery(queryObject));
	}

	private MemberAddressExample builderQuery(QueryObject queryObject) {
		MemberAddressExample example = new MemberAddressExample();
		MemberAddressExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo(Const.FLAG_FALSE);
		if(queryObject.getQuery("id") != null){
			criteria.andIdEqualTo(queryObject.getQueryToInt("id"));
		}
		if(queryObject.getQuery("memberId") != null){
			criteria.andMemberIdEqualTo(queryObject.getQueryToInt("memberId"));
		}
		if(queryObject.getQuery("sort") != null){
			example.setOrderByClause(queryObject.getQueryToStr("sort"));
		}
		return example;
	}
	
}

package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberShopFootprintCustomMapper;
import com.jf.dao.MemberShopFootprintMapper;
import com.jf.entity.MemberShopFootprint;
import com.jf.entity.MemberShopFootprintCustom;
import com.jf.entity.MemberShopFootprintExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MemberShopFootprintService extends BaseService<MemberShopFootprint, MemberShopFootprintExample> {
	@Autowired
	private MemberShopFootprintMapper memberShopFootprintMapper;
	@Autowired
	private MemberShopFootprintCustomMapper memberShopFootprintCustomMapper;
	@Autowired
	public void setMemberShopFootprintMapper(MemberShopFootprintMapper memberShopFootprintMapper) {
		this.setDao(memberShopFootprintMapper);
		this.memberShopFootprintMapper = memberShopFootprintMapper;
	}
	
	public void addMemberShopFootprint(Integer memberId, Integer id) {
		Date currentDate = new Date();
		MemberShopFootprintExample memberShopFootprintExample = new MemberShopFootprintExample();
		memberShopFootprintExample.createCriteria().andMemberIdEqualTo(memberId).andMchtIdEqualTo(id).andDelFlagEqualTo("0");
		List<MemberShopFootprint> ShopFootprints = selectByExample(memberShopFootprintExample);
		MemberShopFootprint memberShopFootprint = new MemberShopFootprint();
		if(CollectionUtils.isNotEmpty(ShopFootprints)){
			memberShopFootprint = ShopFootprints.get(0);
			memberShopFootprint.setCreateDate(currentDate);
			memberShopFootprint.setUpdateBy(memberId);
			memberShopFootprint.setUpdateDate(currentDate);
			updateByPrimaryKeySelective(memberShopFootprint);
		}else{
			memberShopFootprint.setMemberId(memberId);
			memberShopFootprint.setMchtId(id);;
			memberShopFootprint.setCreateBy(memberId);
			memberShopFootprint.setCreateDate(currentDate);
			memberShopFootprint.setUpdateDate(currentDate);
			memberShopFootprint.setDelFlag("0");
			insertSelective(memberShopFootprint);
		}
	}

	public List<Map<String, Object>> getMemberShopFootprintList(String memberId, Integer currentPage,Integer pageSize) {
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		if(currentPage > 9){
			return dataList;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<MemberShopFootprintCustom> shopFootprints = memberShopFootprintCustomMapper.getMemberShopFootprintList(params);
		if(CollectionUtils.isNotEmpty(shopFootprints)){
			for (MemberShopFootprintCustom shop: shopFootprints) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("mchtId", shop.getMchtId());
				dataMap.put("shopName", shop.getShopName());
				dataMap.put("shopLogo", StringUtil.getPic(shop.getShopLogo(), ""));
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	public void deleteMemberShopFootprint(Integer memberId) {
		Date currentDate = new Date();
		MemberShopFootprintExample memberShopFootprintExample = new MemberShopFootprintExample();
		memberShopFootprintExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		MemberShopFootprint memberShopFootprint = new MemberShopFootprint();
		memberShopFootprint.setDelFlag("1");
		memberShopFootprint.setUpdateBy(memberId);
		memberShopFootprint.setUpdateDate(currentDate);
		updateByExampleSelective(memberShopFootprint, memberShopFootprintExample);
	}

	public Integer getMemberShopFootprintCount(Integer memberId) {
		return memberShopFootprintCustomMapper.getMemberShopFootprintCount(memberId);
	}

}

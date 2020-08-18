package com.jf.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.jf.common.base.BaseService;

import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.common.utils.ListHelper;
import com.jf.entity.ActivityCustom;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberFootprintCustomMapper;
import com.jf.dao.MemberFootprintMapper;
import com.jf.entity.MemberFootprint;
import com.jf.entity.MemberFootprintCustom;
import com.jf.entity.MemberFootprintExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年4月20日 下午6:32:37 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class MemberFootprintService extends BaseService<MemberFootprint, MemberFootprintExample> {
	
	@Autowired
	private MemberFootprintMapper memberFootprintMapper;
	@Autowired
	private MemberFootprintCustomMapper memberFootprintCustomMapper;
	@Autowired
	private ActivityService activityService;

	@Autowired
	public void setMemberFootprintMapper(MemberFootprintMapper memberFootprintMapper) {
		super.setDao(memberFootprintMapper);
		this.memberFootprintMapper = memberFootprintMapper;
	}

	public List<Map<String, Object>> getMemberFootprintList(String memberId, Integer currentPage, Integer pageSize, int reqSource, int currentProductId) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		if (currentPage > 9) {
			return dataList;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("memberId", memberId);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage * pageSize);
		List<MemberFootprintCustom> memberFootprintCustomList;
		if (1 == reqSource) {
			params.put("currentProductId", currentProductId);
			memberFootprintCustomList = memberFootprintCustomMapper.findMemberFootprint(params);
			Map<Integer, ActivityCustom> activityMap = buildActivityMap(memberFootprintCustomList);
			if (memberFootprintCustomList != null && memberFootprintCustomList.size() > 0) {
				ActivityCustom activityCustom = null;
				for (MemberFootprintCustom custom : memberFootprintCustomList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", custom.getId());
					map.put("productId", custom.getProductId());
					map.put("name", custom.getName());
					activityCustom = activityMap.get(custom.getProductId());
					if (activityCustom == null) {
						map.put("originalPrice", custom.getTagPrice());
						map.put("productPrice", custom.getSaleOrMallPrice());
					}else{
						map.put("originalPrice", activityCustom.getTagPrice());
						map.put("productPrice", activityCustom.getSalePrice());
					}
					map.put("pic", StringUtil.getPic(custom.getPic(), "M"));
					dataList.add(map);
				}
			}
		} else {
			memberFootprintCustomList = memberFootprintCustomMapper.getMemberFootprintList(params);
			if (memberFootprintCustomList != null && memberFootprintCustomList.size() > 0) {
				for (MemberFootprintCustom custom : memberFootprintCustomList) {
					Map<String, Object> map = new HashMap<String, Object>();
					double originalPrice = custom.getTagPrice();
					double productPrice = custom.getSalePrice();
					map.put("id", custom.getId());
					map.put("productId", custom.getProductId());
					map.put("name", custom.getName());
					map.put("originalPrice", originalPrice);
					map.put("productPrice", productPrice);
					map.put("pic", StringUtil.getPic(custom.getPic(), "M"));
					dataList.add(map);
				}
			}
		}
		return dataList;
	}

	private Map<Integer, ActivityCustom> buildActivityMap(List<MemberFootprintCustom> memberFootprintCustomList) {
		List<ActivityCustom> activityCustomList = activityService.findByProductIds(ListHelper.extractIds(memberFootprintCustomList, new ListHelper.IdExtractor<MemberFootprintCustom>() {
			@Override
			public Integer getId(MemberFootprintCustom source) {
				return source.getProductId();
			}
		}));
		if (CollectionUtils.isEmpty(activityCustomList)) return Collections.emptyMap();

		Map<Integer, ActivityCustom> map = Maps.newHashMapWithExpectedSize(activityCustomList.size());
		for (ActivityCustom activityCustom : activityCustomList) {
			map.put(activityCustom.getProductId(), activityCustom);
		}
		return map;
	}

	public Integer getMemberFootprintCount(Map<String, Object> params) {
		
		return memberFootprintCustomMapper.getMemberFootprintCount(params);
	}

	public void addMemberFootprint(Integer memberId, Integer productId) {
		Date currentDate = new Date();
		MemberFootprintExample memberFootprintExample = new MemberFootprintExample();
		memberFootprintExample.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(productId).andDelFlagEqualTo("0");
		List<MemberFootprint> memberFootprints = selectByExample(memberFootprintExample);
		MemberFootprint memberFootprint = new MemberFootprint();
		if(CollectionUtils.isNotEmpty(memberFootprints)){
			memberFootprint = memberFootprints.get(0);
			memberFootprint.setCreateDate(currentDate);
			memberFootprint.setUpdateBy(memberId);
			memberFootprint.setUpdateDate(currentDate);
			updateByPrimaryKeySelective(memberFootprint);
		}else{
			memberFootprint.setMemberId(memberId);
			memberFootprint.setProductId(productId);
			memberFootprint.setCreateBy(memberId);
			memberFootprint.setCreateDate(currentDate);
			memberFootprint.setUpdateDate(currentDate);
			memberFootprint.setDelFlag("0");
			insertSelective(memberFootprint);
		}
	}

	public void deleteMemberFootprint(Integer memberId) {
//		Date currentDate = new Date();
		MemberFootprintExample memberFootprintExample = new MemberFootprintExample();
		memberFootprintExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		MemberFootprint memberFootprint = new MemberFootprint();
		memberFootprint.setDelFlag("1");
		memberFootprint.setUpdateBy(memberId);
//		memberFootprint.setUpdateDate(currentDate);
		updateByExampleSelective(memberFootprint, memberFootprintExample);
	}
	
	public Integer countMemberFootprint30Days(Map<String, Object> paramMap) {
		return memberFootprintCustomMapper.countMemberFootprint30Days(paramMap);
	}

	public Integer getMemberFootprintListCount(Integer memberId) {
		return memberFootprintCustomMapper.getMemberFootprintListCount(memberId);
	}

	public boolean hasMemberFootprint(int memberId) {
		MemberFootprintExample example = new MemberFootprintExample();
		example.createCriteria()
				.andMemberIdEqualTo(memberId)
				.andDelFlagEqualTo(StateConst.FALSE);
		example.setLimitSize(1);
		example.setLimitStart(0);
		List<MemberFootprint> list = this.selectByExample(example);
		return CollectionUtil.isNotEmpty(list);
	}

	/**
	 * 取用户近3个月浏览的5个三级类目商品
	 */
	public List<Integer> findMemberRecentlyScanProductType3(int memberId) {
		return memberFootprintCustomMapper.findMemberRecentlyScanProductType3(memberId);
	}
}

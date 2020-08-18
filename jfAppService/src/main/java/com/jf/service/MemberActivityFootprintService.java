package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.common.utils.StringUtil;
import com.jf.dao.MemberActivityFootprintCustomMapper;
import com.jf.dao.MemberActivityFootprintMapper;
import com.jf.entity.MemberActivityFootprint;
import com.jf.entity.MemberActivityFootprintCustom;
import com.jf.entity.MemberActivityFootprintExample;
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
public class MemberActivityFootprintService extends BaseService<MemberActivityFootprint, MemberActivityFootprintExample> {
	@Autowired
	private MemberActivityFootprintMapper memberActivityFootprintMapper;
	@Autowired
	private MemberActivityFootprintCustomMapper memberActivityFootprintCustomMapper;
	@Autowired
	public void setMemberActivityFootprintMapper(MemberActivityFootprintMapper memberActivityFootprintMapper) {
		this.setDao(memberActivityFootprintMapper);
		this.memberActivityFootprintMapper = memberActivityFootprintMapper;
	}
	
	public void addMemberActivityFootprint(Integer memberId, Integer id) {
		Date currentDate = new Date();
		MemberActivityFootprintExample memberActivityFootprintExample = new MemberActivityFootprintExample();
		memberActivityFootprintExample.createCriteria().andMemberIdEqualTo(memberId).andActivityIdEqualTo(id).andDelFlagEqualTo("0");
		List<MemberActivityFootprint> activityFootprints = selectByExample(memberActivityFootprintExample);
		MemberActivityFootprint memberActivityFootprint = new MemberActivityFootprint();
		if(CollectionUtils.isNotEmpty(activityFootprints)){
			memberActivityFootprint = activityFootprints.get(0);
			memberActivityFootprint.setCreateDate(currentDate);
			memberActivityFootprint.setUpdateBy(memberId);
			memberActivityFootprint.setUpdateDate(currentDate);
			updateByPrimaryKeySelective(memberActivityFootprint);
		}else{
			memberActivityFootprint.setMemberId(memberId);
			memberActivityFootprint.setActivityId(id);
			memberActivityFootprint.setCreateBy(memberId);
			memberActivityFootprint.setCreateDate(currentDate);
			memberActivityFootprint.setUpdateDate(currentDate);
			memberActivityFootprint.setDelFlag("0");
			insertSelective(memberActivityFootprint);
		}
	}

	public List<Map<String, Object>> getMemberActivityFootprintList(String memberId, Integer currentPage,Integer pageSize) {
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Date currentDate = new Date();
		if(currentPage > 9){
			return dataList;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("memberId", memberId);
		params.put("pageSize", pageSize);
		params.put("currentPage", currentPage*pageSize);
		List<MemberActivityFootprintCustom> activityFootprints = memberActivityFootprintCustomMapper.getMemberActivityFootprintList(params);
		if(CollectionUtils.isNotEmpty(activityFootprints)){
			for (MemberActivityFootprintCustom activity: activityFootprints) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				Date activityBeginTime = activity.getActivityBeginTime();
				String activityRemainingTime = "";
				if(activityBeginTime.after(currentDate)){
					activityRemainingTime = DateUtil.getFormatDate(activity.getActivityBeginTime(), "dd日 HH:mm 开售");
				}else{
					activityRemainingTime = DateUtil.getActivityRemainingTime(activity.getActivityEndTime());
				}
				dataMap.put("activityId", activity.getActivityId());
				dataMap.put("activityName", activity.getActivityName());
				dataMap.put("entryPic", StringUtil.getPic(activity.getEntryPic(), ""));
				dataMap.put("activityTime", activityRemainingTime);
				dataMap.put("activityAreaId", activity.getActivityAreaId());
				dataMap.put("benefitPoint", activity.getBenefitPoint()==null?"":activity.getBenefitPoint());
				dataMap.put("source", "2");
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	public void deleteMemberActivityFootprint(Integer memberId) {
		Date currentDate = new Date();
		MemberActivityFootprintExample memberActivityFootprintExample = new MemberActivityFootprintExample();
		memberActivityFootprintExample.createCriteria().andMemberIdEqualTo(memberId).andDelFlagEqualTo("0");
		MemberActivityFootprint memberActivityFootprint = new MemberActivityFootprint();
		memberActivityFootprint.setDelFlag("1");
		memberActivityFootprint.setUpdateBy(memberId);
		memberActivityFootprint.setUpdateDate(currentDate);
		updateByExampleSelective(memberActivityFootprint, memberActivityFootprintExample);
	}

	public Integer getMemberActivityFootprintCount(Integer memberId) {
		return memberActivityFootprintCustomMapper.getMemberActivityFootprintCount(memberId);
	}

}

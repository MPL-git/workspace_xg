package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.ActivityBrandGroupMapper;
import com.jf.entity.ActivityBrandGroup;
import com.jf.entity.ActivityBrandGroupExample;

@Service
@Transactional
public class ActivityBrandGroupService extends BaseService<ActivityBrandGroup, ActivityBrandGroupExample> {
	@Autowired
	private ActivityBrandGroupMapper activityBrandGroupMapper;
	@Autowired
	public void setActivityBrandGroupMapper(ActivityBrandGroupMapper activityBrandGroupMapper) {
		this.setDao(activityBrandGroupMapper);
		this.activityBrandGroupMapper = activityBrandGroupMapper;
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> getTodayPreferentialActivityBrandGroup(String productTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		ActivityBrandGroupExample activityBrandGroupExample = new ActivityBrandGroupExample();
		activityBrandGroupExample.createCriteria().andAppCatalogEqualTo(productTypeId).andDelFlagEqualTo("0").andStatusEqualTo("1");
		activityBrandGroupExample.setOrderByClause("id desc");
		List<ActivityBrandGroup> activityBrandGroups = selectByExample(activityBrandGroupExample);
		if(CollectionUtils.isNotEmpty(activityBrandGroups)){
			for (ActivityBrandGroup activityBrandGroup : activityBrandGroups) {
				Map<String, String> dataMap = new HashMap<String, String>();
				String position = activityBrandGroup.getPosition().toString();
				String name = activityBrandGroup.getName();
				String entryPic = activityBrandGroup.getEntryPic();
				String posterPic = activityBrandGroup.getPosterPic();
				dataMap.put("activityBrandGroupId", activityBrandGroup.getId().toString());
				dataMap.put("position", position);
				dataMap.put("name", name);
				dataMap.put("entryPic", StringUtil.getPic(entryPic, ""));
				dataMap.put("posterPic", StringUtil.getPic(posterPic, ""));
				if(map.containsKey(position)){
					dataList = (List<Map<String, String>>) map.get(position);
				}else{
					dataList = new ArrayList<Map<String, String>>();
				}
				dataList.add(dataMap);
				map.put(position, dataList);
			}
		}
		return map;
	}
	
	
}

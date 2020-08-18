package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.common.utils.StringUtil;
import com.jf.dao.BlackListMapper;
import com.jf.entity.BlackList;
import com.jf.entity.BlackListExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年5月25日 上午10:47:02 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class BlackListService extends BaseService<BlackList, BlackListExample> {
	@Autowired
	private BlackListMapper blackListMapper;

	@Autowired
	public void setBlackListMapper(BlackListMapper blackListMapper) {
		this.setDao(blackListMapper);
		this.blackListMapper = blackListMapper;
	}

	public Map<String, Object> getIsBlack(Integer memberId, String type) {
		//签到黑名单
		Date date = new Date();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("blackReason", "");
		map.put("isBlack", false);
		if(memberId != null && !StringUtil.isBlank(type)){
			BlackListExample blackListExample = new BlackListExample();
			blackListExample.createCriteria().andMemberIdEqualTo(memberId).andBlackTypeEqualTo(type).andDelFlagEqualTo("0")
			.andBlackDateLessThanOrEqualTo(date).andBlackToDateGreaterThanOrEqualTo(date);
			blackListExample.setLimitStart(0);
			blackListExample.setLimitSize(1);
			blackListExample.setOrderByClause("id desc");
			List<BlackList> blackLists = selectByExample(blackListExample);
			if(CollectionUtils.isNotEmpty(blackLists)){
				map.put("blackReason", blackLists.get(0).getBlackReason());
				map.put("isBlack", true);
			}
		}
		return map;
	}
	
	
}

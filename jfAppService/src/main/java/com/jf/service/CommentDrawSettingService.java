package com.jf.service;


import com.jf.common.base.BaseService;
import com.jf.dao.CommentDrawSettingMapper;
import com.jf.entity.CommentDraw;
import com.jf.entity.CommentDrawSetting;
import com.jf.entity.CommentDrawSettingExample;
import com.jf.entity.SubOrder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
  * @author  chenwf: 
  * @date 创建时间：2018年6月29日 上午9:22:51 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class CommentDrawSettingService extends BaseService<CommentDrawSetting, CommentDrawSettingExample> {
	@Autowired
	private CommentDrawSettingMapper commentDrawSettingMapper;
	@Autowired
	private CommentDrawService commentDrawService;

	@Autowired
	public void setCommentDrawSettingMapper(CommentDrawSettingMapper commentDrawSettingMapper) {
		this.setDao(commentDrawSettingMapper);
		this.commentDrawSettingMapper = commentDrawSettingMapper;
	}

	public Map<String, Object> getDrawIntegral(Integer memberId, SubOrder subOrder) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		Integer subOrderId = subOrder.getId();
		boolean isHasActivity = false;
		Integer integral = 0;
		CommentDrawSettingExample commentDrawSettingExample = new CommentDrawSettingExample();
		commentDrawSettingExample.createCriteria().andStatusEqualTo("1").andBeginDateLessThanOrEqualTo(date)
		.andEndDateGreaterThanOrEqualTo(date).andDelFlagEqualTo("0");
		commentDrawSettingExample.setLimitStart(0);
		commentDrawSettingExample.setLimitSize(1);
		commentDrawSettingExample.setOrderByClause("id desc");
		List<CommentDrawSetting> drawSettings = selectByExample(commentDrawSettingExample);
		if(CollectionUtils.isNotEmpty(drawSettings)){
			CommentDrawSetting setting = drawSettings.get(0);
			Integer integralMin = setting.getIntegralMin();
			Integer integralMax = setting.getIntegralMax();
			if(integralMin != null && integralMax != null){
				Random rand = new Random();
				isHasActivity = true;
				integral = rand.nextInt(integralMax-integralMin+1)+integralMin;
				CommentDraw commentDraw = new CommentDraw();
				commentDraw.setSubOrderId(subOrderId);
				commentDraw.setMemberId(memberId);
				commentDraw.setIntegral(integral);
				//评价单表一评价完成就设计成已抽，因为用户评价完成，不管去不去抽奖，下次都不能在抽奖
				//这边就设计成新增状态，等到了真正去抽奖的话才update成1
				commentDraw.setStatus("0");
				commentDraw.setCreateBy(memberId);
				commentDraw.setCreateDate(date);
				commentDraw.setDelFlag("0");
				commentDrawService.insertSelective(commentDraw);
			}
		}
		map.put("isHasActivity", isHasActivity);
		map.put("integral", integral);
		return map;
	}
	
}

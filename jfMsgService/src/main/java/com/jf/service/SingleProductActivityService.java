package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.jf.common.base.BaseService;
import com.jf.dao.SingleProductActivityCustomMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SingleProductActivityMapper;
import com.jf.entity.SingleProductActivity;
import com.jf.entity.SingleProductActivityExample;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月29日 下午2:58:10 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class SingleProductActivityService extends BaseService<SingleProductActivity, SingleProductActivityExample> {
	@Autowired
	private SingleProductActivityMapper singleProductActivityMapper;

	@Autowired
	private SingleProductActivityCustomMapper singleProductActivityCustomMapper;
	
	@Autowired
	public void setSingleProductActivityMapper(SingleProductActivityMapper singleProductActivityMapper) {
		this.setDao(singleProductActivityMapper);
		this.singleProductActivityMapper = singleProductActivityMapper;
	}

	public void updateSingleActivityUnrealityNum() {
		Date currentDate = new Date();
		Random rand = new Random();
		List<String> types = new ArrayList<String>();
		types.add("7");
		types.add("8");
		SingleProductActivityExample productActivityExample = new SingleProductActivityExample();
		productActivityExample.createCriteria().andTypeIn(types).andStatusEqualTo("1").andAuditStatusEqualTo("3")
					.andBeginTimeLessThanOrEqualTo(currentDate).andEndTimeGreaterThan(currentDate)
					.andDelFlagEqualTo("0").andTomorrowIncreaseMinGreaterThan(0).andIsCloseEqualTo("0");
		List<SingleProductActivity> singleProductActivities = selectByExample(productActivityExample);
		if(CollectionUtils.isNotEmpty(singleProductActivities)){
			for (SingleProductActivity singleProductActivity : singleProductActivities) {
				Integer unrealityNum = singleProductActivity.getUnrealityNum();
				Integer tomorrowIncreaseMin = singleProductActivity.getTomorrowIncreaseMin();
				Integer tomorrowIncreaseMax = singleProductActivity.getTomorrowIncreaseMax();
				if(tomorrowIncreaseMin > tomorrowIncreaseMax){
					continue;
				}
				Integer num = rand.nextInt(tomorrowIncreaseMax-tomorrowIncreaseMin+1)+tomorrowIncreaseMin;
				singleProductActivity.setUnrealityNum(unrealityNum+num);
				singleProductActivity.setUpdateDate(currentDate);
				updateByPrimaryKeySelective(singleProductActivity);
			}
		}
	}

	public void offlineClosedSingleProduct() {
		singleProductActivityCustomMapper.offlineClosedSingleProduct();
	}

}

package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.dao.AndroidChannelGroupDiscountRateCustomMapper;
import com.jf.dao.AndroidChannelGroupDiscountRateMapper;
import com.jf.dao.SpreadDataMapper;
import com.jf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pengl
 * @create 2019-12-04 下午 4:16
 */
@Service
@Transactional
public class AndroidChannelGroupDiscountRateService extends BaseService<AndroidChannelGroupDiscountRate, AndroidChannelGroupDiscountRateExample> {

	@Autowired
	private AndroidChannelGroupDiscountRateMapper androidChannelGroupDiscountRateMapper;

	@Autowired
	private AndroidChannelGroupDiscountRateCustomMapper androidChannelGroupDiscountRateCustomMapper;

	@Autowired
	private SpreadDataMapper spreadDataMapper;

	@Autowired
	public void setAndroidChannelGroupDiscountRateMapper(AndroidChannelGroupDiscountRateMapper androidChannelGroupDiscountRateMapper) {
		super.setDao(androidChannelGroupDiscountRateMapper);
		this.androidChannelGroupDiscountRateMapper = androidChannelGroupDiscountRateMapper;
	}

	public int countByCustomExample(AndroidChannelGroupDiscountRateCustomExample example) {
		return androidChannelGroupDiscountRateCustomMapper.countByCustomExample(example);
	}

	public List<AndroidChannelGroupDiscountRateCustom> selectByCustomExample(AndroidChannelGroupDiscountRateCustomExample example) {
		return androidChannelGroupDiscountRateCustomMapper.selectByCustomExample(example);
	}

	public AndroidChannelGroupDiscountRateCustom selectByCustomPrimaryKey(Integer id) {
		return androidChannelGroupDiscountRateCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") AndroidChannelGroupDiscountRate record, @Param("example") AndroidChannelGroupDiscountRateCustomExample example) {
		return androidChannelGroupDiscountRateCustomMapper.updateByCustomExampleSelective(record, example);
	}


	public void androidChannelGroupDiscountRate() {
		AndroidChannelGroupDiscountRateExample androidChannelGroupDiscountRateExample = new AndroidChannelGroupDiscountRateExample();
		androidChannelGroupDiscountRateExample.createCriteria().andDelFlagEqualTo("0");
		androidChannelGroupDiscountRateExample.setOrderByClause(" date desc");
		androidChannelGroupDiscountRateExample.setLimitStart(0);
		androidChannelGroupDiscountRateExample.setLimitSize(1);
		List<AndroidChannelGroupDiscountRate> androidChannelGroupDiscountRateList = androidChannelGroupDiscountRateMapper.selectByExample(androidChannelGroupDiscountRateExample);
		Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(DateUtil.getDate(), "yyyy-MM-dd"), "yyyy-MM-dd");
		Date endDate = DateUtil.getDate(DateUtil.getFormatDate(DateUtil.getDate(), "yyyy-MM-dd"), "yyyy-MM-dd");
		if(androidChannelGroupDiscountRateList != null && androidChannelGroupDiscountRateList.size() > 0 ) {
			beginDate = DateUtil.getDateAfter(androidChannelGroupDiscountRateList.get(0).getDate(), 1);
		}else {
			SpreadDataExample spreadDataExample = new SpreadDataExample();
			spreadDataExample.createCriteria().andDelFlagEqualTo("0");
			spreadDataExample.setOrderByClause(" date asc");
			spreadDataExample.setLimitStart(0);
			spreadDataExample.setLimitSize(1);
			List<SpreadData> spreadDataList = spreadDataMapper.selectByExample(spreadDataExample);
			if(spreadDataList != null && spreadDataList.size() > 0 ) {
				beginDate = spreadDataList.get(0).getDate();
			}
		}
		List<String> dateList = DateUtil.getBetweenDates(beginDate, endDate, new SimpleDateFormat("yyyy-MM-dd"), "day");
		for(String dateStr : dateList ) {
			androidChannelGroupDiscountRateCustomMapper.insertAndroidChannelGroupDiscountRateList(dateStr);
		}
	}

}

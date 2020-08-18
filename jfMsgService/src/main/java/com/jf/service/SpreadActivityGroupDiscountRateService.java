package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.utils.DateUtil;
import com.jf.dao.SpreadActivityGroupDiscountRateCustomMapper;
import com.jf.dao.SpreadActivityGroupDiscountRateMapper;
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
 * @create 2019-12-04 下午 4:19
 */
@Service
@Transactional
public class SpreadActivityGroupDiscountRateService extends BaseService<SpreadActivityGroupDiscountRate, SpreadActivityGroupDiscountRateExample> {

	@Autowired
	private SpreadActivityGroupDiscountRateMapper spreadActivityGroupDiscountRateMapper;

	@Autowired
	private SpreadActivityGroupDiscountRateCustomMapper spreadActivityGroupDiscountRateCustomMapper;

	@Autowired
	private SpreadDataMapper spreadDataMapper;

	@Autowired
	public void setSpreadActivityGroupDiscountRateMapper(SpreadActivityGroupDiscountRateMapper spreadActivityGroupDiscountRateMapper) {
		super.setDao(spreadActivityGroupDiscountRateMapper);
		this.spreadActivityGroupDiscountRateMapper = spreadActivityGroupDiscountRateMapper;
	}

	public int countByCustomExample(SpreadActivityGroupDiscountRateCustomExample example) {
		return spreadActivityGroupDiscountRateCustomMapper.countByCustomExample(example);
	}

	public List<SpreadActivityGroupDiscountRateCustom> selectByCustomExample(SpreadActivityGroupDiscountRateCustomExample example) {
		return spreadActivityGroupDiscountRateCustomMapper.selectByCustomExample(example);
	}

	public SpreadActivityGroupDiscountRateCustom selectByCustomPrimaryKey(Integer id) {
		return spreadActivityGroupDiscountRateCustomMapper.selectByCustomPrimaryKey(id);
	}

	public int updateByCustomExampleSelective(@Param("record") SpreadActivityGroupDiscountRate record, @Param("example") SpreadActivityGroupDiscountRateCustomExample example) {
		return spreadActivityGroupDiscountRateCustomMapper.updateByCustomExampleSelective(record, example);
	}


	public void spreadActivityGroupDiscountRate() {
		SpreadActivityGroupDiscountRateExample spreadActivityGroupDiscountRateExample = new SpreadActivityGroupDiscountRateExample();
		spreadActivityGroupDiscountRateExample.createCriteria().andDelFlagEqualTo("0");
		spreadActivityGroupDiscountRateExample.setOrderByClause(" date desc");
		spreadActivityGroupDiscountRateExample.setLimitStart(0);
		spreadActivityGroupDiscountRateExample.setLimitSize(1);
		List<SpreadActivityGroupDiscountRate> spreadActivityGroupDiscountRateList = spreadActivityGroupDiscountRateMapper.selectByExample(spreadActivityGroupDiscountRateExample);
		Date beginDate = DateUtil.getDate(DateUtil.getFormatDate(DateUtil.getDate(), "yyyy-MM-dd"), "yyyy-MM-dd");
		Date endDate = DateUtil.getDate(DateUtil.getFormatDate(DateUtil.getDate(), "yyyy-MM-dd"), "yyyy-MM-dd");
		if(spreadActivityGroupDiscountRateList != null && spreadActivityGroupDiscountRateList.size() > 0 ) {
			beginDate = DateUtil.getDateAfter(spreadActivityGroupDiscountRateList.get(0).getDate(), 1);
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
			spreadActivityGroupDiscountRateCustomMapper.insertSpreadActivityGroupDiscountRateList(dateStr);
		}
	}

}

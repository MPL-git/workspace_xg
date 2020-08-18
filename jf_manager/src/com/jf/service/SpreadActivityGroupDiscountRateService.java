package com.jf.service;

import com.jf.common.utils.DateUtil;
import com.jf.dao.SpreadActivityGroupDiscountRateCustomMapper;
import com.jf.dao.SpreadActivityGroupDiscountRateMapper;
import com.jf.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


	public void addSpreadActivityGroupDiscountRate(Integer groupNameId, Date discountRateDateBegin, Date discountRateDateEnd, BigDecimal discountRate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> dateList = DateUtil.getBetweenDates(discountRateDateBegin, discountRateDateEnd, sdf, "day");
			SpreadActivityGroupDiscountRateExample spreadActivityGroupDiscountRateExample = new SpreadActivityGroupDiscountRateExample();
			spreadActivityGroupDiscountRateExample.createCriteria().andDelFlagEqualTo("0").andSpreadActivityGroupIdEqualTo(groupNameId)
					.andDateBetween(discountRateDateBegin, discountRateDateEnd);
			List<SpreadActivityGroupDiscountRate> spreadActivityGroupDiscountRateList = spreadActivityGroupDiscountRateMapper.selectByExample(spreadActivityGroupDiscountRateExample);
			for(String date : dateList) {
				SpreadActivityGroupDiscountRate spreadActivityGroupDiscountRate = new SpreadActivityGroupDiscountRate();
				spreadActivityGroupDiscountRate.setDiscountRate(discountRate);
				spreadActivityGroupDiscountRate.setSpreadActivityGroupId(groupNameId);
				spreadActivityGroupDiscountRate.setDate(sdf.parse(date));
				for(SpreadActivityGroupDiscountRate groupDiscountRate : spreadActivityGroupDiscountRateList) {
					if(date.equals(sdf.format(groupDiscountRate.getDate()))) {
						spreadActivityGroupDiscountRate.setId(groupDiscountRate.getId());
						break;
					}
				}
				if(spreadActivityGroupDiscountRate.getId() != null) {
					spreadActivityGroupDiscountRate.setUpdateDate(new Date());
					spreadActivityGroupDiscountRateMapper.updateByPrimaryKeySelective(spreadActivityGroupDiscountRate);
				}else {
					spreadActivityGroupDiscountRate.setCreateDate(new Date());
					spreadActivityGroupDiscountRate.setDelFlag("0");
					spreadActivityGroupDiscountRateMapper.insertSelective(spreadActivityGroupDiscountRate);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


}

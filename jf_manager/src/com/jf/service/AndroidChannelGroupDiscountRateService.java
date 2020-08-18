package com.jf.service;

import com.jf.common.utils.DateUtil;
import com.jf.dao.AndroidChannelGroupDiscountRateCustomMapper;
import com.jf.dao.AndroidChannelGroupDiscountRateMapper;
import com.jf.entity.AndroidChannelGroupDiscountRate;
import com.jf.entity.AndroidChannelGroupDiscountRateCustom;
import com.jf.entity.AndroidChannelGroupDiscountRateCustomExample;
import com.jf.entity.AndroidChannelGroupDiscountRateExample;
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

	public void addAndroidChannelGroupDiscountRate(Integer groupNameId, Date discountRateDateBegin, Date discountRateDateEnd, BigDecimal discountRate) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<String> dateList = DateUtil.getBetweenDates(discountRateDateBegin, discountRateDateEnd, sdf, "day");
			AndroidChannelGroupDiscountRateExample androidChannelGroupDiscountRateExample = new AndroidChannelGroupDiscountRateExample();
			androidChannelGroupDiscountRateExample.createCriteria().andDelFlagEqualTo("0").andAndroidChannelGroupIdEqualTo(groupNameId)
					.andDateBetween(discountRateDateBegin, discountRateDateEnd);
			List<AndroidChannelGroupDiscountRate> androidChannelGroupDiscountRateList = androidChannelGroupDiscountRateMapper.selectByExample(androidChannelGroupDiscountRateExample);
			for(String date : dateList) {
				AndroidChannelGroupDiscountRate androidChannelGroupDiscountRate = new AndroidChannelGroupDiscountRate();
				androidChannelGroupDiscountRate.setDiscountRate(discountRate);
				androidChannelGroupDiscountRate.setAndroidChannelGroupId(groupNameId);
				androidChannelGroupDiscountRate.setDate(sdf.parse(date));
				for(AndroidChannelGroupDiscountRate groupDiscountRate : androidChannelGroupDiscountRateList) {
					if(date.equals(sdf.format(groupDiscountRate.getDate()))) {
						androidChannelGroupDiscountRate.setId(groupDiscountRate.getId());
						break;
					}
				}
				if(androidChannelGroupDiscountRate.getId() != null) {
					androidChannelGroupDiscountRate.setUpdateDate(new Date());
					androidChannelGroupDiscountRateMapper.updateByPrimaryKeySelective(androidChannelGroupDiscountRate);
				}else {
					androidChannelGroupDiscountRate.setCreateDate(new Date());
					androidChannelGroupDiscountRate.setDelFlag("0");
					androidChannelGroupDiscountRateMapper.insertSelective(androidChannelGroupDiscountRate);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}



}

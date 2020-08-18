package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.common.constant.StateConst;
import com.jf.common.utils.CollectionUtil;
import com.jf.dao.SvipMemberSettingMapper;
import com.jf.entity.SvipMemberSetting;
import com.jf.entity.SvipMemberSettingExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SvipMemberSettingService extends BaseService<SvipMemberSetting, SvipMemberSettingExample> {
	@Autowired
	private SvipMemberSettingMapper svipMemberSettingMapper;
	@Autowired
	public void setSvipMemberSettingMapper(SvipMemberSettingMapper svipMemberSettingMapper) {
		this.setDao(svipMemberSettingMapper);
		this.svipMemberSettingMapper = svipMemberSettingMapper;
	}
	
	public List<Map<String, Object>> getSvipSettingList() {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<SvipMemberSetting> svipMemberSettings = findModels();
		if(CollectionUtils.isNotEmpty(svipMemberSettings)){
			BigDecimal zero = new BigDecimal("0");
			BigDecimal amount = svipMemberSettings.get(0).getPrice();
			for (SvipMemberSetting svipMemberSetting : svipMemberSettings) {
				Map<String, Object> dataMap = new HashMap<>();
				Integer id = svipMemberSetting.getId();
				Integer openingYear= svipMemberSetting.getOpeningYear();
				BigDecimal price = svipMemberSetting.getPrice();
				Integer giveIntegral = svipMemberSetting.getIntegral() == null ? 0 : svipMemberSetting.getIntegral();
				Integer monthIntegral = svipMemberSetting.getMonthIntegral() == null ? 0 : svipMemberSetting.getMonthIntegral();
				BigDecimal buyPreferentialAmount = amount.multiply(new BigDecimal(openingYear)).subtract(price);
				String title = "";
				String preferentialContent = "";
				String preferentialContent2 = "";
				String saveContent = "";
				if(openingYear == 1){
					title = "开通1年";
					preferentialContent2 = "分12个月领取";
				}else if(openingYear == 2){
					title = "开通2年";
					preferentialContent2 = "分24个月领取";
				}else if(openingYear == 3){
					title = "开通3年";
					preferentialContent2 = "分36个月领取";
				}
				if(giveIntegral > 0){
					preferentialContent = "得"+giveIntegral+"积分";
				}
				
				if(buyPreferentialAmount.compareTo(zero) > 0){
					saveContent = "省"+buyPreferentialAmount.stripTrailingZeros().toPlainString();
				}
				dataMap.put("svipSettingId", id);
				dataMap.put("price", price.stripTrailingZeros().toPlainString());
				dataMap.put("giveIntegral", giveIntegral);
				dataMap.put("monthIntegral", monthIntegral);
				dataMap.put("title", title);
				dataMap.put("saveContent", saveContent);
				dataMap.put("preferentialContent", preferentialContent);
				dataMap.put("preferentialContent2", preferentialContent2);
				dataList.add(dataMap);
			}
		}
		return dataList;
	}

	public List<SvipMemberSetting> findModels() {
		SvipMemberSettingExample svipMemberSettingExample = new SvipMemberSettingExample();
		svipMemberSettingExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0");
		svipMemberSettingExample.setOrderByClause("price");
		return selectByExample(svipMemberSettingExample);
	}
	
	public List<SvipMemberSetting> findModel(Integer id) {
		SvipMemberSettingExample svipMemberSettingExample = new SvipMemberSettingExample();
		svipMemberSettingExample.createCriteria().andStatusEqualTo("1").andDelFlagEqualTo("0").andIdEqualTo(id);
		return selectByExample(svipMemberSettingExample);
	}

	public SvipMemberSetting getByYear(Integer year) {
		SvipMemberSettingExample example = new SvipMemberSettingExample();
		example.createCriteria()
				.andOpeningYearEqualTo(year)
				.andStatusEqualTo("1")
				.andDelFlagEqualTo(StateConst.FALSE);
		List<SvipMemberSetting> list = this.selectByExample(example);
		return CollectionUtil.isEmpty(list) ? null : list.get(0);
	}
}

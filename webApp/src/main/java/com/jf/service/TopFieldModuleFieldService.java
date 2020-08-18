package com.jf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jf.common.base.BaseService;
import com.jf.common.utils.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.TopFieldModuleFieldMapper;
import com.jf.entity.DecorateModule;
import com.jf.entity.TopFieldModuleField;
import com.jf.entity.TopFieldModuleFieldExample;

@Service
@Transactional
public class TopFieldModuleFieldService extends BaseService<TopFieldModuleField, TopFieldModuleFieldExample> {

	@Autowired
	private TopFieldModuleFieldMapper topFieldModuleFieldMapper;
	
	@Autowired
	public void setTopFieldModuleFieldMapper(TopFieldModuleFieldMapper topFieldModuleFieldMapper) {
		super.setDao(topFieldModuleFieldMapper);
		this.topFieldModuleFieldMapper = topFieldModuleFieldMapper;
	}
	
	/**
	 * 
	 * @Title getBgModules   
	 * @Description TODO(固定顶部栏)   
	 * @author pengl
	 * @date 2018年10月31日 下午5:30:37
	 */
	public Map<String, Object> getBgModules(DecorateModule decorateModule) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> topFieldModuleMapList = new ArrayList<Map<String,Object>>();
		TopFieldModuleFieldExample topFieldModuleFieldExample = new TopFieldModuleFieldExample();
		topFieldModuleFieldExample.createCriteria().andDelFlagEqualTo("0")
			.andDecorateModuleIdEqualTo(decorateModule.getId())
			.andLinkDecorateAreaIdIsNotNull();
		topFieldModuleFieldExample.setOrderByClause("ifnull(seq_no,99999),id asc");
		List<TopFieldModuleField> topFieldModuleFieldList = topFieldModuleFieldMapper.selectByExample(topFieldModuleFieldExample);
		for(TopFieldModuleField topFieldModuleField : topFieldModuleFieldList) {
			Map<String, Object> topFieldModuleMap = new HashMap<String, Object>();
			topFieldModuleMap.put("topFieldModuleId", topFieldModuleField.getId());
			topFieldModuleMap.put("decorateModuleId", topFieldModuleField.getDecorateModuleId());
			topFieldModuleMap.put("fieldName", topFieldModuleField.getFieldName());
			topFieldModuleMap.put("linkDecorateAreaId", topFieldModuleField.getLinkDecorateAreaId());
			topFieldModuleMapList.add(topFieldModuleMap);
		}
		map.put("topFieldModuleList", topFieldModuleMapList);
		map.put("fieldFontColor", decorateModule.getFieldFontColor());
		map.put("fieldSelectFontColor", decorateModule.getFieldSelectFontColor());
		map.put("fieldBgColor", decorateModule.getFieldBgColor());
		map.put("fieldBgPic", StringUtil.getPic(decorateModule.getFieldBgPic(),""));
		map.put("openFontColor", decorateModule.getOpenFontColor());
		map.put("openFieldBgColor", decorateModule.getOpenFieldBgColor());
		map.put("openBtnBgColor", decorateModule.getOpenBtnBgColor());
		map.put("openBtnArrowColor", decorateModule.getOpenBtnArrowColor());
		return map;
	}
	
	
	
}

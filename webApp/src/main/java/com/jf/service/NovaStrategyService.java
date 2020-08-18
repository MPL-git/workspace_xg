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

import com.jf.dao.NovaStrategyMapper;
import com.jf.entity.NovaStrategy;
import com.jf.entity.NovaStrategyExample;

@Service
@Transactional
public class NovaStrategyService extends BaseService<NovaStrategy, NovaStrategyExample> {
	@Autowired
	private NovaStrategyMapper novaStrategyMapper;
	@Autowired
	public void setNovaStrategyMapper(NovaStrategyMapper novaStrategyMapper) {
		this.setDao(novaStrategyMapper);
		this.novaStrategyMapper = novaStrategyMapper;
	}
	public List<Map<String, Object>> getNovaPlanHelpCenter(String type) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<NovaStrategy> novaStrategies = getModelsByType(type);
		if(CollectionUtils.isNotEmpty(novaStrategies)){
			for (NovaStrategy novaStrategy : novaStrategies) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("title", novaStrategy.getTitle() == null ? "" : novaStrategy.getTitle());
				dataMap.put("content", novaStrategy.getContent() == null ? "" : novaStrategy.getContent());
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	public List<NovaStrategy> getModelsByType(String type) {
		NovaStrategyExample novaStrategyExample = new NovaStrategyExample();
		novaStrategyExample.createCriteria().andTypeEqualTo(type).andDelFlagEqualTo("0");
		novaStrategyExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return selectByExample(novaStrategyExample);
	}
	public List<NovaStrategy> getModels() {
		NovaStrategyExample novaStrategyExample = new NovaStrategyExample();
		novaStrategyExample.createCriteria().andDelFlagEqualTo("0");
		novaStrategyExample.setOrderByClause("ifnull(seq_no,99999),id desc");
		return selectByExample(novaStrategyExample);
	}
	
	
}

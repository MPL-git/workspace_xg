package com.jf.dao;

import com.jf.entity.AdPvStatisticalCustom;
import com.jf.entity.AdPvStatisticalCustomExample;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AdPvStatisticalCustomMapper{
	//广告位列表查询
	List<AdPvStatisticalCustom> selectAdPvStatisticalCustomByExample(
			AdPvStatisticalCustomExample adPvStatisticalCustomExample);
	//广告位列表数量
	Integer countCustomByExample(
			AdPvStatisticalCustomExample adPvStatisticalCustomExample);
	
}
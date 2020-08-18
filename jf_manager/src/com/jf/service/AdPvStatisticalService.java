package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.AdPvStatisticalCustomMapper;
import com.jf.entity.AdPvStatistical;
import com.jf.entity.AdPvStatisticalCustom;
import com.jf.entity.AdPvStatisticalCustomExample;
import com.jf.entity.AdPvStatisticalExample;

@Service
public class AdPvStatisticalService extends BaseService<AdPvStatistical, AdPvStatisticalExample>{
	
	@Autowired
	private AdPvStatisticalCustomMapper adPvStatisticalCustomMapper;
	//广告位列表查询
	public List<AdPvStatisticalCustom> selectAdPvStatisticalCustomByExample(
			AdPvStatisticalCustomExample adPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		return adPvStatisticalCustomMapper.selectAdPvStatisticalCustomByExample(adPvStatisticalCustomExample);
	}
 
	//广告位列表数量
	public Integer countCustomByExample(
			AdPvStatisticalCustomExample adPvStatisticalCustomExample) {
		// TODO Auto-generated method stub
		return adPvStatisticalCustomMapper.countCustomByExample(adPvStatisticalCustomExample);
	}
}

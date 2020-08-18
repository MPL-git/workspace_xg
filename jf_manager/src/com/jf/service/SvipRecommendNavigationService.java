package com.jf.service;

import com.jf.dao.SvipRecommendNavigationCustomMapper;
import com.jf.dao.SvipRecommendNavigationMapper;
import com.jf.entity.SvipRecommendNavigation;
import com.jf.entity.SvipRecommendNavigationExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class SvipRecommendNavigationService extends BaseService<SvipRecommendNavigation, SvipRecommendNavigationExample> {
	
	@Autowired
	private SvipRecommendNavigationMapper svipRecommendNavigationMapper;

	@Autowired
	private SvipRecommendNavigationCustomMapper svipRecommendNavigationCustomMapper;
	
	@Autowired
	public void setSvipProductRecommendMapper(SvipRecommendNavigationMapper svipRecommendNavigationMapper) {
		super.setDao(svipRecommendNavigationMapper);
		this.svipRecommendNavigationMapper = svipRecommendNavigationMapper;
	}

    public void saveSvipRecommendNavigation(SvipRecommendNavigation svipRecommendNavigation, Integer staffID) {
		Date date = new Date();
		if(svipRecommendNavigation.getId() == null){
			svipRecommendNavigation.setStatus("0");
			svipRecommendNavigation.setStatusDate(date);
			svipRecommendNavigation.setCreateBy(staffID);
			svipRecommendNavigation.setCreateDate(date);
			svipRecommendNavigation.setDelFlag("0");
			svipRecommendNavigationMapper.insertSelective(svipRecommendNavigation);
		}else {
			svipRecommendNavigation.setUpdateBy(staffID);
			svipRecommendNavigation.setUpdateDate(date);
			svipRecommendNavigationMapper.updateByPrimaryKey(svipRecommendNavigation);
		}
    }

	public void updateSeqNoIsNull(Integer id,Integer staffID,String date) {
		svipRecommendNavigationCustomMapper.updateSeqNoIsNull(id,staffID,date);
	}
}

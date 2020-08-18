package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.TrackDataMapper;
import com.jf.entity.TrackData;
import com.jf.entity.TrackDataExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
  * @author  chenwf: 
  * @date 创建时间：2017年9月6日 下午1:41:43 
  * @version 1.0 
  * @parameter  
  * @return  
*/
@Service
@Transactional
public class TrackDataService extends BaseService<TrackData, TrackDataExample> {
	@Autowired
	private TrackDataMapper trackDataMapper;

	@Autowired
	public void setTrackDataMapper(TrackDataMapper trackDataMapper) {
		this.setDao(trackDataMapper);
		this.trackDataMapper = trackDataMapper;
	}


	public TrackData findByIdfa(String idfa) {
		TrackDataExample example = new TrackDataExample();
		TrackDataExample.Criteria criteria = example.createCriteria();
		criteria.andIdfaEqualTo(idfa);
		example.setLimitStart(0);
		example.setLimitSize(1);
		List<TrackData> list = selectByExample(example);
		if(list.size() > 0)	return list.get(0);
		return null;
	}
	public TrackData findByImei(String imei) {
		TrackDataExample example = new TrackDataExample();
		TrackDataExample.Criteria criteria = example.createCriteria();
		criteria.andImeiEqualTo(imei);
		example.setLimitStart(0);
		example.setLimitSize(1);
		List<TrackData> list = selectByExample(example);
		if(list.size() > 0)	return list.get(0);
		return null;
	}
}

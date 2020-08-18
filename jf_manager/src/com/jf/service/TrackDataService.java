package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.TrackDataMapper;
import com.jf.entity.TrackData;
import com.jf.entity.TrackDataExample;

@Service
@Transactional
public class TrackDataService extends BaseService<TrackData, TrackDataExample> {

	@Autowired
	private TrackDataMapper trackDataMapper;
	
	@Autowired
	public void setTrackDataMapper(TrackDataMapper trackDataMapper) {
		super.setDao(trackDataMapper);
		this.trackDataMapper = trackDataMapper;
	}

	public Map<String, Object> updateOrInsertTrackData(HttpServletRequest request, List<TrackData> trackDataList, Integer staffId, String status) {
		if(trackDataList != null && trackDataList.size() > 0) {
			Integer updateNum = 0;
			Integer insertNum = 0;
			StringBuffer androidUpdate = new StringBuffer();
			if(status.equals("IOS")) {
				for(TrackData trackData : trackDataList) {
					++updateNum;
					TrackDataExample trackDataExample = new TrackDataExample();
					TrackDataExample.Criteria trackDataCriteria = trackDataExample.createCriteria();
					trackData.setUpdateBy(staffId);
					trackData.setUpdateDate(new Date());
					trackDataCriteria.andDelFlagEqualTo("0").andIdfaEqualTo(trackData.getIdfa());
					trackDataMapper.updateByExampleSelective(trackData, trackDataExample);
				}
			}else if(status.equals("ANDROID")) {
				for(TrackData trackData : trackDataList) {
					TrackDataExample trackDataExample = new TrackDataExample();
					TrackDataExample.Criteria trackDataCriteria = trackDataExample.createCriteria();
					trackDataCriteria.andDelFlagEqualTo("0").andImeiEqualTo(trackData.getImei());
					List<TrackData> TrackDatas = trackDataMapper.selectByExample(trackDataExample);
					if(TrackDatas != null && TrackDatas.size() > 0) {
						++updateNum;
						if(androidUpdate.length() < 1) {
							androidUpdate.append(trackData.getImei());
						}else {
							androidUpdate.append("，"+trackData.getImei());
						}
//						trackData.setUpdateBy(staffId);
//						trackData.setUpdateDate(new Date());
//						trackDataMapper.updateByExampleSelective(trackData, trackDataExample);
					}else {
						++insertNum;
						trackData.setCreateBy(staffId);
						trackData.setCreateDate(new Date());
						trackDataMapper.insertSelective(trackData);
					}
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("updateNum", updateNum);
			map.put("insertNum", insertNum);
			map.put("androidUpdate", androidUpdate);
			return map;
		}
		return null;
	}
	
}

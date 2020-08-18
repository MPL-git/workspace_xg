package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.SpreadNameCustomMapper;
import com.jf.dao.SpreadNameMapper;
import com.jf.dao.SysParamCfgMapper;
import com.jf.dao.TrackDataMapper;
import com.jf.entity.SpreadName;
import com.jf.entity.SpreadNameExample;
import com.jf.entity.SysParamCfg;
import com.jf.entity.SysParamCfgExample;
import com.jf.entity.TrackData;
import com.jf.entity.TrackDataExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpreadNameService extends BaseService<SpreadName,SpreadNameExample> {
	@Autowired
	private SpreadNameMapper spreadNameMapper;
	
	@Autowired
	private SpreadNameCustomMapper spreadNameCustomMapper;
	
	@Autowired
	private SysParamCfgMapper sysParamCfgMapper;
	
	@Autowired
	private TrackDataMapper trackDataMapper;
	
	@Autowired
	public void setSpreadNameMapper(SpreadNameMapper spreadNameMapper) {
		super.setDao(spreadNameMapper);
		this.spreadNameMapper = spreadNameMapper;
	}
	
    public int extrackSpreadName(){
    	TrackDataExample trackDataExample=new TrackDataExample();
    	trackDataExample.createCriteria();
    	trackDataExample.setOrderByClause("id desc");
    	trackDataExample.setLimitStart(0);
    	trackDataExample.setLimitSize(1);
    	List<TrackData> trackDatas=trackDataMapper.selectByExample(trackDataExample);
    	int extrackCount= spreadNameCustomMapper.insertFromTrackData();
    	
       //设置排序默认值	
    	spreadNameCustomMapper.batchUpdateSeqNo();
    	
    	if(trackDatas!=null&&trackDatas.size()>0){
			SysParamCfgExample paramCfgExample=new SysParamCfgExample();
        	paramCfgExample.createCriteria().andParamCodeEqualTo("CURRENT_SPREAD_NAME_EXTRACT_ID");
			SysParamCfg paramCfg4Update=new SysParamCfg();
        	paramCfg4Update.setParamValue(String.valueOf(trackDatas.get(0).getId()));
			sysParamCfgMapper.updateByExampleSelective(paramCfg4Update, paramCfgExample);
    	}

    	
    	return extrackCount;
    }
	
}

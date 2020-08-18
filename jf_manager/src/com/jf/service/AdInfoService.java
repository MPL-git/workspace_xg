package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.AdInfoCustomMapper;
import com.jf.dao.AdInfoMapper;
import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.AdInfo;
import com.jf.entity.AdInfoCustom;
import com.jf.entity.AdInfoExample;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;

@Service
@Transactional
public class AdInfoService extends BaseService<AdInfo,AdInfoExample> {
	@Autowired
	private AdInfoMapper adInfoMapper;
	@Autowired
	private AdInfoCustomMapper adInfoCustomMapper;
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	
	@Autowired
	public void setAdInfoMapper(AdInfoMapper adInfoMapper) {
		super.setDao(adInfoMapper);
		this.adInfoMapper = adInfoMapper;
	}
	
	public int countAdInfoCustomByExample(AdInfoExample example){
		return adInfoCustomMapper.countByExample(example);
	}
    public List<AdInfoCustom> selectAdInfoCustomByExample(AdInfoExample example){
    	return adInfoCustomMapper.selectByExample(example);
    }
    public AdInfoCustom selectAdInfoCustomByPrimaryKey(Integer id){
    	return adInfoCustomMapper.selectByPrimaryKey(id);
    }
	public int updateAdInfoSeqNo(HashMap<String,Object> map){
		return adInfoCustomMapper.updateAdInfoSeqNo(map);
	}
	
	public String countIndexAdInfo(HashMap<String,Object> map){
		return adInfoCustomMapper.countIndexAdInfo(map);
	}
	
	public String countTypeAdInfo(HashMap<String,Object> map){
		return adInfoCustomMapper.countTypeAdInfo(map);
	}

	public void saveThemePavilions(AdInfo adInfo) {
		if(adInfo.getId()!=null){
			this.updateByPrimaryKeySelective(adInfo);
		}else{
			DecorateInfo decorateInfo = new DecorateInfo();
			decorateInfo.setDelFlag("0");
			decorateInfo.setCreateDate(new Date());
			decorateInfo.setDecorateName(adInfo.getRemarks());
			decorateInfoMapper.insertSelective(decorateInfo);
			DecorateArea decorateArea = new DecorateArea();
			decorateArea.setDelFlag("0");
			decorateArea.setCreateDate(new Date());
			decorateArea.setDecorateInfoId(decorateInfo.getId());
			decorateArea.setRemarks(adInfo.getRemarks());
			decorateAreaMapper.insertSelective(decorateArea);
			adInfo.setLinkId(decorateInfo.getId());
			this.insertSelective(adInfo);
		}
	}
	
}

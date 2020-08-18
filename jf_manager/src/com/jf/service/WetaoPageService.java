package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.dao.WetaoPageCustomMapper;
import com.jf.dao.WetaoPageMapper;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;
import com.jf.entity.DecorateInfoExample;
import com.jf.entity.WetaoPage;
import com.jf.entity.WetaoPageCustom;
import com.jf.entity.WetaoPageExample;

@Service
@Transactional
public class WetaoPageService extends BaseService<WetaoPage,WetaoPageExample> {
	@Autowired
	private WetaoPageMapper wetaoPageMapper;
	@Autowired
	private WetaoPageCustomMapper wetaoPageCustomMapper;
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	@Autowired
	public void setWetaoPageMapper(WetaoPageMapper wetaoPageMapper) {
		super.setDao(wetaoPageMapper);
		this.wetaoPageMapper = wetaoPageMapper;
	}
	
	public int countCustomByExample(WetaoPageExample example){
		return wetaoPageCustomMapper.countByExample(example);
	}
    public List<WetaoPageCustom> selectCustomByExample(WetaoPageExample example){
    	return wetaoPageCustomMapper.selectByExample(example);
    }

	public void save(DecorateInfo di, DecorateArea da, WetaoPage wetaoPage) {
		decorateInfoMapper.insertSelective(di);
		da.setDecorateInfoId(di.getId());
		decorateAreaMapper.insertSelective(da);
		wetaoPage.setDecorateInfoId(di.getId());
		this.insertSelective(wetaoPage);
	}

	public void update(WetaoPage wetaoPage) {
		this.updateByPrimaryKeySelective(wetaoPage);
		DecorateInfo di = new DecorateInfo();
		di.setDecorateName(wetaoPage.getName());
		di.setUpdateDate(new Date());
		DecorateInfoExample example = new DecorateInfoExample();
		example.createCriteria().andIdEqualTo(wetaoPage.getDecorateInfoId());
		decorateInfoMapper.updateByExampleSelective(di, example);
	}
}

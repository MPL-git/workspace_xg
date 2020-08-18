package com.jf.service;

import java.util.Date;
import java.util.List;

import com.jf.entity.DecorateAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CustomPageCustomMapper;
import com.jf.dao.CustomPageMapper;
import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.entity.CustomPage;
import com.jf.entity.CustomPageCustom;
import com.jf.entity.CustomPageExample;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;

@Service
@Transactional
public class CustomPageService extends BaseService<CustomPage,CustomPageExample> {
	@Autowired
	private CustomPageMapper customPageMapper;
	@Autowired
	private CustomPageCustomMapper customPageCustomMapper;
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	@Autowired
	public void setCustomPageMapper(CustomPageMapper customPageMapper) {
		super.setDao(customPageMapper);
		this.customPageMapper = customPageMapper;
	}
	
	public int countCustomPageCustomByExample(CustomPageExample example){
		return customPageCustomMapper.countByExample(example);
	}
    public List<CustomPageCustom> selectCustomPageCustomByExample(CustomPageExample example){
    	return customPageCustomMapper.selectByExample(example);
    }

	public void save(CustomPage customPage,DecorateInfo decorateInfo,DecorateArea decorateArea) {
		if(customPage.getId()!=null){
			this.updateByPrimaryKeySelective(customPage);
		}else{
			decorateInfoMapper.insertSelective(decorateInfo);
			decorateArea.setDecorateInfoId(decorateInfo.getId());
			decorateAreaMapper.insertSelective(decorateArea);
			customPage.setDecorateInfoId(decorateInfo.getId());
			this.insertSelective(customPage);
		}
	}

    public void delBatch(Integer staffId, Integer customPageId) {
		CustomPageExample customPageExample = new CustomPageExample();
		customPageExample.createCriteria().andIdEqualTo(customPageId).andDelFlagEqualTo("0");
		List<CustomPage> customPages = customPageMapper.selectByExample(customPageExample);
		if(!customPages.isEmpty()){
			CustomPage customPage = customPages.get(0);
			customPage.setDelFlag("1");
			customPage.setUpdateDate(new Date());
			customPage.setUpdateBy(staffId);
			customPageMapper.updateByPrimaryKeySelective(customPage);

			DecorateInfo decorateInfo = new DecorateInfo();
			decorateInfo.setId(customPage.getDecorateInfoId());
			decorateInfo.setDelFlag("1");
			decorateInfo.setUpdateDate(new Date());
			decorateInfo.setUpdateBy(staffId);
			decorateInfoMapper.updateByPrimaryKeySelective(decorateInfo);

			DecorateAreaExample decorateAreaExample = new DecorateAreaExample();
			decorateAreaExample.createCriteria().andDecorateInfoIdEqualTo(decorateInfo.getId()).andDelFlagEqualTo("0");
			DecorateArea decorateArea = new DecorateArea();
			decorateArea.setDelFlag("1");
			decorateArea.setUpdateDate(new Date());
			decorateArea.setUpdateBy(staffId);
			decorateAreaMapper.updateByExampleSelective(decorateArea,decorateAreaExample);
		}
    }
}

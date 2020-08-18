package com.jf.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.BgModuleCategoryCustomMapper;
import com.jf.dao.BgModuleCategoryMapper;
import com.jf.entity.BgModuleCategory;
import com.jf.entity.BgModuleCategoryCustom;
import com.jf.entity.BgModuleCategoryExample;

@Service
@Transactional
public class BgModuleCategoryService extends BaseService<BgModuleCategory,BgModuleCategoryExample> {
	@Autowired
	private BgModuleCategoryMapper bgModuleCategoryMapper;
	@Autowired
	private BgModuleCategoryCustomMapper bgModuleCategoryCustomMapper;
	@Autowired
	public void setBgModuleCategoryMapper(BgModuleCategoryMapper bgModuleCategoryMapper) {
		super.setDao(bgModuleCategoryMapper);
		this.bgModuleCategoryMapper = bgModuleCategoryMapper;
	}
	
    public List<BgModuleCategoryCustom> selectBgModuleCategoryCustomByExample(BgModuleCategoryExample example){
    	return bgModuleCategoryCustomMapper.selectByExample(example);
    }
    
    public Integer getMaxSeqNo(Integer decorateModuleId){
    	return bgModuleCategoryCustomMapper.getMaxSeqNo(decorateModuleId);
    }

	public void deleteAndSort(BgModuleCategory bgModuleCategory) {
		this.updateByPrimaryKeySelective(bgModuleCategory);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("decorateModuleId", bgModuleCategory.getDecorateModuleId());
		map.put("seqNo", bgModuleCategory.getSeqNo());
		bgModuleCategoryCustomMapper.batchUpdateSeqNo(map);
	}

	public void updateBgModuleCategory(BgModuleCategory prevBgModuleCategory,BgModuleCategory nextBgModuleCategory) {
		this.updateByPrimaryKeySelective(prevBgModuleCategory);
		this.updateByPrimaryKeySelective(nextBgModuleCategory);
	}

	public void save(BgModuleCategory bmc) {
		if(bmc.getId()!=null){
			this.updateByPrimaryKeySelective(bmc);
		}else{
			this.insertSelective(bmc);
		}
	}
}

package com.jf.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DecorateModuleCustomMapper;
import com.jf.dao.DecorateModuleMapper;
import com.jf.dao.ModuleItemMapper;
import com.jf.dao.ModuleMapMapper;
import com.jf.entity.DecorateModule;
import com.jf.entity.DecorateModuleCustom;
import com.jf.entity.DecorateModuleExample;
import com.jf.entity.ModuleItem;
import com.jf.entity.ModuleItemExample;
import com.jf.entity.ModuleMap;
import com.jf.entity.ModuleMapExample;

@Service
@Transactional
public class DecorateModuleService extends BaseService<DecorateModule,DecorateModuleExample> {
	@Autowired
	private DecorateModuleMapper decorateModuleMapper;
	@Autowired
	private DecorateModuleCustomMapper decorateModuleCustomMapper;
	@Autowired
	private ModuleMapMapper moduleMapMapper;
	@Autowired
	private ModuleItemMapper moduleItemMapper;
	@Autowired
	public void setDecorateAreaMapper(DecorateModuleMapper decorateModuleMapper) {
		super.setDao(decorateModuleMapper);
		this.decorateModuleMapper = decorateModuleMapper;
	}
	
	public List<DecorateModuleCustom> selectDecorateModuleCustomByExample(DecorateModuleExample example){
		return decorateModuleCustomMapper.selectByExample(example);
	}

	public void save(DecorateModule decorateModule) {
		if(decorateModule.getId()!=null){
			this.updateByPrimaryKeySelective(decorateModule);
		}else{
			this.insertSelective(decorateModule);
		}
	}

	public void update(DecorateModule prevDecorateModule,DecorateModule nextDecorateModule) {
		this.updateByPrimaryKeySelective(prevDecorateModule);
		this.updateByPrimaryKeySelective(nextDecorateModule);
	}

	public void delete(DecorateModule decorateModule) {
		this.updateByPrimaryKeySelective(decorateModule);
		if(decorateModule.getModuleType().equals("1") || decorateModule.getModuleType().equals("8") || decorateModule.getModuleType().equals("9")){//1.图片
			ModuleMapExample mme = new ModuleMapExample();
			ModuleMapExample.Criteria mmec = mme.createCriteria();
			mmec.andDelFlagEqualTo("0");
			mmec.andDecorateModuleIdEqualTo(decorateModule.getId());
			ModuleMap moduleMap = new ModuleMap();
			moduleMap.setDelFlag("1");
			moduleMap.setUpdateDate(new Date());
			moduleMapMapper.updateByExampleSelective(moduleMap, mme);
		}else if(decorateModule.getModuleType().equals("2") || decorateModule.getModuleType().equals("3")){//2.商品
			ModuleItemExample mie = new ModuleItemExample();
			ModuleItemExample.Criteria miec = mie.createCriteria();
			miec.andDelFlagEqualTo("0");
			miec.andDecorateModuleIdEqualTo(decorateModule.getId());
			ModuleItem moduleItem = new ModuleItem();
			moduleItem.setDelFlag("1");
			moduleItem.setUpdateDate(new Date());
			moduleItemMapper.updateByExampleSelective(moduleItem, mie);
		}
	}

	public void save(DecorateModule decorateModule, List<ModuleItem> moduleItems) {
		if(decorateModule.getId()!=null){
			//先全部删除
			if(decorateModule.getModuleType().equals("3")){//特卖
				ModuleItemExample example = new ModuleItemExample();
				ModuleItemExample.Criteria c = example.createCriteria();
				c.andDelFlagEqualTo("0");
				c.andDecorateModuleIdEqualTo(decorateModule.getId());
				c.andItemTypeEqualTo("2");
				moduleItemMapper.deleteByExample(example);
			}
			for(ModuleItem moduleItem:moduleItems){
				moduleItem.setDecorateModuleId(decorateModule.getId());
				moduleItemMapper.insertSelective(moduleItem);
			}
			this.updateByPrimaryKeySelective(decorateModule);
		}else{
			this.insertSelective(decorateModule);
			for(ModuleItem moduleItem:moduleItems){
				moduleItem.setDecorateModuleId(decorateModule.getId());
				moduleItemMapper.insertSelective(moduleItem);
			}
		}
	}

	public void savePicModule(DecorateModule decorateModule,List<ModuleMap> moduleMaps) {
		if(decorateModule.getId()!=null){
			if(decorateModule.getModuleType().equals("1") || decorateModule.getModuleType().equals("8") || decorateModule.getModuleType().equals("9")){//先全部删除
				ModuleMapExample mme = new ModuleMapExample();
				ModuleMapExample.Criteria mmec = mme.createCriteria();
				mmec.andDelFlagEqualTo("0");
				mmec.andDecorateModuleIdEqualTo(decorateModule.getId());
				ModuleMap moduleMap = new ModuleMap();
				moduleMap.setDelFlag("1");
				moduleMap.setUpdateDate(new Date());
				moduleMapMapper.updateByExampleSelective(moduleMap, mme);
			}
			for(ModuleMap moduleMap:moduleMaps){//后添加
				moduleMap.setDecorateModuleId(decorateModule.getId());
				moduleMapMapper.insertSelective(moduleMap);
			}
			this.updateByPrimaryKeySelective(decorateModule);
		}else{
			this.insertSelective(decorateModule);
			for(ModuleMap moduleMap:moduleMaps){//后添加
				moduleMap.setDecorateModuleId(decorateModule.getId());
				moduleMapMapper.insertSelective(moduleMap);
			}
		}
	}
	
	public Integer getModuleCount(HashMap<String, Object> map) {
		return decorateModuleCustomMapper.getModuleCount(map);
	}
	
	public void createTopModule(DecorateModule dm,List<DecorateModuleCustom> decorateModuleCustoms) {
		insertSelective(dm);
		if(decorateModuleCustoms.size()>0){
			 ArrayList<Integer> arr = new ArrayList<Integer>();
			for (DecorateModuleCustom decorateModuleCustom : decorateModuleCustoms) {
				arr.add(decorateModuleCustom.getId());
			}
			decorateModuleCustomMapper.updateOtherModuleSeqNo(arr);
		}
	}
}

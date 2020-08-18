package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.jf.dao.*;
import com.jf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecorateAreaService extends BaseService<DecorateArea,DecorateAreaExample> {
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	@Autowired
	private DecorateAreaCustomMapper decorateAreaCustomMapper;
	@Autowired
	private DecorateModuleMapper decorateModuleMapper;
	@Autowired
	private ModuleMapMapper moduleMapMapper;
	@Autowired
	private ModuleItemMapper moduleItemMapper;
	@Autowired
	private SeckillModuleColorSettingMapper seckillModuleColorSettingMapper;
	@Autowired
	public void setDecorateAreaMapper(DecorateAreaMapper decorateAreaMapper) {
		super.setDao(decorateAreaMapper);
		this.decorateAreaMapper = decorateAreaMapper;
	}
	
	public List<DecorateAreaCustom> selectDecorateAreaCustomByExample(DecorateAreaExample example){
		return decorateAreaCustomMapper.selectByExample(example);
	}

	public void save(DecorateArea decorateArea) {
		if(decorateArea.getId()!=null){
			this.updateByPrimaryKeySelective(decorateArea);
		}else{
			this.insertSelective(decorateArea);
		}
	}

	public void deleteDecorateArea(Integer decorateAreaId) {
		DecorateModuleExample example = new DecorateModuleExample();
		DecorateModuleExample.Criteria criteria = example.createCriteria();
		criteria.andDelFlagEqualTo("0");
		criteria.andDecorateAreaIdEqualTo(decorateAreaId);
		List<DecorateModule> decorateModules = decorateModuleMapper.selectByExample(example);
		for(DecorateModule decorateModule:decorateModules){
			if(decorateModule.getModuleType().equals("1") || decorateModule.getModuleType().equals("8") || decorateModule.getModuleType().equals("9")){//1.图片
				ModuleMapExample mme = new ModuleMapExample();
				ModuleMapExample.Criteria mmec = mme.createCriteria();
				mmec.andDelFlagEqualTo("0");
				mmec.andDecorateModuleIdEqualTo(decorateModule.getId());
				moduleMapMapper.deleteByExample(mme);
			}else if(decorateModule.getModuleType().equals("2")){//2.商品
				ModuleItemExample mie = new ModuleItemExample();
				ModuleItemExample.Criteria miec = mie.createCriteria();
				miec.andDelFlagEqualTo("0");
				miec.andDecorateModuleIdEqualTo(decorateModule.getId());
				miec.andItemTypeEqualTo("1");
				moduleItemMapper.deleteByExample(mie);
			}
		}
		decorateModuleMapper.deleteByExample(example);
		DecorateArea decorateArea = this.selectByPrimaryKey(decorateAreaId);
		decorateArea.setDelFlag("1");
		this.updateByPrimaryKeySelective(decorateArea);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("decorateInfoId", decorateArea.getDecorateInfoId());
		paramMap.put("seqNo", decorateArea.getSeqNo());
		decorateAreaCustomMapper.updateSeqNo(paramMap);
	}

	public void update(DecorateArea prevDecorateArea,DecorateArea nextDecorateArea) {
		this.updateByPrimaryKeySelective(prevDecorateArea);
		this.updateByPrimaryKeySelective(nextDecorateArea);
	}

	public void save(DecorateArea dae, DecorateModule dm) {
		this.insertSelective(dae);
		dm.setDecorateAreaId(dae.getId());
		decorateModuleMapper.insertSelective(dm);
	}

	//插入领券秒杀的时候,设置默认的领券秒杀背景色
	public void insertCouponColor(Integer decorateModuleId,String moduleType,Integer staffId) {
		SeckillModuleColorSetting scs = new SeckillModuleColorSetting();
		scs.setDecorateModuleId(decorateModuleId);
		if("15".equals(moduleType)) {
		scs.setTotalBgColor("#6317B7");
		scs.setTimeColBgColor("#551188");
		scs.setSelectedBgColor("#8F1ECE");
		scs.setBtnDefaultBgColor("#8F1ECE");
		scs.setBtnSelectedBgColor("#D94555");
		scs.setDefaultFontColor("#FFFFFF");
		scs.setSelectedFontColor("#FFFFFF");
		scs.setCreateDate(new Date());
		scs.setDelFlag("0");
		scs.setCreateBy(staffId);
		scs.setCouponBgColor("#F8D7AA");
		}
		if("6".equals(moduleType)){
			scs.setDataSource("1");//默认为限时秒杀
			scs.setTotalBgColor("#FCC96E");
			scs.setTimeColBgColor("#FFFFFF");
			scs.setSelectedBgColor("#FCC96E");
			scs.setBtnDefaultBgColor("#FFFFFF");
			scs.setBtnSelectedBgColor("#FFFFFF");
			scs.setDefaultFontColor("#000000");
			scs.setSelectedFontColor("#000000");
			scs.setCreateDate(new Date());
			scs.setDelFlag("0");
			scs.setCreateBy(staffId);
		}
		seckillModuleColorSettingMapper.insert(scs);
	}
}

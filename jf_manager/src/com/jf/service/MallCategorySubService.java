package com.jf.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.dao.MallCategorySubCustomMapper;
import com.jf.dao.MallCategorySubMapper;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;
import com.jf.entity.MallCategorySub;
import com.jf.entity.MallCategorySubCustom;
import com.jf.entity.MallCategorySubExample;

@Service
@Transactional
public class MallCategorySubService extends BaseService<MallCategorySub,MallCategorySubExample> {
	@Autowired
	private MallCategorySubMapper dao;
	
	@Autowired
	private MallCategorySubCustomMapper mallCategorySubCustomMapper;
	
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	
	@Autowired
	public void setMallCategoryTopMapper(MallCategorySubMapper mallCategorySubMapper) {
		super.setDao(mallCategorySubMapper);
		this.dao = mallCategorySubMapper;
	}
	
	public int countMallCategorySubCustomByExample(MallCategorySubExample example){
		return mallCategorySubCustomMapper.countByExample(example);
	}
	
	public List<MallCategorySubCustom> selectMallCategorySubCustomByExample(MallCategorySubExample example){
		return mallCategorySubCustomMapper.selectByExample(example);
	}

	public void save(MallCategorySub mallCategorySub) {
		if(mallCategorySub.getId()!=null){
			this.updateByPrimaryKeySelective(mallCategorySub);
		}else{
			DecorateInfo di = new DecorateInfo();
			di.setDelFlag("0");
			di.setCreateDate(new Date());
			di.setDecorateName(mallCategorySub.getName());
			decorateInfoMapper.insertSelective(di);
			DecorateArea da = new DecorateArea();
			da.setAreaName(di.getDecorateName());
			da.setDecorateInfoId(di.getId());
			da.setDelFlag("0");
			da.setCreateDate(new Date());
			decorateAreaMapper.insertSelective(da);
			mallCategorySub.setDecorateInfoId(di.getId());
			this.insertSelective(mallCategorySub);
		}
	}

	public int getMaxSeqNo() {
		return mallCategorySubCustomMapper.getMaxSeqNo();
	}

	public void saveSeqNo(MallCategorySub mallCategorySub, int seqNo) {
		int maxSeqNo = mallCategorySubCustomMapper.getMaxSeqNo();
		int oldSeqNo=mallCategorySub.getSeqNo();
		if(maxSeqNo>=seqNo){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("seqNo", seqNo);
			map.put("oldSeqNo", oldSeqNo);
			if(oldSeqNo<seqNo){
				mallCategorySubCustomMapper.batchExecute(map);
			}else{
				mallCategorySubCustomMapper.batchExecute2(map);
			}
		}else{
			mallCategorySubCustomMapper.batchExecute3(oldSeqNo);
		}
		mallCategorySub.setSeqNo(maxSeqNo);
		this.updateByPrimaryKeySelective(mallCategorySub);
	}
	
}

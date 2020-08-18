package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.DecorateAreaMapper;
import com.jf.dao.DecorateInfoMapper;
import com.jf.dao.MallCategoryTopCustomMapper;
import com.jf.dao.MallCategoryTopMapper;
import com.jf.dao.ProductTypeMapper;
import com.jf.entity.DecorateArea;
import com.jf.entity.DecorateInfo;
import com.jf.entity.MallCategoryTop;
import com.jf.entity.MallCategoryTopCustom;
import com.jf.entity.MallCategoryTopExample;
import com.jf.entity.ProductType;

@Service
@Transactional
public class MallCategoryTopService extends BaseService<MallCategoryTop,MallCategoryTopExample> {
	@Autowired
	private MallCategoryTopMapper dao;
	
	@Autowired
	private MallCategoryTopCustomMapper mallCategoryTopCustomMapper;
	
	@Autowired
	private DecorateInfoMapper decorateInfoMapper;
	
	@Autowired
	private ProductTypeMapper productTypeMapper;
	
	@Autowired
	private DecorateAreaMapper decorateAreaMapper;
	
	@Autowired
	public void setMallCategoryTopMapper(MallCategoryTopMapper mallCategoryTopMapper) {
		super.setDao(mallCategoryTopMapper);
		this.dao = mallCategoryTopMapper;
	}
	
	public int countMallCategoryTopCustomByExample(MallCategoryTopExample example){
		return mallCategoryTopCustomMapper.countByExample(example);
	}
	
	public List<MallCategoryTopCustom> selectMallCategoryTopCustomByExample(MallCategoryTopExample example){
		return mallCategoryTopCustomMapper.selectByExample(example);
	}

	public void save(MallCategoryTop mallCategoryTop) {
		if(mallCategoryTop.getId()!=null){
			this.updateByPrimaryKeySelective(mallCategoryTop);
		}else{
			DecorateInfo di = new DecorateInfo();
			di.setDelFlag("0");
			di.setCreateDate(new Date());
			if(mallCategoryTop.getProductTypeId().equals(1)){
				di.setDecorateName("商城【首页】");
			}else{
				ProductType productType = productTypeMapper.selectByPrimaryKey(mallCategoryTop.getProductTypeId());
				di.setDecorateName("商城【"+productType.getName()+"馆】");
			}
			decorateInfoMapper.insertSelective(di);
			DecorateArea da = new DecorateArea();
			da.setAreaName(di.getDecorateName());
			da.setDecorateInfoId(di.getId());
			da.setDelFlag("0");
			da.setCreateDate(new Date());
			decorateAreaMapper.insertSelective(da);
			mallCategoryTop.setDecorateInfoId(di.getId());
			this.insertSelective(mallCategoryTop);
		}
	}
	
}

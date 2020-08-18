package com.jf.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SeckillBrandGroupMapper;
import com.jf.dao.SeckillBrandGroupProductCustomMapper;
import com.jf.dao.SeckillBrandGroupProductMapper;
import com.jf.entity.SeckillBrandGroup;
import com.jf.entity.SeckillBrandGroupExample;
import com.jf.entity.SeckillBrandGroupProduct;
import com.jf.entity.SeckillBrandGroupProductCustom;
import com.jf.entity.SeckillBrandGroupProductExample;

@Service
@Transactional
public class SeckillBrandGroupService extends BaseService<SeckillBrandGroup, SeckillBrandGroupExample> {

	@Autowired
	private SeckillBrandGroupMapper seckillBrandGroupMapper;
	
	@Autowired
	private SeckillBrandGroupProductMapper seckillBrandGroupProductMapper;
	
	@Autowired
	private SeckillBrandGroupProductCustomMapper seckillBrandGroupProductCustomMapper;
	
	@Autowired
	public void setSeckillBrandGroupMapper(SeckillBrandGroupMapper seckillBrandGroupMapper) {
		super.setDao(seckillBrandGroupMapper);
		this.seckillBrandGroupMapper = seckillBrandGroupMapper;
	}
	
	/**
	 * 
	 * @Title delSeckillBrandGroup   
	 * @Description TODO(这里用一句话描述这个方法的作用)   
	 * @author pengl
	 * @date 2018年4月18日 下午6:47:50
	 */
	public void delSeckillBrandGroup(Integer seckillBrandGroupId, Integer staffID) {
		Date date = new Date();
		SeckillBrandGroupProduct seckillBrandGroupProduct = new SeckillBrandGroupProduct();
		seckillBrandGroupProduct.setUpdateBy(staffID);
		seckillBrandGroupProduct.setUpdateDate(date);
		seckillBrandGroupProduct.setDelFlag("1");
		SeckillBrandGroupProductExample seckillBrandGroupProductExample = new SeckillBrandGroupProductExample();
		seckillBrandGroupProductExample.createCriteria().andDelFlagEqualTo("0").andSeckillBrandGroupIdEqualTo(seckillBrandGroupId);
		seckillBrandGroupProductMapper.updateByExampleSelective(seckillBrandGroupProduct, seckillBrandGroupProductExample);
		SeckillBrandGroup seckillBrandGroup = new SeckillBrandGroup();
		seckillBrandGroup.setId(seckillBrandGroupId);
		seckillBrandGroup.setUpdateBy(staffID);
		seckillBrandGroup.setUpdateDate(date);
		seckillBrandGroup.setDelFlag("1");
		seckillBrandGroupMapper.updateByPrimaryKeySelective(seckillBrandGroup);
	}
	
	/**
	 * 
	 * @Title addSeckillBrandGroupProduct   
	 * @Description TODO(添加秒杀品牌团商品表)   
	 * @author pengl
	 * @date 2018年4月19日 下午1:11:36
	 */
	public void addSeckillBrandGroupProduct(Integer staffID, String singleProductActivityIds, String seckillBrandGroupId) {
		String[] strs = singleProductActivityIds.split(",");
		Date date = new Date();
		for(String singleProductActivityId : strs) {
			SeckillBrandGroupProduct seckillBrandGroupProduct = new SeckillBrandGroupProduct();
			seckillBrandGroupProduct.setSeckillBrandGroupId(Integer.parseInt(seckillBrandGroupId));
			seckillBrandGroupProduct.setSingleProductActivityId(Integer.parseInt(singleProductActivityId));
			seckillBrandGroupProduct.setCreateBy(staffID);
			seckillBrandGroupProduct.setCreateDate(date);
			seckillBrandGroupProduct.setUpdateDate(date);
			seckillBrandGroupProduct.setDelFlag("0");
			seckillBrandGroupProductMapper.insertSelective(seckillBrandGroupProduct);
		}
	}
	
}

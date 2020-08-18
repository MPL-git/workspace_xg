package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoExample;

@Service
public class KdnWuliuInfoService extends BaseService<KdnWuliuInfo, KdnWuliuInfoExample> {

	@Autowired
	private KdnWuliuInfoMapper dao;
	
	@Autowired
	public void setKdnWuliuInfoMapper(KdnWuliuInfoMapper dao) {
		super.setDao(dao);
		this.dao = dao;
	}
	
	/**
	 * 查询物流信息
	 */
	public String findTractInfo(Integer expressId, String expressNo){
		KdnWuliuInfoExample kdnWuliuInfoExample = new KdnWuliuInfoExample();
		KdnWuliuInfoExample.Criteria kdnWuliuInfoCriteria = kdnWuliuInfoExample.createCriteria();
		kdnWuliuInfoCriteria.andDelFlagEqualTo("0");
		kdnWuliuInfoCriteria.andExpressIdEqualTo(expressId);
		kdnWuliuInfoCriteria.andLogisticCodeEqualTo(expressNo);
		List<KdnWuliuInfo> list = dao.selectByExample(kdnWuliuInfoExample);
		if(list.size() == 0)	return null;
		return list.get(0).getTractInfo();
	}

	
}

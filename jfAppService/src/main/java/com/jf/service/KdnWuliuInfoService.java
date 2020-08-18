package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.KdnWuliuInfoCustomMapper;
import com.jf.dao.KdnWuliuInfoMapper;
import com.jf.entity.KdnWuliuInfo;
import com.jf.entity.KdnWuliuInfoCustom;
import com.jf.entity.KdnWuliuInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author chenwf:
 * @date 创建时间：2017年6月5日 下午7:51:47
 * @version 1.0
 * @parameter
 * @return
 */

@Service
@Transactional
public class KdnWuliuInfoService extends BaseService<KdnWuliuInfo, KdnWuliuInfoExample> {
	
	@Autowired
	private KdnWuliuInfoMapper kdnWuliuInfoMapper;
	
	@Autowired
	private KdnWuliuInfoCustomMapper kdnWuliuInfoCustomMapper;

	@Autowired
	public void setKdnWuliuInfoMapper(KdnWuliuInfoMapper kdnWuliuInfoMapper) {
		this.setDao(kdnWuliuInfoMapper);
		this.kdnWuliuInfoMapper = kdnWuliuInfoMapper;
	}

	public KdnWuliuInfoCustom getExpressInfo(Map<String, Object> params) {
		
		return kdnWuliuInfoCustomMapper.getExpressInfo(params);
	}
	
	
}

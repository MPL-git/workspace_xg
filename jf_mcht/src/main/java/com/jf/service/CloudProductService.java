package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CloudProductCustomMapper;
import com.jf.dao.CloudProductMapper;
import com.jf.entity.CloudProduct;
import com.jf.entity.CloudProductCustom;
import com.jf.entity.CloudProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CloudProductService extends BaseService<CloudProduct,CloudProductExample> {
	@Autowired
	private CloudProductMapper dao;
	
	@Autowired
	private CloudProductCustomMapper cloudProductCustomMapper;
	
	@Autowired
	public void setCloudProductMapper(CloudProductMapper cloudProductMapper) {
		super.setDao(cloudProductMapper);
		this.dao = cloudProductMapper;
	}

	public int countByCustomExample(HashMap<String, Object> map) {
		return cloudProductCustomMapper.countByExample(map);
	}
	
	public List<CloudProductCustom> selectByCustomExample(HashMap<String, Object> map) {
		return cloudProductCustomMapper.selectByExample(map);
	}

	public List<HashMap<String, Object>> getRelatedProduct(HashMap<String,Object> map) {
		return cloudProductCustomMapper.getRelatedProduct(map);
	}

	public CloudProductCustom selectCustomById(int id) {
		return cloudProductCustomMapper.selectCustomById(id);
	}
}

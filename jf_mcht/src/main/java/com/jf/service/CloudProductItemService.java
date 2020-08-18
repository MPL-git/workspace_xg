package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CloudProductItemCustomMapper;
import com.jf.dao.CloudProductItemMapper;
import com.jf.entity.CloudProductItem;
import com.jf.entity.CloudProductItemCustom;
import com.jf.entity.CloudProductItemCustomExample;
import com.jf.entity.CloudProductItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class CloudProductItemService extends BaseService<CloudProductItem,CloudProductItemExample> {
	@Autowired
	private CloudProductItemMapper dao;
	
	@Autowired
	private CloudProductItemCustomMapper cloudProductItemCustomMapper;
	
	@Autowired
	public void setCloudProductItemMapper(CloudProductItemMapper cloudProductItemMapper) {
		super.setDao(cloudProductItemMapper);
		this.dao = cloudProductItemMapper;
	}

	public int countByCustomExample(CloudProductItemCustomExample example) {
		return cloudProductItemCustomMapper.countByExample(example);
	}

	public List<CloudProductItemCustom> selectByCustomExample(CloudProductItemCustomExample example) {
		return cloudProductItemCustomMapper.selectByExample(example);
	}
	
	public String getSupplierStatusByItemId(Integer id){
		return cloudProductItemCustomMapper.getSupplierStatusByItemId(id);
	}
	
	public String getMchtSupplierStatus(HashMap<String, Object> map){
		return cloudProductItemCustomMapper.getMchtSupplierStatus(map);
	}
}

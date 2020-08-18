package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CloudProductItemCustomMapper;
import com.jf.dao.CloudProductItemMapper;
import com.jf.entity.CloudProductItem;
import com.jf.entity.CloudProductItemCustom;
import com.jf.entity.CloudProductItemExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CloudProductItemService extends BaseService<CloudProductItem, CloudProductItemExample> {
	@Autowired
	private CloudProductItemMapper cloudProductItemMapper;
	@Autowired
	private CloudProductItemCustomMapper cloudProductItemCustomMapper;
	@Autowired
	public void setCloudProductItemMapper(CloudProductItemMapper cloudProductItemMapper) {
		this.setDao(cloudProductItemMapper);
		this.cloudProductItemMapper = cloudProductItemMapper;
	}
	


	public CloudProductItem findModel(Integer cloudProductItemId) {
		CloudProductItem cloudProductItem = null;
		CloudProductItemExample cloudProductItemExample = new CloudProductItemExample();
		cloudProductItemExample.createCriteria().andIdEqualTo(cloudProductItemId).andDelFlagEqualTo("0");
		List<CloudProductItem> cloudProductItems = cloudProductItemMapper.selectByExample(cloudProductItemExample);
		if(CollectionUtils.isNotEmpty(cloudProductItems)){
			cloudProductItem = cloudProductItems.get(0);
		}
		return cloudProductItem;
	}



	public List<CloudProductItemCustom> getCloudProducrItems(Integer cloudProductItemId) {
		
		return cloudProductItemCustomMapper.getCloudProducrItems(cloudProductItemId);
	}
}

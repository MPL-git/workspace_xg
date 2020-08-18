package com.jf.service;

import java.util.List;

import com.jf.common.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.CloudProductAfterTempletMapper;
import com.jf.entity.CloudProductAfterTemplet;
import com.jf.entity.CloudProductAfterTempletExample;

@Service
@Transactional
public class CloudProductAfterTempletService extends BaseService<CloudProductAfterTemplet, CloudProductAfterTempletExample> {
	@Autowired
	private CloudProductAfterTempletMapper cloudProductAfterTempletMapper;
	@Autowired
	public void setCloudProductAfterTempletMapper(CloudProductAfterTempletMapper cloudProductAfterTempletMapper) {
		this.setDao(cloudProductAfterTempletMapper);
		this.cloudProductAfterTempletMapper = cloudProductAfterTempletMapper;
	}
	
	public CloudProductAfterTemplet findModel(Integer id) {
		CloudProductAfterTemplet cloudProductAfterTemplet = null;
		CloudProductAfterTempletExample cloudProductAfterTempletExample = new CloudProductAfterTempletExample();
		cloudProductAfterTempletExample.createCriteria().andIdEqualTo(id).andDelFlagEqualTo("0");
		List<CloudProductAfterTemplet> cloudProductAfterTemplets = cloudProductAfterTempletMapper.selectByExample(cloudProductAfterTempletExample);
		if(CollectionUtils.isNotEmpty(cloudProductAfterTemplets)){
			cloudProductAfterTemplet = cloudProductAfterTemplets.get(0);
		}
		return cloudProductAfterTemplet;
	}
}

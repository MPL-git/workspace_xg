package com.jf.service;

import com.jf.common.base.BaseService;
import com.jf.dao.CloudProductAfterTempletCustomMapper;
import com.jf.dao.CloudProductAfterTempletMapper;
import com.jf.entity.CloudProductAfterTemplet;
import com.jf.entity.CloudProductAfterTempletCustom;
import com.jf.entity.CloudProductAfterTempletCustomExample;
import com.jf.entity.CloudProductAfterTempletExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CloudProductAfterTempletService extends BaseService<CloudProductAfterTemplet,CloudProductAfterTempletExample> {
	@Autowired
	private CloudProductAfterTempletMapper cloudProductAfterTempletMapper;
	@Autowired
	private CloudProductAfterTempletCustomMapper cloudProductAfterTempletCustomMapper;
	@Autowired
	public void setCloudProductAfterTempletMapper(CloudProductAfterTempletMapper cloudProductAfterTempletMapper) {
		super.setDao(cloudProductAfterTempletMapper);
		this.cloudProductAfterTempletMapper = cloudProductAfterTempletMapper;
	}
	
	public int countCustomByExample(CloudProductAfterTempletCustomExample example){
		return cloudProductAfterTempletCustomMapper.countByExample(example);
	}
    public List<CloudProductAfterTempletCustom> selectCustomByExample(CloudProductAfterTempletCustomExample example){
    	return cloudProductAfterTempletCustomMapper.selectByExample(example);
    }
    public CloudProductAfterTempletCustom selectCustomByPrimaryKey(Integer id){
    	return cloudProductAfterTempletCustomMapper.selectByPrimaryKey(id);
    }
    
}

package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SpreadActivityGroupCustomMapper;
import com.jf.dao.SpreadActivityGroupMapper;
import com.jf.entity.SpreadActivityGroup;
import com.jf.entity.SpreadActivityGroupCustom;
import com.jf.entity.SpreadActivityGroupExample;

@Service
@Transactional
public class SpreadActivityGroupService extends BaseService<SpreadActivityGroup,SpreadActivityGroupExample> {
	@Autowired
	private SpreadActivityGroupMapper spreadActivityGroupMapper;
	
	@Autowired
	private SpreadActivityGroupCustomMapper spreadActivityGroupCustomMapper;
	@Autowired
	public void setSpreadActivityGroupMapper(SpreadActivityGroupMapper spreadActivityGroupMapper) {
		super.setDao(spreadActivityGroupMapper);
		this.spreadActivityGroupMapper = spreadActivityGroupMapper;
	}
	
	
    public List<SpreadActivityGroupCustom> selectSpreadActivityGroupCustomByExample(SpreadActivityGroupExample example){
    	return spreadActivityGroupCustomMapper.selectByExample(example);
    }
    
    public void saveSpreadActivityGroup(SpreadActivityGroup spreadActivityGroup){
    	
    	if(spreadActivityGroup.getId()==null){
    	   spreadActivityGroupMapper.insertSelective(spreadActivityGroup);
    	}else{
    	   spreadActivityGroupMapper.updateByPrimaryKeySelective(spreadActivityGroup);
    	}
    	
    }
    
    public void batchEditSpreadActivityGroup(String[] spreadActivityGroupIds,SpreadActivityGroup spreadActivityGroup){
    	
    	if(spreadActivityGroupIds==null||spreadActivityGroupIds.length==0){
    		return;
    	}
    	
    	for (int i = 0; i < spreadActivityGroupIds.length; i++) {
    		spreadActivityGroup.setId(Integer.valueOf(spreadActivityGroupIds[i]));
    		spreadActivityGroupMapper.updateByPrimaryKeySelective(spreadActivityGroup);
		}
    	
    	
    }
	
}

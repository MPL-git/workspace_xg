package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SpreadNameCustomMapper;
import com.jf.dao.SpreadNameMapper;
import com.jf.entity.SpreadActivityGroup;
import com.jf.entity.SpreadName;
import com.jf.entity.SpreadNameCustom;
import com.jf.entity.SpreadNameCustomExample;
import com.jf.entity.SpreadNameExample;

@Service
@Transactional
public class SpreadNameService extends BaseService<SpreadName,SpreadNameExample> {
	@Autowired
	private SpreadNameMapper spreadNameMapper;
	
	@Autowired
	private SpreadNameCustomMapper spreadNameCustomMapper;
	
	
	@Autowired
	public void setSpreadNameMapper(SpreadNameMapper spreadNameMapper) {
		super.setDao(spreadNameMapper);
		this.spreadNameMapper = spreadNameMapper;
	}
	
	
    public List<SpreadNameCustom> selectSpreadNameCustomByExample(SpreadNameCustomExample example){
    	return spreadNameCustomMapper.selectByExample(example);
    }
    
    public int countSpreadNameCustomByExample(SpreadNameCustomExample example){
    	return spreadNameCustomMapper.countByExample(example);
    }



    public  SpreadNameCustom selectSpreadNameCustomByPrimaryKey(Integer id){
    	return spreadNameCustomMapper.selectByPrimaryKey(id);
    }
    
    
    public void batchEditSpreadName(String[] spreadNameIds,SpreadName spreadName){
    	
    	if(spreadNameIds==null||spreadNameIds.length==0){
    		return;
    	}
    	
    	for (int i = 0; i < spreadNameIds.length; i++) {
    		spreadName.setId(Integer.valueOf(spreadNameIds[i]));
    		spreadNameMapper.updateByPrimaryKeySelective(spreadName);
		}
    	
    	
    }
    
    
	
}

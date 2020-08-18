package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jf.dao.InformationCustomMapper;
import com.jf.dao.InformationMapper;
import com.jf.entity.Information;
import com.jf.entity.InformationCustom;
import com.jf.entity.InformationCustomExample;
import com.jf.entity.InformationExample;

@Service
public class InformationService extends BaseService<Information, InformationExample>{
	
	@Autowired
	private InformationMapper infoMapper;
	
	@Autowired
	private InformationCustomMapper infCustomMapper;
	
	@Autowired
	public void setMchtInfoMapper(InformationMapper infoMapper) {
		super.setDao(infoMapper);
		this.infoMapper = infoMapper;
	}
	
	 public List<InformationCustom> selectInformationByExample(InformationCustomExample example){
	    	return infCustomMapper.selectInformationByExample(example);
	 }
	 
	 public Integer countByExample(InformationCustomExample example){
		 return infCustomMapper.countByExample(example);
	 }
	 
	 public Integer updateReleaseTimeByPrimaryKey(Information record){
		 return infCustomMapper.updateReleaseTimeByPrimaryKey(record);
	 }
	 
	 public List<Information> selectByExampleWithBLOBs(InformationExample example) {
		 return infoMapper.selectByExampleWithBLOBs(example);
	 }
	
}

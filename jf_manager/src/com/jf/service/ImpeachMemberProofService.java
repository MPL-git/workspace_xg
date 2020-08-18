package com.jf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ImpeachMemberProofCustomMapper;
import com.jf.dao.ImpeachMemberProofMapper;
import com.jf.entity.ImpeachMemberProof;
import com.jf.entity.ImpeachMemberProofCustom;
import com.jf.entity.ImpeachMemberProofCustomExample;
import com.jf.entity.ImpeachMemberProofExample;

@Service
@Transactional
public class ImpeachMemberProofService extends BaseService<ImpeachMemberProof,ImpeachMemberProofExample> {
	@Autowired
	private ImpeachMemberProofMapper impeachMemberProofMapper;
	@Autowired
	private ImpeachMemberProofCustomMapper impeachMemberProofCustomMapper;	
	@Autowired
	public void setImpeachMemberProofMapper(ImpeachMemberProofMapper impeachMemberProofMapper) {
		super.setDao(impeachMemberProofMapper);
		this.impeachMemberProofMapper = impeachMemberProofMapper;
	}
	
	public int countByImpeachMemberProofCustomExample(ImpeachMemberProofCustomExample example){
		return impeachMemberProofCustomMapper.countByImpeachMemberProofCustomExample(example);
	}
    public List<ImpeachMemberProofCustom> selectMemberFootprintCustomByExample(ImpeachMemberProofCustomExample example){
    	return impeachMemberProofCustomMapper.selectByImpeachMemberProofCustomExample(example);
    }
    public ImpeachMemberProofCustom selectByImpeachMemberProofCustomPrimaryKey(Integer id){
    	return impeachMemberProofCustomMapper.selectByImpeachMemberProofCustomPrimaryKey(id);
    }
}

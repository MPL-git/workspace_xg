package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.ImpeachMemberCustomMapper;
import com.jf.dao.ImpeachMemberMapper;
import com.jf.entity.ImpeachMember;
import com.jf.entity.ImpeachMemberCustom;
import com.jf.entity.ImpeachMemberCustomExample;
import com.jf.entity.ImpeachMemberExample;

@Service
@Transactional
public class ImpeachMemberService extends BaseService<ImpeachMember,ImpeachMemberExample> {
	@Autowired
	private ImpeachMemberMapper impeachMemberMapper;
	@Autowired
	private ImpeachMemberCustomMapper impeachMemberCustomMapper;	
	@Autowired
	public void setImpeachMemberMapper(ImpeachMemberMapper impeachMemberMapper) {
		super.setDao(impeachMemberMapper);
		this.impeachMemberMapper = impeachMemberMapper;
	}
	
	public int countByImpeachMemberCustomExample(ImpeachMemberCustomExample example){
		return impeachMemberCustomMapper.countByImpeachMemberCustomExample(example);
	}
    public List<ImpeachMemberCustom> selectByImpeachMemberCustomExample(ImpeachMemberCustomExample example){
    	return impeachMemberCustomMapper.selectByImpeachMemberCustomExample(example);
    }
    public ImpeachMemberCustom selectByImpeachMemberCustomPrimaryKey(Integer id){
    	return impeachMemberCustomMapper.selectByImpeachMemberCustomPrimaryKey(id);
    }
    
    public List<Map<String, Object>> getCommissionerauditbyList(){
		return impeachMemberCustomMapper.getCommissionerauditbyList();
	}
    
    public List<Map<String, Object>> getFwauditbyList(){
		return impeachMemberCustomMapper.getFwauditbyList();
	}
    public List<Map<String, Object>> getReceiverbyList(){
		return impeachMemberCustomMapper.getReceiverbyList();
	}
}

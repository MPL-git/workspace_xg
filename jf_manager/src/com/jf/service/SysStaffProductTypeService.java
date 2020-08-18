package com.jf.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jf.dao.SysStaffProductTypeCustomMapper;
import com.jf.dao.SysStaffProductTypeMapper;
import com.jf.entity.SysStaffProductType;
import com.jf.entity.SysStaffProductTypeCustom;
import com.jf.entity.SysStaffProductTypeCustomExample;
import com.jf.entity.SysStaffProductTypeExample;

@Service
@Transactional
public class SysStaffProductTypeService extends BaseService<SysStaffProductType, SysStaffProductTypeExample> {

	@Autowired
	private SysStaffProductTypeMapper sysStaffProductTypeMapper;
	
	@Autowired
	private SysStaffProductTypeCustomMapper sysStaffProductTypeCustomMapper;
	
	@Autowired
	public void setASysStaffProductTypeMapper(SysStaffProductTypeMapper sysStaffProductTypeMapper) {
		super.setDao(sysStaffProductTypeMapper);
		this.sysStaffProductTypeMapper = sysStaffProductTypeMapper;
	}
	
	public List<SysStaffProductTypeCustom> selectByCustomExample(SysStaffProductTypeCustomExample example){
		return sysStaffProductTypeCustomMapper.selectByCustomExample(example);
	}
	   
   public Integer countByCustomExample(SysStaffProductTypeCustomExample example){
	   return sysStaffProductTypeCustomMapper.countByCustomExample(example);
   }
   
   public SysStaffProductTypeCustom selectByCustomPrimaryKey(Integer id){
   	return sysStaffProductTypeCustomMapper.selectByPrimaryKeyCustom(id);
   }
   
   /**
    * 根据用户id查询用户负责主营类目 
    * 
    * @param staffId
    * @return
    */
   public List<Map<String, Object>> selectByStaffId(Integer staffId) {
	   return sysStaffProductTypeCustomMapper.selectByStaffId(staffId);
   }
	
}

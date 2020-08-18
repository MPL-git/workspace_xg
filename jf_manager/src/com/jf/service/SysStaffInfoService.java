package com.jf.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzs.common.beans.ProductType;
import com.jf.dao.BrandTmkPicTmpMapper;
import com.jf.dao.SysStaffInfoCustomMapper;
import com.jf.dao.SysStaffInfoMapper;
import com.jf.entity.SysStaffInfo;
import com.jf.entity.SysStaffInfoCustom;
import com.jf.entity.SysStaffInfoCustomExample;
import com.jf.entity.SysStaffInfoExample;

@Service
@Transactional
public class SysStaffInfoService extends BaseService<SysStaffInfo, SysStaffInfoExample>{
	@Autowired
	SysStaffInfoMapper sysStaffInfo;
	
	@Autowired
	private SysStaffInfoCustomMapper sysStaffInfoCustomMapper;
	
	@Resource
	private BrandTmkPicTmpMapper brandTmkPicTmpMapper;
	
	@Autowired
	public void setSysStaffInfoMapper(SysStaffInfoMapper SysStaffInfoMapper) {
		super.setDao(SysStaffInfoMapper);
		this.sysStaffInfo = SysStaffInfoMapper;
	}
	
	public int countByCustomExample(SysStaffInfoCustomExample example) {
		return sysStaffInfoCustomMapper.countByCustomExample(example);
	}

	public List<SysStaffInfoCustom> selectByCustomExample(SysStaffInfoCustomExample example) {
		return sysStaffInfoCustomMapper.selectByCustomExample(example);
	}

	public SysStaffInfoCustom selectByCustomPrimaryKey(Integer staffId) {
		return sysStaffInfoCustomMapper.selectByCustomPrimaryKey(staffId);
	}

	public int updateByCustomExampleSelective(@Param("record") SysStaffInfo record, @Param("example") SysStaffInfoCustomExample example) {
		return sysStaffInfoCustomMapper.updateByCustomExampleSelective(record, example);
	}
	
	  //查询用户的上级人员
   public  List<SysStaffInfoCustom> selectSuperior(Integer staffId){
	   return sysStaffInfoCustomMapper.selectSuperior(staffId);
   };
   
}

